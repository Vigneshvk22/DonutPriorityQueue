package com.business.service;
import org.springframework.http.ResponseEntity;

public interface PriorityQueueService {
   	
	public ResponseEntity<?> placeOrder(String ClientId,String quantity);
	
	public ResponseEntity<?> checkOrderStatus(String ClientId);
	
	public ResponseEntity<?> checkEntries();
	
	public ResponseEntity<?> retrieveNextOrder();
	
	public ResponseEntity<?> cancelOrder(String ClientId);
	   
}