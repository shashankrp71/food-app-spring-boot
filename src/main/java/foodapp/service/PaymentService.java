package foodapp.service;

import org.springframework.stereotype.Service;

import foodapp.dto.ProductResponse;
import foodapp.model.Order;

@Service
public interface PaymentService {
	public ProductResponse createPaymentLink(Order order);
	public void addOrderJpa(Order order);
}
