package org.nercel.joker.web.config;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.nercel.joker.web.core.CP_InitializingInterceptor;
import org.nercel.joker.web.core.CP_PropertyEditorRegistrar;
import org.nercel.joker.web.core.CP_SimpleMappingExceptionResolver;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.handler.SimpleServletHandlerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "org.nercel.joker.web", useDefaultFilters = false, includeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION, value = {Controller.class})
})
public class MvcConfig extends WebMvcConfigurationSupport {
	
	private static final Logger logger = Logger
			.getLogger(MvcConfig.class);

    /**                                                          
    * 描述 : <注册试图处理器>. <br> 
    *<p> 
    	<使用方法说明>  
     </p>                                                                                                                                                                                                                                                
    * @return                                                                                                      
    */  
    @Bean
    public ViewResolver viewResolver() {
    	logger.info("ViewResolver");
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("mainView/function/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
    
    /**                                                          
    * 描述 : <注册消息资源处理器>. <br> 
    *<p> 
    	<使用方法说明>  
     </p>                                                                                                                                                                                                                                                
    * @return                                                                                                      
    */  
    @Bean
    public MessageSource messageSource() {
    	logger.info("MessageSource");
    	ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
    	messageSource.setBasename("config.messages.messages");
    	
    	return messageSource;
    }
    
    /**                                                          
    * 描述 : <注册servlet适配器>. <br> 
    *<p> 
    	<只需要在自定义的servlet上用@Controller("映射路径")标注即可>  
     </p>                                                                                                                                                                                                                                                
    * @return                                                                                                      
    */  
    @Bean
    public HandlerAdapter servletHandlerAdapter(){
    	logger.info("HandlerAdapter");
    	return new SimpleServletHandlerAdapter();
    }
    
    /**                                                          
    * 描述 : <本地化拦截器>. <br> 
    *<p> 
    	<使用方法说明>  
     </p>                                                                                                                                                                                                                                                
    * @return                                                                                                      
    */  
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor(){
    	logger.info("LocaleChangeInterceptor");
    	return new LocaleChangeInterceptor();
    }
    
    /**                                                          
    * 描述 : <基于cookie的本地化资源处理器>. <br> 
    *<p> 
    	<使用方法说明>  
     </p>                                                                                                                                                                                                                                                
    * @return                                                                                                      
    */  
    @Bean(name="localeResolver")
    public CookieLocaleResolver cookieLocaleResolver(){
    	logger.info("CookieLocaleResolver");
    	return new CookieLocaleResolver();
    }
    
    /**                                                          
    * 描述 : <注册自定义拦截器>. <br> 
    *<p> 
    	<使用方法说明>  
     </p>                                                                                                                                                                                                                                                
    * @return                                                                                                      
    */  
    @Bean
    public CP_InitializingInterceptor initializingInterceptor(){
    	logger.info("CP_InitializingInterceptor");
    	return new CP_InitializingInterceptor();
    }
    
    
    /**                                                          
     * 描述 : <RequestMappingHandlerMapping需要显示声明，否则不能注册自定义的拦截器>. <br> 
     *<p> 
     	<这个比较奇怪,理论上应该是不需要的>  
      </p>                                                                                                                                                                                                                                                
     * @return                                                                                                      
     */ 
    @Bean
	public RequestMappingHandlerMapping requestMappingHandlerMapping() {
    	logger.info("RequestMappingHandlerMapping");
		
		return super.requestMappingHandlerMapping();
	}
    
    
    
    
    

    /**                                                          
    * 描述 : <添加拦截器>. <br> 
    *<p> 
    	<使用方法说明>  
     </p>                                                                                                                                                                                                                                                
    * @param registry                                                                                                      
    */  
    @Override
	protected void addInterceptors(InterceptorRegistry registry) {
    	logger.info("addInterceptors start");
		registry.addInterceptor(localeChangeInterceptor());
		registry.addInterceptor(initializingInterceptor());
		logger.info("addInterceptors end");
	}

    /**                                                          
     * 描述 : <HandlerMapping需要显示声明，否则不能注册资源访问处理器>. <br> 
     *<p> 
     	<这个比较奇怪,理论上应该是不需要的>  
      </p>                                                                                                                                                                                                                                                
     * @return                                                                                                      
     */ 
    @Bean
	public HandlerMapping resourceHandlerMapping() {
    	logger.info("HandlerMapping");
    	return super.resourceHandlerMapping();
    }
    
    /**                                                          
     * 描述 : <资源访问处理器>. <br> 
     *<p> 
     	<可以在jsp中使用/static/**的方式访问/WEB-INF/static/下的内容>  
      </p>                                                                                                                                                                                                                                                
     * @param registry                                                                                                      
     */  
	@Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		logger.info("addResourceHandlers");
        registry.addResourceHandler("/static/**").addResourceLocations("/WEB-INF/static/");
    }
    
	/**                                                          
	* 描述 : <文件上传处理器>. <br> 
	*<p> 
		<使用方法说明>  
	 </p>                                                                                                                                                                                                                                                
	* @return                                                                                                      
	*/  
	@Bean(name="multipartResolver")
	public CommonsMultipartResolver commonsMultipartResolver(){
		logger.info("CommonsMultipartResolver");
		return new CommonsMultipartResolver();
	}
	
	/**                                                          
	* 描述 : <异常处理器>. <br> 
	*<p> 
		<系统运行时遇到指定的异常将会跳转到指定的页面>  
	 </p>                                                                                                                                                                                                                                                
	* @return                                                                                                      
	*/  
	@Bean(name="exceptionResolver")
	public CP_SimpleMappingExceptionResolver simpleMappingExceptionResolver(){
		logger.info("CP_SimpleMappingExceptionResolver");
		CP_SimpleMappingExceptionResolver simpleMappingExceptionResolver= new CP_SimpleMappingExceptionResolver();
		simpleMappingExceptionResolver.setDefaultErrorView("common_error");
		simpleMappingExceptionResolver.setExceptionAttribute("exception");
		Properties properties = new Properties();
		properties.setProperty("java.lang.RuntimeException", "common_error");
		simpleMappingExceptionResolver.setExceptionMappings(properties);
		return simpleMappingExceptionResolver;
	}
	
	 /**                                                          
     * 描述 : <RequestMappingHandlerAdapter需要显示声明，否则不能注册通用属性编辑器>. <br> 
     *<p> 
     	<这个比较奇怪,理论上应该是不需要的>  
      </p>                                                                                                                                                                                                                                                
     * @return                                                                                                      
     */ 
	@Bean
	public RequestMappingHandlerAdapter requestMappingHandlerAdapter() {
		logger.info("RequestMappingHandlerAdapter");
    	return super.requestMappingHandlerAdapter();
	}
	
	/**                                                          
	* 描述 : <注册通用属性编辑器>. <br> 
	*<p> 
		<这里只增加了字符串转日期和字符串两边去空格的处理>  
	 </p>                                                                                                                                                                                                                                                
	* @return                                                                                                      
	*/  
	@Override
	protected ConfigurableWebBindingInitializer getConfigurableWebBindingInitializer() {
		logger.info("ConfigurableWebBindingInitializer");
		ConfigurableWebBindingInitializer initializer = super.getConfigurableWebBindingInitializer();
		CP_PropertyEditorRegistrar register = new CP_PropertyEditorRegistrar();
		register.setFormat("yyyy-MM-dd");
		initializer.setPropertyEditorRegistrar(register);
		return initializer;
	}
    
    
}

