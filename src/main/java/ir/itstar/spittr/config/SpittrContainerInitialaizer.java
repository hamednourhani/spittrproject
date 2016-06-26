package ir.itstar.spittr.config;

import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class SpittrContainerInitialaizer extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return new Class<?>[]{RootConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return new Class<?>[]{WebConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return new String[]{"/"};
	}
	
	@Override
	protected void customizeRegistration(Dynamic registration){
		
		//To Handleing MultiPart of a request(file upload) we need a MultipartResolver
		//Bean to help the dispachurServlet and for Standard 
		
		//registration.setMultipartConfig(new MultipartConfigElement("c:/tmp/"));
		
	}
	
	

}
