package mx.spring.dao;

import mx.spring.domain.User1;
import org.springframework.data.jpa.repository.JpaRepository;

//import lombok.extern.slf4j.Slf4j;

// ------>> usa objetos de tipo usuario, la llave primaria es de tipo long
//---jpa->> crea la los metodos de la interface

/* Se define el metodo findByUsername que devuelve un objeto de tipo User1. findByUserName es parte de la seguridad de spring
 * se debe de escribir tal cual.
 * 
 * Se define el parametro de "username"; es decir una vez que se proporcione el "username" spring va a recuperar
 * el objeto de tipo "User1" filtrado por el "username"
 * */

public interface IUserDAO extends JpaRepository<User1, Long>{
	User1 findByUsername(String username);
	
}
