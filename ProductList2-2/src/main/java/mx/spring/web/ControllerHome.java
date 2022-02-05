package mx.spring.web;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ControllerHome {
	
	//go to home page
	@GetMapping("/home")  
	public String home(Model model) {   
		return "home";
	}	
}
