package ir.itstar.spittr.web.controllers;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
//	    @RequestPart("profilePicture") MultipartFile profilePicture,
//		@RequestPart("profilePicture") Part profilePicture,
		
		@Valid Spitter spitter, 
	      Errors errors,RedirectAttributes model) throws IOException {
		
		
	    if (errors.hasErrors()) {
	      return "registerForm";
	    }
	    
	    // Multipart Handeling by Part and MultiPartFile Interfaces
	    // with the help of @RequestPart annotaion
	    // we must add a MultipartResolver Bean to WebConfig to
	    //	Help DispatcherServlet deligting multipart resolving 
	    // MultiPartResolver No need for Part Interface
	    // two Implemention of MultiPartResolver is :
	    //1 - commonMultiPart Resolver 
	    //2 - StandardMultiPatResolver (you Must config this class in
	    // in DispacthcerServlet setMultiPartmethod by passing an instance of 
	    // MultiPartConfigElemnt)
	    //((MultipartFile) profilePicture).transferTo(
//	    		new File("/data/spittr/" + ((MultipartFile) profilePicture).getOriginalFilename()));
//	    
//	    ((Part) profilePicture).write("/data/spittr/" +
//	    		( profilePicture).getOriginalFilename());
	    
	    //Spitter savedSpitter = spitterRepository.save(spitter);
	    model.addAttribute("username", spitter.getUsername());
	     model.addFlashAttribute("spitter",spitter);
	   // model.addAttribute("spitterId", savedSpitter.getId());
	    return "redirect:/spitter/{username}";
	    
	}
	
	@RequestMapping(value="/{username}",method=RequestMethod.GET)
	public String showSpitterProfile(@PathVariable String username,Model model){
		if(!model.containsAttribute("spitter")){
			Spitter spitter = spitterRepository.findeByUsername(username);
			model.addAttribute("spitter", spitter);
		}
		return "profile";
	}
	
}
