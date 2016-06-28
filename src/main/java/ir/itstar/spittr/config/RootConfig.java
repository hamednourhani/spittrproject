package ir.itstar.spittr.config;



import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages="ir.itstar.spittr",
			excludeFilters={
					@Filter(type=FilterType.ANNOTATION,value=EnableWebMvc.class)
			}
	)
public class RootConfig {
	
	//make a DataSouce by Jndi lookup on the java app server
	//@Bean
	public JndiObjectFactoryBean jndiDataSource(){
		JndiObjectFactoryBean jndiObjectFactoryBean = new JndiObjectFactoryBean();
		jndiObjectFactoryBean.setJndiName("/jdbc/SpittrDB");
		jndiObjectFactoryBean.setResourceRef(true);
		jndiObjectFactoryBean.setProxyInterface(javax.sql.DataSource.class);
		return jndiObjectFactoryBean;
	}
	
	//create a connection pool by org.apache.commons.dbcp2.BasicDataSouce
	//@Bean 
	public BasicDataSource basicDataSource(){
		BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setDriverClassName("org.h2.Driver");
		basicDataSource.setUrl("jdbc:h2:tcp://localhost/~/spitter");
		basicDataSource.setUsername("sa");
		basicDataSource.setPassword("");
		basicDataSource.setInitialSize(5);
		basicDataSource.setMaxTotal(10);
		return basicDataSource;
	}
	
	//create a JDBC Driver-Base DataSouce by org.springframework.jdbc.datasouce package
	//DriverManagerDatasouce each request each connection
	//SimpleConnectionDataSource each request each connection
	//SingleConnectionDataSource One Connection for all.
	@Bean 
	@Profile("prod")
	public DataSource dataSource(){
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUsername("sa");
		dataSource.setPassword("");
		dataSource.setUrl("jdbc:h2:tcp://localhost/~/spittr");
		return dataSource;
	}
	
	@Bean
	@Profile("dev")
	public DataSource testDataSource(){
		return (DataSource) new EmbeddedDatabaseBuilder()
				.setType(EmbeddedDatabaseType.H2)
				.addScript("classpath:schema.sql")
				.addScript("classpath:test-data.sql").build();
	}
	
}
