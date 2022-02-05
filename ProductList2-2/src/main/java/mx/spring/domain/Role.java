package mx.spring.domain;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import javax.validation.constraints.NotEmpty;


@Data
@Entity
@Table(name="ROLE")
public class Role implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long ROLE_ID;
	
	@NotEmpty
	private String NAME;
	
}
