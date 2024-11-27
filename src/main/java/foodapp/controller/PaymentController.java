package foodapp.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import foodapp.dto.ProductResponse;
import foodapp.model.Order;
import foodapp.service.PaymentService;
import foodapp.service.PaymentServiceImplementation;

@RestController
@RequestMapping("/payment")
public class PaymentController {
	PaymentServiceImplementation service;
	Order order;
	public PaymentController(PaymentServiceImplementation service,Order order) {
		this.service = service;
		this.order = order;
	}
	
	@CrossOrigin(origins = "http://localhost:5173")
	@PostMapping("/order")
	public ProductResponse createPaymentLink(@RequestBody Order order) {
		this.order = order;
		return service.createPaymentLink(order);
	}
	
	@GetMapping("/success/*")
	public String success() {
		service.addOrderJpa(order);
		return "Payment done successfully!";
	}
	
	@GetMapping("/cancel/*")
	public String cancel() {
		return "Go to home page ";
	}
}
