package org.sofyan.latihan;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ResourceBundleViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

@Configuration
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
		registry.addViewController("/page/reports").setViewName("reports");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**")
				.addResourceLocations("/resources/")
				.setCachePeriod(31556926);
	}
	
	@Bean
	@Primary
	public ResourceBundleViewResolver getResourceBundleViewResolver() {
		ResourceBundleViewResolver resolver = new ResourceBundleViewResolver();
		resolver.setBasename("views");
		resolver.setOrder(0);
		return resolver;
	}
	
//	@Bean
//    public JasperReportsViewResolver getJasperReportsViewResolver() {
//        JasperReportsViewResolver resolver = new JasperReportsViewResolver();
//        resolver.setPrefix("classpath:/jasper/");
//        resolver.setSuffix(".jrxml");
//        resolver.setReportDataKey("datasource");
//        
//        //yang bikin beda buat spring kalo depannya ini adalah report selain itu bukan
//        //akan dilanjutkan ke resolver yang lain
//        resolver.setViewNames("report_*"); 
//        resolver.setViewClass(JasperReportsMultiFormatView.class);
//        return resolver;
//    }
	
//	@Bean
//	public UrlBasedViewResolver tilesViewResolver() {
//
//		UrlBasedViewResolver tilesViewResolver = new UrlBasedViewResolver();
//		tilesViewResolver.setViewClass(TilesView.class);
//		tilesViewResolver.setOrder(1);
//		return tilesViewResolver;
//		
//	}

	@Bean
	public TilesConfigurer tilesConfigurer() {

		String[] definitions = new String[] { "/WEB-INF/tiles.xml" };

		TilesConfigurer tilesConfigurer = new TilesConfigurer();
		tilesConfigurer.setDefinitions(definitions);
		tilesConfigurer.setCheckRefresh(true);

		return tilesConfigurer;

	}

}
