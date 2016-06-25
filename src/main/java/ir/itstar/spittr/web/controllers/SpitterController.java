package ir.itstar.spittr.web.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ir.itstar.spittr.data.Spitter;
import ir.itstar.spittr.data.SpitterRepository;

@Controller
@RequestMapping("/spitter")
public class SpitterController {
	
	
	private SpitterRepository spitterRepository;
	
	@Autowired
	public SpitterController(SpitterRepository spitterRepository){
		this.spitterRepository = spitterRepository;
	}
	
	@RequestMapping(value="/register" , method=RequestMethod.GET)
	public String showRegisterForm(Model model){
		model.addAttribute("spitter", new Spitter());
		return "registerForm";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	  public String processRegistration(
	    
		
		@Valid Spitter spitter, 
	      Errors errors) {
	    if (errors.hasErrors()) {
	      return "registerForm";
	    }
	    
	    spitterRepository.save(spitter);
	    return "redirect:/spitter/" + spitter.getUsername();
	}
	
	@RequestMapping(value="/{username}",method=RequestMethod.GET)
	public String showSpitterProfile(@PathVariable String username,Model model){
		Spitter spitter = spitterRepository.findeByUsername(username);
		model.addAttribute("spitter", spitter);
		return "profile";
	}
	
}
