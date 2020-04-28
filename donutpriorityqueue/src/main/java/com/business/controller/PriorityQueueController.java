package com.business.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.business.service.PriorityQueueService;

@RestController
public class PriorityQueueController {
	
    @Autowired
	private PriorityQueueService priorityQueueService;
    
    @GetMapping("/placeOrder")
    public ResponseEntity<?> placeOrder(@RequestParam String ClientId,@RequestParam String quantity) {
		return priorityQueueService.placeOrder(ClientId, quantity);
	}
    
    @GetMapping("/checkOrderStatus")
    public ResponseEntity<?> checkOrderStatus(@RequestParam String ClientId) {
		return priorityQueueService.checkOrderStatus(ClientId);
	}
    
    @GetMapping("/checkEntries")
    public ResponseEntity<?> checkEntries() {
		return priorityQueueService.checkEntries();
	}
    
    @GetMapping("/retrieveNextOrders")
    public ResponseEntity<?> retrieveNextOrder() {
    	return priorityQueueService.retrieveNextOrder();	
    }
    
    @GetMapping("/cancelOrder")
    public ResponseEntity<?> cancelOrder(@RequestParam String ClientId) {
    	return priorityQueueService.cancelOrder(ClientId);
    }
}
