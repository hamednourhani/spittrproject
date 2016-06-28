package ir.itstar.spittr.config;



import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import ir.itstar.spittr.data.Spitter;
import ir.itstar.spittr.data.Spittle;

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
	
	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource){
		return new JdbcTemplate(dataSource);
	}
	
	@Bean
	@Primary
	public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource){
		return new NamedParameterJdbcTemplate(dataSource);
	}
	
	@Bean
	@Qualifier("localXml")
	public LocalSessionFactoryBean localSessionFactory(DataSource dataSource){
		LocalSessionFactoryBean lsfb = new LocalSessionFactoryBean();
		lsfb.setDataSource(dataSource);
		lsfb.setMappingResources(new String[]{"Spittr.hbm.xml"});
		Properties prop = new Properties();
		prop.setProperty("dialect", "org.hibernate.dialect.H2Dialect");
		lsfb.setHibernateProperties(prop);
		return lsfb;
		
	}
	
	@Bean
	public AnnotationSessionFactoryBean sessionFactory(DataSource dataSource){
		AnnotationSessionFactoryBean asfb = new AnnotationSessionFactoryBean();
		asfb.setDataSource(dataSource);
		asfb.setAnnotatedPackages(new String[]{"ir.itstar.spittr.data"});
		asfb.setAnnotatedClasses(new Class<?>[] {Spitter.class,Spittle.class});
		Properties prop = new Properties();
		prop.setProperty("dialect", "org.hibernate.dialect.H2Dialect");
		return asfb;
	}
	
	//if you not use HibernateTemplate must configur a 
	//BeanPostProccesor to Translating SQL and other Exceptions
	//to DAO uncheked Exceptions 
	@Bean
	public BeanPostProcessor persistenceTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}
	
}
