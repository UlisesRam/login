package mx.spring.web;

import java.util.*;
import mx.spring.domain.*;
import mx.spring.service.*;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@Controller
public class ControllerAdmin {

	@Autowired
	private IProductService productService; 
	
	//Mapping list
	@GetMapping("/admin")  
	public String admin(Model model) {   
		List<Product> products = (List<Product>) productService.listProduct();
		model.addAttribute("products", products); 
		return "admin";
	}
	//Mapping add - Create new product
	@GetMapping("/add")
	public String add(Product product) {
		return "admin";
	}
	//Mapping edit	
	@GetMapping("/edit/{product_id}")
	public String edit(Product product, Model model) {
		product = productService.findProduct(product);
		model.addAttribute("product", product);
		log.info("Product->" + product);
		return "admin"; 
	}
//	//Mapping edit	
//	@GetMapping("/edit/{product_id}")
//	public String edit (@PathVariable("product_id")Long product_id, Model model) {
//		model.addAttribute("product", productService.editProduct(product_id));
//		return "admin"; 
//	}
	//Mapping save
	@PostMapping("/save")
	public String save(Product product) {   
		productService.save(product);
		return "redirect:/admin"; 
	}
	//Mapping delete
	@GetMapping("/delete")
	public String delete(Product product) {
		productService.delete(product);
		return "redirect:/admin"; 
	}
	
}

	