package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
	
	OrderRepository repo;
    public MyController(OrderRepository repo) {
    	this.repo = repo;
    }
    @GetMapping("/name")
    public String sayMyName(@RequestParam("name")String name) {
        return "Something "+name;
    }
    
//    @PostConstruct
//    public void addSomeMethods() {
//    	repo.save(new Order(1L, "XYZ1"));
//    	
//    }
    
    
    
    
    @GetMapping("/orders")
    public Iterable<Order> getOrders() {
    	
    	return repo.findAll();
    }
    
    @RequestMapping(value="/order", method = {RequestMethod.POST, 
    		RequestMethod.PUT}, 
    		consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
    	System.out.println(order);
//    	System.out.println(repo);
    	Order newOrder = repo.save(order);
    	return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
    }
}
