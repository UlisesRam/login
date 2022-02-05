package mx.spring.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Entity
@Table(name="users")
public class User1 implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long USER_ID;
	
	@NotEmpty
	private String username;
	private String USER_EMAIL;
	private int USER_PHONE;
	private String USER_PASSWORD;
	private String USER_TYPE;
	
	@OneToMany(cascade=CascadeType.ALL)
	@Column(name="USER_ID")
	private List<Role> roles;
	
	
	//@JoinColumn(name="USER_ID")
	//@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	//@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	//@OneToMany(mappedBy = "users")
	//@JoinTable(name="ROLE")
	//@OneToMany
}
