package mx.spring.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import lombok.Data;

@Data
@Entity
@Table(name = "coupons")
public class Coupon implements Serializable {

	private static final long serialVersionUID = 1L;

	// chech database structure if some value have @OneToMany relation 

	
	
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long COUPONS_ID;

	@NotEmpty
	private String CO_NAME;

	@NotEmpty
	private float CO_DISCOUNT;

	@NotEmpty
	private String TYPE_COUPON;

	@NotEmpty
	private int PROM_EVENT;

	@NotEmpty
	private int COUPCATEGORY;

}
