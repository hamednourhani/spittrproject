package ir.itstar.spittr.test.controllers;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import ir.itstar.spittr.data.Spittle;
import ir.itstar.spittr.data.SpittleRepository;
import ir.itstar.spittr.web.controllers.SpittleController;


public class SpittleControllerTest {
	private InternalResourceViewResolver viewResolver;
	
	private SpittleRepository spittleRepositoryMock;
	private MockMvc spMockMvc;
	private SpittleController spittleController;
	
	@Before
    public void setup() {
      viewResolver   = new InternalResourceViewResolver();
      viewResolver.setPrefix("/WEB-INF/views/");
      viewResolver.setSuffix(".jsp");
      viewResolver.setExposeContextBeansAsAttributes(true);
      
      spittleRepositoryMock = mock(SpittleRepository.class);
     
      
      spittleController = new SpittleController(spittleRepositoryMock);
      spMockMvc = standaloneSetup(spittleController)
//				.setSingleView(new InternalResourceView("/WEB-INF/views/spittles.jsp"))
				.setViewResolvers(viewResolver)
				.build();
    }
	
	@Test
	public void shouldShowRecentSpittles() throws Exception{
		List<Spittle> expectedSpittlesList = createSpittlesList(20);
		when(spittleRepositoryMock.findSpittles(Long.MAX_VALUE, 20)).thenReturn(expectedSpittlesList);
			
		spMockMvc.perform(get("/spittles"))
			.andExpect(view().name("spittles"))
			.andExpect(model().attributeExists("spittleList"))
			.andExpect(model().attribute("spittleList", hasItems(expectedSpittlesList.toArray())));
	}
	
	@Test
	public void shouldShowSpecifiedNumberOfSpittles() throws Exception{
		List<Spittle> expectedSpittlesList = createSpittlesList(50);
		when(spittleRepositoryMock.findSpittles(238900, 50)).thenReturn(expectedSpittlesList);
				
		spMockMvc.perform(get("/spittles?max=238900&count=50"))
			.andExpect(view().name("spittles"))
			.andExpect(model().attributeExists("spittleList"))
			.andExpect(model().attribute("spittleList", hasItems(expectedSpittlesList.toArray())));
	}
	
	@Test
	public void testSingleSpittle() throws Exception {
		Spittle expectedSpittle = new Spittle("Hello", new Date());
		when( spittleRepositoryMock.findOne(12345)).thenReturn(expectedSpittle);
			
		spMockMvc.perform(get("/spittles/12345"))
			.andExpect(view().name("spittle"))
			.andExpect(model().attributeExists("spittle"))
			.andExpect(model().attribute("spittle", expectedSpittle));
	}
	
	@After
	public void destory(){
		viewResolver = null;
		spittleRepositoryMock = null;
		spittleController= null;
		spMockMvc= null;
		
	}
	
	public List<Spittle> createSpittlesList(int count){
		List<Spittle> spittlesList = new ArrayList<Spittle>();
		for(int i=0; i<count ; i++){
			spittlesList.add(new Spittle("Spittle No."+i, new Date()));
		}
		return spittlesList;
	}

}
