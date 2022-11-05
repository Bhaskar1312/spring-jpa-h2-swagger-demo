package com.example.demo;

import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class MyController {

	OrderRepository repo;

	public MyController(OrderRepository repo) {
		this.repo = repo;
	}

	@GetMapping("/name")
	public String sayMyName(@RequestParam("name") String name) {
		return "Something " + name;
	}

//    @PostConstruct
//    public void addSomeMethods() {
//    	repo.save(new Order(1L, "XYZ1"));
//    	
//    }

	@GetMapping("/orders")
	
	public /* @ResponseBody */ Iterable<Order> getOrders() { //as RestController -> Controller + ResponseBody

		return repo.findAll();
	}

	@RequestMapping(value = "/order", 
			method = { RequestMethod.POST, RequestMethod.PUT },
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Order> createOrder(@RequestBody Order order) {
		System.out.println(order);
//    	System.out.println(repo);
		Order newOrder = repo.save(order);
		return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
	}
	
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Transactional
	@DeleteMapping("/order")
	public ResponseEntity<Order> deleteOrder(@RequestParam("id") Long id) {
		Optional<Order> order = repo.findById(id);
//		logger.info(order.get()+"");
		if (order.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		logger.info(order.get().toString());
		repo.deleteById(id);
		return new ResponseEntity<>(order.get(), HttpStatus.OK);
	}
}
