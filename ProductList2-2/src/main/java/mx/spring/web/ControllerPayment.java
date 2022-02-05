package mx.spring.web;


import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ControllerPayment {

	
	//go to payment page
	@GetMapping("/payment")  
	public String payment(Model model) {   
		return "payment";
	}	
}