package com.leatherswan.artisticendeavors.config;  
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
@Configuration 
@EnableWebMvc   
@ComponentScan("com.leatherswan.artisticendeavors.app")
@EnableTransactionManagement
public class DispatcherConfig extends WebMvcConfigurerAdapter {  
    @Bean(name = "viewResolver")
    public InternalResourceViewResolver setupViewResolver() {  
    	InternalResourceViewResolver resolver = new InternalResourceViewResolver();  
        resolver.setPrefix("/WEB-INF/views/");  
        resolver.setSuffix(".jsp");  
        resolver.setViewClass(JstlView.class);
        resolver.setExposeContextBeansAsAttributes(true);
        return resolver;  
    }
    @Bean(name = "messageSource")
    public ReloadableResourceBundleMessageSource getMessageSource() {
        ReloadableResourceBundleMessageSource resource = new ReloadableResourceBundleMessageSource();
        resource.setBasename("WEB-INF/classes/messages");
        resource.setDefaultEncoding("UTF-8");
        return resource;
    }
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
      registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
      registry.addResourceHandler("/img/**").addResourceLocations("/resources/img/");
      registry.addResourceHandler("/data/**").addResourceLocations("/resources/data/");
      registry.addResourceHandler("/css/**").addResourceLocations("/resources/css/");
  }

  @Override
  public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
      configurer.enable();
  }

}