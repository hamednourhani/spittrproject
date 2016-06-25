package ir.itstar.spittr.test.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import ir.itstar.spittr.web.controllers.HomeController;


public class HomeControllerTest {

	HomeController controller = new HomeController();

	@Test
	public void HomeControllerMustNotNull(){
				assertNotNull(controller);
	}
	
	@Test
	public void TheViewNameMUSTBeEQUAL(){
		assertEquals("home", controller.home());
	}
	
	@Test
	public void theViewNAMEMUSTBeEqualMockTest() throws Exception{
		HomeController hcontroller = new HomeController();
		MockMvc mockMvc= standaloneSetup(hcontroller).build();
		mockMvc.perform(get("/")).andExpect(view().name("home"));
	}
}
