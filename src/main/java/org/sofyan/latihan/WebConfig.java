package org.sofyan.latihan;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {
	
	@Primary
	@Bean
    public MappingJackson2HttpMessageConverter jacksonMessageConverter(){
       
    	MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new Hibernate4Module());
        mapper.setDateFormat(new SimpleDateFormat("dd/MM/yyyy"));
        
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        messageConverter.setObjectMapper(mapper);
        
        return messageConverter;

    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(jacksonMessageConverter());
        super.configureMessageConverters(converters);
    }
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {

		registry.addViewController("/login").setViewName("login");
		registry.addViewController("/page/index").setViewName("index");
		registry.addViewController("/page/visit").setViewName("visit");
		
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations(
				"/resources/");
	}
	
//	@Bean
//	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
//	    
//		ObjectMapper mapper = new ObjectMapper();
//	    mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
//	    mapper.configure( DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false );
//	    mapper.setSerializationInclusion(Include.NON_NULL);
//	    mapper.setSerializationInclusion(Include.NON_EMPTY);
//	    
//	    mapper.registerModule( new Hibernate4Module() );
//	    
//	    MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter(mapper);
//	    
//	    return converter;
//	}

	@Bean
	public UrlBasedViewResolver tilesViewResolver() {

		UrlBasedViewResolver tilesViewResolver = new UrlBasedViewResolver();
		tilesViewResolver.setViewClass(TilesView.class);
		return tilesViewResolver;
	}

	@Bean
	public TilesConfigurer tilesConfigurer() {

		String[] definitions = new String[] { "/WEB-INF/tiles.xml" };

		TilesConfigurer tilesConfigurer = new TilesConfigurer();
		tilesConfigurer.setDefinitions(definitions);
		tilesConfigurer.setCheckRefresh(true);

		return tilesConfigurer;

	}

}
