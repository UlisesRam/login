package mx.spring.web;

import org.springframework.context.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired //Injectamos el servicio de UserDetailsService (package Service) y
	//definimos la variable userDetailsService -> interface que ocupara spring security para recuperar el objeto de tipo user.
	private UserDetailsService userDetailsService; 
	
/*
	//Definimos el tipo de encrypcion, de define el bean para que spring lo use como bean.
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	} 
*/
	
	@Autowired //Injecta un objecto de tipo AuthenticationManagerBuilder(propio de spring) a la spring factory
	//definimos el metodo configurerGlobal donde esta el objeto que se inyecta en automatico, asi que spring
	//busca una implementacion de AuthenticationManagerBuilder
	//por medio de la variable build le pasamos userDetailsService(instancia) y spring security usa 
	//el metodo loadUserByUsername(definido en UserService y es JPA)
	public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception {
		build.userDetailsService(userDetailsService);
	}
	
	//AUTHORIZATION
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests()
		// antMatchers -> indicar los paths a usar
		.antMatchers("/admin","/coupon","/promotional-events") //con /** restringimos sub-paths
		.hasRole("ADMIN") //indicamos el usuario que puede entrar a los paths de arriba
		
		.antMatchers("/home", "/product", "/payment" ) //indicamos quien puede acceder al path home
		//.hasAnyRole("USER", "ADMIN") -> si fueran mas usuarios
		.hasRole("USER") //SE INSERTA "ROLE_" EN AUTOMATICO
		
	//	indicamos cual va a ser la pagina de login en la configuracion de seguridad de spring
	.and()
		.formLogin() //configuracion del form-login personalizado que vamos a usar 
		.loginPage("/login")//indicamos el nombre de la pagina
	
	.and()	
	/* before using view with thymeleaf if the user was not authorized, 
	 * this access denied page was shown, we can add more error pages 
	 * like, 404, 500 etc */
		.exceptionHandling().accessDeniedPage("/errors/403")
		
		;
	}

}
