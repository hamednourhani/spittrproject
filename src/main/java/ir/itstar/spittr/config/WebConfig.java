package ir.itstar.spittr.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages={"ir.itstar.spittr.web"})
public class WebConfig<TemplateResolver> extends WebMvcConfigurerAdapter{
	
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
		.addResourceHandler("/css/*")
		.addResourceLocations("/css/")
		.addResourceLocations("/images/*")
		.addResourceLocations("/images/");		
	} 
	
	@Bean
	@Primary
	public MessageSource messageSource(){
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		return messageSource;
	}
	
	@Bean
	public MessageSource reloadableMessageSource(){
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("file:///etc/spittr/messages");
		messageSource.setCacheSeconds(10);
		return messageSource;
	}
	
	
//	@Bean
//	public ViewResolver viewResolver(){
//		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//		resolver.setPrefix("/WEB-INF/views/");
//		resolver.setSuffix(".jsp");
//		resolver.setViewClass(JstlView.class);
//		resolver.setExposeContextBeansAsAttributes(true);
//		
//		return resolver;
//	}
	
	@Bean
	@Primary
	public ViewResolver tileViewResolver(){
		return new TilesViewResolver();
	}
	
	@Bean
	public TilesConfigurer tilesConfigurer(){
		TilesConfigurer tilesConfigurer = new TilesConfigurer();
		tilesConfigurer.setDefinitions(new String[]{
				"/WEB-INF/layout/tiles.xml",
				"/WEB-INF/views/**/tiles.xml"
		});
		tilesConfigurer.setCheckRefresh(true);
		return tilesConfigurer;
	}
	
//	@Bean
//	public MultipartResolver multipartResolver(){
//		return new StandardServletMultipartResolver();
//	}
//	
//	@Bean
//	public MultipartResolver jmMultipartResolver() throws MalformedURLException, IOException{
//		CommonsMultipartResolver cmr = new CommonsMultipartResolver();
//		cmr.setMaxInMemorySize(400000);
//		cmr.setUploadTempDir(new UrlResource("/tmp"));
//		return cmr;
//	}
//	
	
//	@Bean
//	public ViewResolver viewResolver(
//		SpringTemplateEngine templateEngine) {
//		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
//		viewResolver.setTemplateEngine(templateEngine);
//		return viewResolver;
//	}
//	
//	@Bean
//	public SpringTemplateEngine templateEngine(
//		ITemplateResolver templateResolver) {
//		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
//		templateEngine.setTemplateResolver((ITemplateResolver) templateResolver);
//		return templateEngine;
//	}
//	
//	@Bean
//	public ITemplateResolver templateResolver(ServletContext servletContext) {
//		ServletContextTemplateResolver templateResolver =
//		new ServletContextTemplateResolver(servletContext);
//		templateResolver.setPrefix("/WEB-INF/templates/");
//		templateResolver.setSuffix(".html");
//		templateResolver.setTemplateMode("HTML5");
//		return templateResolver;
//	}
}
