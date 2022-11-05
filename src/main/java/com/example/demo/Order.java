package com.example.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@Entity
@Table(name="my_order")
//@JsonComponent
//@Data
public class Order {
	@Id
//	@NotNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("orderId")
	Long orderId;
	@JsonProperty("name")
	@Column(nullable = false)
	String name;
	
//	public Order() {
//		
//	}
//	
//	public Order(Long id) {
//		this.orderId = id;
//	}
//	public Order(Long id, String name) {
//		this.orderId = id;
//		this.name = name;
//	}
//
//	public Long getId() {
//		return orderId;
//	}
//
//	public void setId(Long id) {
//		this.orderId = id;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
	@Override
	public String toString() {
		return "Order [id=" + orderId + ", name=" + name + "]";
	}

}
