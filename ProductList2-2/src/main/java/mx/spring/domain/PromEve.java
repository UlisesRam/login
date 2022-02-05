package mx.spring.domain;

import java.util.*;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import lombok.Data;

@Data
@Entity
@Table(name="promotionevents")
public class PromEve implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long PROMOTION_EVENT_ID;
	
	@NotEmpty
	private String PROM_NAME;
	
	@NotEmpty
	private Date STARTDATE;	
	
	@NotEmpty
	private Date ENDDATE;
	
	@NotEmpty
	private String PROM_DESCRIPTION;
	
	@NotEmpty
	private String USER_TYPE;
	
	@NotEmpty
	private String PROMSTATUS;
	
	
}
