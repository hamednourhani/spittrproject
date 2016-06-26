package ir.itstar.spittr.web.advises;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import ir.itstar.spittr.exceptions.DuplicateSpittleException;
import ir.itstar.spittr.exceptions.SpittleNotFoundException;

@Component
@ControllerAdvice
public class AppWideExceptionHandler {
	
	@ExceptionHandler(SpittleNotFoundException.class)
	public String SpittleNotFoundHandler(){
		return "error/not-found";
	}
	
	@ExceptionHandler(DuplicateSpittleException.class)
	public String DuplicateSpittleExceptionHandler(){
		return "error/duplicated";
	}
	
	
	//here is two other annotaiions that advise methods
	//can be defind with to advise controller methods
	//@InitBinder
	//@ModelAttribute
	
}
