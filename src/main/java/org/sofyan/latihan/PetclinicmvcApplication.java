package org.sofyan.latihan;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Import;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

@SpringBootApplication
@Import({SecurityConfig.class,WebConfig.class})
@EnableAutoConfiguration
@EnableCaching
public class PetclinicmvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetclinicmvcApplication.class, args);
	}
	
	@Value("classpath:dbscript/insert.sql")
	private Resource dataScript;
	
	@Autowired
	private DataSource dataSource;
	
//	@Bean
//	public EmbeddedServletContainerCustomizer containerCustomizer() {
//	 
//	    return new EmbeddedServletContainerCustomizer() {
//	        @Override
//	        public void customize(ConfigurableEmbeddedServletContainer container) {
//
//	        	ErrorPage error403Page = new ErrorPage(HttpStatus.FORBIDDEN, "/accessdenied.jsp");
//	 
//	            container.addErrorPages( error403Page );
//	        }
//	    };
//	}
	
	@EventListener({ContextRefreshedEvent.class})
    void contextRefreshedEvent() {
		
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScript( dataScript );
		populator.setContinueOnError(true);
		
		DatabasePopulatorUtils.execute(populator, dataSource);
		
    }
}
