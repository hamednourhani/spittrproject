package ir.itstar.spittr.test.controllers;

import static org.hamcrest.Matchers.hasProperty;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import ir.itstar.spittr.data.Spitter;
import ir.itstar.spittr.data.SpitterRepository;
import ir.itstar.spittr.web.controllers.SpitterController;


public class SpitterControllerTest {
	
	private SpitterController spitterController;
	private  MockMvc spMockMvc; 
	private SpitterRepository mockRepository;
	
	
	@Before
	public void setup(){
		mockRepository = mock(SpitterRepository.class);
					
		spitterController = new SpitterController(mockRepository);
		spMockMvc = standaloneSetup(spitterController).build();
		
	}
	
	@Test
	public void registerFromViewMustBe() throws Exception{
		spMockMvc.perform(get("/spitter/register"))
		.andExpect(view().name("registerForm"));
	}
	
	@Test
	public void shouldProcessRegistration() throws Exception{
		SpitterRepository mockRepository = mock(SpitterRepository.class);
	    Spitter unsaved = new Spitter("jbauer", "24hours", "Jack", "Bauer","info@example.com");
	    Spitter saved = new Spitter(24L, "jbauer", "24hours", "Jack", "Bauer","info@example.com");
	    when(mockRepository.save(unsaved)).thenReturn(saved);
	    
	    SpitterController controller = new SpitterController(mockRepository);
	    MockMvc mockMvc = standaloneSetup(controller).build();

	    mockMvc.perform(post("/spitter/register")
	           .param("firstName", "Jack")
	           .param("lastName", "Bauer")
	           .param("username", "jbauer")
	           .param("password", "24hours")
	    		.param("email","info@example.com"))
	   .andExpect(redirectedUrl("/spitter/jbauer"));
	    //.andExpect(redirectedUrl("/spitter/jbauer?spitterId=24"))
//	           .andExpect(model().attributeHasNoErrors("spitter"));
	    	
	}
	
	@Test
	public void shouldBeReturnToRegisterPage() throws Exception{
		SpitterRepository mockRepository = mock(SpitterRepository.class);
	    Spitter unsaved = new Spitter("jbauer", "24hours", "Jack", "Bauer","info@example.com");
	    Spitter saved = new Spitter(24L, "jbauer", "24hours", "Jack", "Bauer","info@example.com");
	    when(mockRepository.save(unsaved)).thenReturn(saved);
	    
	    SpitterController controller = new SpitterController(mockRepository);
	    MockMvc mockMvc = standaloneSetup(controller).build();

	    mockMvc.perform(post("/spitter/register")
	           .param("firstName", "Jack")
	           .param("lastName", "Bauer")
	           .param("username", "jbauer")
	           .param("password","")
	           .param("email", "info@example.com"))
	    		.andExpect(view().name("registerForm"))
	    		.andExpect(model().attributeHasErrors("spitter"));
	}
	
	@Test
	public void shouldShowUserProfile() throws Exception{
		Spitter hamed = new Spitter("hamednourhani", "9124134625","hamed", "nourhani","info@itstar.ir");
		when(mockRepository.findeByUsername("hamednourhani")).thenReturn(hamed);
		
		spMockMvc.perform(get("/spitter/hamednourhani"))
		.andExpect(view().name("profile"))
		.andExpect(model().attributeExists("spitter"))
		.andExpect(model().attribute("spitter", hasProperty("firstName", CoreMatchers.is("hamed"))))
		.andExpect(model().attribute("spitter", hasProperty("email", CoreMatchers.is("info@itstar.ir"))));
		
		verify(mockRepository,atLeastOnce()).findeByUsername("hamednourhani");
		
	}
	
	

	@After
	public void destory(){
		spitterController = null;
		spMockMvc = null;
	}
	
}
