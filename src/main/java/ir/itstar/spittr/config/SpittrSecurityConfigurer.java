package ir.itstar.spittr.config;



import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SpittrSecurityConfigurer extends WebSecurityConfigurerAdapter {
	
	//@Autowired
	//DataSource dataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		//auth.jdbcAuthentication().dataSource(dataSource);
		auth.inMemoryAuthentication()
		.withUser("user").password("password").roles("USER").and()
		.withUser("admin").password("admin").roles("USER","ADMIN");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.formLogin().loginPage("/login").and()
		.logout().logoutSuccessUrl("/").and()
		.httpBasic().realmName("Spittr").and()
		.rememberMe().and()
		.csrf().and()
		.authorizeRequests()
		.antMatchers("/spitter/profile/**").authenticated()
		.anyRequest().permitAll().and()
		.requiresChannel().antMatchers("/spitter/profile/**").requiresSecure()
		.anyRequest().requiresInsecure();
		
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		// TODO Auto-generated method stub
		super.configure(web);
	}
}
