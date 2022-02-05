
package mx.spring.web;

import java.util.*;
import mx.spring.domain.*;
import mx.spring.service.*;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
//import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

//@Slf4j
@Controller
public class ControllerProduct {

	@Autowired
	private IProductService productService; 
	
	@GetMapping("/product")  
	public String product(Model model) {   
		List<Product> products = (List<Product>) productService.listProduct();
		model.addAttribute("products", products); 
		return "product";
	}
	
	
	
}
