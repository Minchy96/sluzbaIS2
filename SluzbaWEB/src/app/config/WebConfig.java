package app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.jasperreports.JasperReportsMultiFormatView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsViewResolver;

import app.converter.DepartmanKonverter;
import app.converter.ProfesorKonverter;
import app.repository.AdminRepo;



@Configuration
@EnableWebMvc
@ComponentScan("app")
public class WebConfig extends WebMvcConfigurerAdapter {
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/views/");
		resolver.setSuffix(".jsp");
		resolver.setExposeContextBeansAsAttributes(true);
		return resolver;
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Bean(name = "multipartResolver")
	public MultipartResolver getResolver() {
		CommonsMultipartResolver multipartRes = new CommonsMultipartResolver();
		multipartRes.setMaxUploadSize(2000000);
		return multipartRes;
	}
	
	
	@Autowired
	AdminRepo ar;
	
	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addConverter(new DepartmanKonverter(ar));
		registry.addConverter(new ProfesorKonverter(ar));
	}
	
	
	

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource rb = new ReloadableResourceBundleMessageSource();
		rb.addBasenames("classpath:resources/messages");
		return rb;
	}

	@Bean
	public AcceptHeaderLocaleResolver getLocalResolver() {
		AcceptHeaderLocaleResolver lr = new AcceptHeaderLocaleResolver();
		return lr;
	}
	
	@Bean
	public JasperReportsViewResolver getJasperReportsViewResolver() {
	    JasperReportsViewResolver resolver = new JasperReportsViewResolver();
	    resolver.setPrefix("classpath:resources/jasperreports/");
	    resolver.setSuffix(".jasper");
	    resolver.setReportDataKey("datasource");
	    resolver.setViewNames("rpt_*");
	    resolver.setViewClass(JasperReportsMultiFormatView.class);
	    resolver.setOrder(0);
	    return resolver;
	}
}
