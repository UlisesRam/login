package mx.spring.service;

import java.util.*;
import mx.spring.dao.*;
import mx.spring.domain.Role;
import mx.spring.domain.User1;
//import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

  


//@Slf4j
@Service ("userDetailsService") // userDetailsService es clase de uso de spring security - no se debe cambiar el nombre
public class UserService implements UserDetailsService{
	
	@Autowired
	private IUserDAO userDAO; //interaccion con la BD
	
	@Override
	@Transactional (readOnly = true) //Los metodos de  las clases de servicio deben de ser transaccionales para que interactue con la DB.
	//recibimos el parametro de username del IUserDAO
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		
		
		//implementacion usando la clase DAO por eso se inyecta con autowired
		User1 user1 = userDAO.findByUsername(username); //recuperamos un usuario filtrado por el username
		//si no encoentramos al usuario arrojamos excepcion ->	
		if(user1 == null) {
			throw new UsernameNotFoundException(username + "User not authorized");
		}
		//si encontramos el usuario, recuperamos los "usertype" asociados al usuario
		//definimos la variable de tipo "GrantedAuthority" que va a recuperar los roles asociados al usuario
		List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
		
		//Recuperamos todos los roles del usuario
		for (Role role: user1.getRoles()) { 
			roles.add(new SimpleGrantedAuthority(role.getNAME()));
		}
			
			// debe de retornar un objeto de typo UserDetails con los elementos que se recuperaron
			//Este User es una clase de spring -> NO ES UNA CLASE QUE DEFINIMOS NOSOTROS,
			//Se utiliza en spring security y recibe el nombre el password y la variable roles de tipo GrantedAuthority
		return new User(user1.getUsername(), user1.getUSER_PASSWORD(),roles);
	}

}
