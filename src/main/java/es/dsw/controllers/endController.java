package es.dsw.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes({"reservation", "sessionData", "client"})
public class endController {
	@GetMapping(value= {"/end"})
	public String end () {
		
		return "views/end";
	}
}
