package foodapp.model;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Repository
@Entity
// donot mention the keywords like order in sql table name it gives error
@Table(name ="orderdetails")
public class Order {
	@Id
	@Column
	private int orderId;
	@Column
	private String productName;
	@Column
	private long quantity;
	@Column
	private long amount;
}
