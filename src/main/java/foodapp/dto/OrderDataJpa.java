package foodapp.dto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import foodapp.model.Order;

@Component
public interface OrderDataJpa extends JpaRepository<Order, Integer>{

}
