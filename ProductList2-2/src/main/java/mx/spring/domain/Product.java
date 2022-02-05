package mx.spring.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import lombok.Data;

@Data
@Entity
@Table(name = "products")
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	/*
	 * @SequenceGenerator(name="seq",sequenceName="product_id_seq")
	 * @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
	 * @GeneratedValue(strategy = GenerationType.IDENTITY)
	 */

	// PrimaryKey mapping
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long product_id;

	@NotEmpty
	private String pname;

	@NotEmpty
	private int pcategory;

	@NotEmpty
	private float price;

	@NotEmpty
	private String pdescription;

	@NotEmpty
	private String pimage;

}
