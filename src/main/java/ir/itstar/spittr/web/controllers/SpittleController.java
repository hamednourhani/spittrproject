package ir.itstar.spittr.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ir.itstar.spittr.data.Spittle;
import ir.itstar.spittr.data.SpittleRepository;

@Controller
@RequestMapping(value="/spittles")
public class SpittleController {
	
	private SpittleRepository spittleRepository;
	
	@Autowired
	public SpittleController(SpittleRepository spittleRepository) {
		this.spittleRepository = spittleRepository;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Spittle> spittles(
			@RequestParam(value="max",defaultValue= ""+Long.MAX_VALUE+"") long max ,
			@RequestParam(value="count",defaultValue="20") int count
			){
		
		return spittleRepository.findSpittles(max, count);
		
	}
	
	@RequestMapping(value="/{spittleId}",method=RequestMethod.GET)
	public String spittle(@PathVariable int spittleId, Model model){
		Spittle spittle = spittleRepository.findOne(spittleId);
		if( spittle == null){
			//throw new SpittleNotFoundException();
		}
		model.addAttribute(spittle);
		return "spittle";
	}
	
//	AppWideExceptionHandler class will do Exception handeling for all 
//	methods in app that annotated by @RequestMapping by default
//	
//	@ExceptionHandler(value = SpittleNotFoundException.class)
//	public String spittleErrorHandeling(){
//		return "error/duplicated";
//	}
}
