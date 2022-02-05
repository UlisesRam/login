package mx.spring.web;

import java.util.*;
import org.springframework.web.servlet.i18n.*;
import org.springframework.context.annotation.*;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebConfig implements WebMvcConfigurer{

	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver slr = new SessionLocaleResolver();
		slr.setDefaultLocale(new Locale("en"));
		return slr;
	}
	
	//This interceptor changes the language dynamically
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
		lci.setParamName("lang");
		return lci;
	}
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
	}
	
	/* Mapping de la URL por default, cuando agregamos seguridad ya no podemos acceder directamente al path 
	 * agergamos el metodo addViewControllers */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// agregamos el path por default -> raiz
		registry.addViewController("/home")
		// nombramos esta vista como user 
		.setViewName("home");
		
		/*se necesita ya haber hecho login para visualizar esta pagina de inicio por lo que se tiene que
		 * agregar la pagina de login que hicimos
		 * */
		
		//agregamos el mapeo de la vista del login, sin necesidad de pasar por un controlador
		registry.addViewController("/login"); //.setViewName("login");
		
		//Mapping layout errors with addViewController which doesn't need go througth by the controller
		//registry.addViewController("/errors/403").setViewName("/errors/403");
	}
	
}
