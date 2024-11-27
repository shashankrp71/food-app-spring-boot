package foodapp.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

import foodapp.dto.ProductResponse;
import foodapp.model.Order;

@Service
public class PaymentServiceImplementation implements PaymentService {

	@Value("${stripe.secretKey}")
	private String secretKey;
	private ProductResponse productResponse;
	
	public PaymentServiceImplementation(ProductResponse productResponse) {
		this.productResponse = productResponse;
	}

	@Override
	public ProductResponse createPaymentLink(Order order) {
		Stripe.apiKey = secretKey;
		// insert product name
		System.out.println(secretKey);
		SessionCreateParams.LineItem.PriceData.ProductData productData = SessionCreateParams.LineItem.PriceData.ProductData.builder()
				.setName(order.getProductName())
				.build();
		
		// insert unit price ,productdata and currency
		SessionCreateParams.LineItem.PriceData priceData = SessionCreateParams.LineItem.PriceData.builder()
				.setProductData(productData)
				.setCurrency("INR")
				.setUnitAmount(order.getAmount()*100)
				.build();

		// insert quantity and pricedata
		SessionCreateParams.LineItem lineItem = SessionCreateParams.LineItem.builder()
				.setPriceData(priceData)
				.setQuantity(order.getQuantity())
				.build();
		
		// creaete
		SessionCreateParams params = SessionCreateParams.builder()
				.addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
				.setMode(SessionCreateParams.Mode.PAYMENT)
				.addLineItem(lineItem)
				.setSuccessUrl("http://localhost:8080/payment/success/"+order.getOrderId())
				.setCancelUrl("http://localhost:8080/payment/cancel/"+order.getOrderId())
				.build();
		Session session = null;
		try {
			session = Session.create(params);
			productResponse.setPaymentUrl(session.getUrl());
			
		} catch (StripeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return productResponse;
	}

}
