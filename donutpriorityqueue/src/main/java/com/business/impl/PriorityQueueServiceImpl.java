package com.business.impl;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.business.dto.ResponseEntriesDTO;
import com.business.service.PriorityQueueService;

@Service
public class PriorityQueueServiceImpl implements PriorityQueueService {

	static Map<String, String> data = new HashMap<String, String>();
	static List<String> pQueue = new ArrayList<String>();
	static Map<String,LocalDateTime> timeMap = new HashMap<String,LocalDateTime>();

	@Override
	public ResponseEntity<?> placeOrder(String ClientId, String quantity) {
		Long cid = Long.valueOf(ClientId);
		if(cid>=1 && cid<=20000) {
			synchronized (this) {
				List<Long> premiumCustomers = pQueue.stream().map(x -> Long.valueOf(x)).filter(x -> x<1000).collect(Collectors.toList());
				List<Long> normalCustomers = pQueue.stream().map(x -> Long.valueOf(x)).filter(x -> x>1000).collect(Collectors.toList());
				if(pQueue.contains(ClientId)) {
					return new ResponseEntity<> ("Cannot place new order while processing your previous orders",HttpStatus.BAD_REQUEST);
				}
				if(cid <= 1000) {
					premiumCustomers.add(cid);
				}
				else {
					normalCustomers.add(cid);
					
				}
				pQueue.clear();
				premiumCustomers.stream().map(x -> String.valueOf(x)).forEach(x -> pQueue.add(x));
				normalCustomers.stream().map(x -> String.valueOf(x)).forEach(x -> pQueue.add(x));
				InsertIntoMap(ClientId,quantity);
			}
			}
			else {
				return new ResponseEntity<> ("Invalid Client Id -  Order cannot be placed",HttpStatus.OK);
			}
			return new ResponseEntity<> ("Your Order has been placed Successfully",HttpStatus.OK);
		}

		@Override
		public ResponseEntity<?> checkOrderStatus(String ClientId) {
			if(pQueue.contains(ClientId)) {
				List<String> statusList = new ArrayList<String>(pQueue);
				Integer id = statusList.indexOf(ClientId);
				Map<String,String> responseMap = new HashMap<String, String>();
				responseMap.put("Clientid", ClientId);
				responseMap.put("Position", String.valueOf(id+1));
				Long waitTime = ChronoUnit.SECONDS.between(timeMap.get(ClientId), LocalDateTime.now());
				LocalTime tod = LocalTime.ofSecondOfDay(waitTime);
				responseMap.put("WaitingTime",tod.toString());
				return new ResponseEntity<> (responseMap,HttpStatus.OK);
			}
			else {
				return new ResponseEntity<> ("No Orders have been placed under this id",HttpStatus.BAD_REQUEST);
			}
		}

		@Override
		public ResponseEntity<?> checkEntries() {
			List<ResponseEntriesDTO> response = new ArrayList<ResponseEntriesDTO>();
			pQueue.forEach(queue ->{
				ResponseEntriesDTO entry = new ResponseEntriesDTO();
				entry.setClientId(queue);
				entry.setQuantity(data.get(queue));
				Long waitTime = ChronoUnit.SECONDS.between(timeMap.get(queue), LocalDateTime.now());
				LocalTime tod = LocalTime.ofSecondOfDay(waitTime);
				entry.setWaitingTime(tod.toString());
				response.add(entry);
			});
			if(response.isEmpty()) {
				return new ResponseEntity<> ("Currently no pending orders.Kindly,check after sometime.",HttpStatus.OK);
			}
			return new ResponseEntity<> (response,HttpStatus.OK);
		}

		@Override
		public ResponseEntity<?> retrieveNextOrder() {
			// TODO Auto-generated method stub
			if(pQueue.isEmpty()) {
				return new ResponseEntity<> ("No more orders.Kindly,check after sometime",HttpStatus.OK);
			}
			String value = pQueue.stream().findFirst().get();
			ResponseEntriesDTO entry = new ResponseEntriesDTO();
			entry.setClientId(value);
			entry.setQuantity(data.get(value));
			Long waitTime = ChronoUnit.SECONDS.between(timeMap.get(value), LocalDateTime.now());
			LocalTime tod = LocalTime.ofSecondOfDay(waitTime);
			entry.setWaitingTime(tod.toString());
			return new ResponseEntity<> (entry,HttpStatus.OK);
		}

		@Override
		public ResponseEntity<?> cancelOrder(String ClientId) {
			if(pQueue.contains(ClientId)) {
				pQueue.remove(ClientId);
				RemovefromMap(ClientId);
				return new ResponseEntity<> ("Order has been cancelled successfully",HttpStatus.OK) ;
			}
			else {
				return new ResponseEntity<> ("No orders have been placed under this id",HttpStatus.BAD_REQUEST) ;
			}
		}

		void InsertIntoMap(String clientId, String quantity){
			timeMap.put(clientId, LocalDateTime.now()) ;
			data.put(clientId, quantity);
		}

		void RemovefromMap(String clientId){
			data.remove(clientId);
		}

		void Removefromqueue(String ctid){
			RemovefromMap(ctid); 
		}

	}
