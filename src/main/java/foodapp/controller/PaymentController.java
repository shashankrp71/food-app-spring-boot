package foodapp.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
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
	public PaymentController(PaymentServiceImplementation service) {
		this.service = service;
	}
	
	@CrossOrigin(origins = "http://localhost:5173")
	@PostMapping("/order")
	public ProductResponse createPaymentLink(@RequestBody Order order) {
		System.out.println(order);
		return service.createPaymentLink(order);
	}
}
