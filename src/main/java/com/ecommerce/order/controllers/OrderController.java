package com.ecommerce.order.controllers;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.order.models.OrderDTO;
import com.ecommerce.order.service.OrderService;
import com.ecommerce.products.exception.InternalServerException;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/api/v1/order")
public class OrderController {

	private final static Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());

	@Resource
	private OrderService orderService;

	@PostMapping("/createOrder")
	public void addOrder(@RequestBody OrderDTO orderDTO) {
		LOGGER.info("create Order");
		try {
			orderService.addOrder(orderDTO);
			return;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			System.err.println(e.getStackTrace());
		}
		throw new InternalServerException();
	}

	@DeleteMapping("/deleteOrder")
	public void deleteOrder(@RequestParam("orderId") String orderId, @RequestParam("userEmail") String userEmail) {
		LOGGER.info("delete Order");
		try {
			orderService.deleteOrder(orderId, userEmail);
			return;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			System.err.println(e.getStackTrace());
		}
		throw new InternalServerException();
	}

	@GetMapping("/getAllOrdersByEmail/{emailId}")
	public ResponseEntity<List<OrderDTO>> getAllOrdersByEmail(@PathVariable String emailId) {
		LOGGER.info("Getting order by email");
		List<OrderDTO> productDTO = new ArrayList<OrderDTO>();
		try {
			productDTO = orderService.getAllOrdersByEmail(emailId);
			return new ResponseEntity<List<OrderDTO>>(productDTO, HttpStatus.OK);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			System.err.println(e.getStackTrace());
		}
		throw new InternalServerException();
	}

}
