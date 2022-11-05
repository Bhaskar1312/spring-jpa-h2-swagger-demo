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
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

	@Operation(summary ="Get All orders")
	@ApiResponses(value = {
		@ApiResponse(responseCode="200", description="all orders", 
				content= {@Content(mediaType="application/json",
				schema=@Schema(implementation=Order[].class))}),
	})
	@GetMapping("/orders")
	public /* @ResponseBody */ Iterable<Order> getOrders() { //as RestController -> Controller + ResponseBody

		return repo.findAll();
	}
	
	@Operation(summary ="Get a order by id")
	@ApiResponses(value = {
		@ApiResponse(responseCode="200", description="order found", 
				content= {@Content(mediaType="application/json",
				schema=@Schema(implementation=Order.class))}),
		@ApiResponse(responseCode="400", description="Invalid id", 
		content= {@Content}),
		@ApiResponse(responseCode="404", description="Order not found", 
		content= {@Content}),
	})
	@GetMapping("/order/{id}")
	public Order getOrderById(Long id) throws OrderNotFoundException {
		Optional<Order> order = repo.findById(id);
		if(order.isPresent()) {
			return order.get();
		}
		throw new OrderNotFoundException("order with "+id+" is not found");//order or book not found exception
	}

	
	@Operation(summary ="Create/Update a order with id(opt for create) and name")
	@ApiResponses(value = {
		@ApiResponse(responseCode="201", description="order created", 
				content= {@Content(mediaType="application/json",
				schema=@Schema(implementation=Order.class))}),
		@ApiResponse(responseCode="400", description="Invalid id", 
		content= {@Content}),
	})
	@RequestMapping(value = "/order", 
			method = { RequestMethod.POST, RequestMethod.PUT },
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Order> createOrder(@RequestBody Order order) {
		logger.info(order.toString());
//    	logger.info(repo);
		Order newOrder = repo.save(order);
		return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
	}
	
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	
	@Operation(summary ="Delete a order by id")
	@ApiResponses(value = {
		@ApiResponse(responseCode="200", description="order deleted", 
				content= {@Content(mediaType="application/json",
				schema=@Schema(implementation=Order.class))}),
		@ApiResponse(responseCode="404", description="Order not found", 
		content= {@Content}),
	})
	@DeleteMapping("/order")
	public ResponseEntity<Order> deleteOrder(@RequestParam("id") Long id) throws OrderNotFoundException {
		Optional<Order> order = repo.findById(id);
//		logger.info(order.get()+"");
		if (order.isEmpty()) {
			throw new OrderNotFoundException("order with "+ id + " is not found");
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		logger.info(order.get().toString());
		repo.deleteById(id);
		return new ResponseEntity<>(order.get(), HttpStatus.OK);
	}
}
