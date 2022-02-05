package mx.spring.web;

import java.util.List;
import mx.spring.domain.PromEve;
import org.springframework.ui.Model;
import mx.spring.service.IPromEveService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@Controller
public class ControllerPromEve {

	@Autowired
	private IPromEveService promEveService; 
	
	@GetMapping("/promotional-events")
	public String promEve(Model model) {
		List<PromEve> promEves = (List<PromEve>) promEveService.listPromEve();
		model.addAttribute("promEves", promEves);
		return "promotional-events";
	}
	@GetMapping("/add_pr")
	public String add_pr(PromEve promEve) {
		return "promotional-events"; 
	}
	@GetMapping("/edit_pr/{PROMOTION_EVENT_ID}")
	public String edit_pr(PromEve promEve, Model model) {
		promEve = promEveService.findPromEve(promEve);
		model.addAttribute("obj", promEve);
		return "promotional-events";
	}
	@PostMapping("/save_pr")
	public String save_pr(PromEve promEve) {
		promEveService.save(promEve);
		return "redirect:/promotional-events"; 
	}
	@GetMapping("/delete_pr")
	public String delete_pr(PromEve promEve) {
		promEveService.delete(promEve);
		log.info("PROMEVE->" + promEve);
		return "redirect:/promotional-events";
	}
	
}
