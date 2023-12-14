package org.greatgamesonly.core.universalcalculator.configuration;

import org.greatgamesonly.core.universalcalculator.GlobalConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private GlobalConstants globalConstants;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/"+globalConstants.JAVADOCS_SPECIAL_KEY()+"/javadoc/*/**").addResourceLocations("classpath:/static/"+globalConstants.JAVADOCS_SPECIAL_KEY()+"/javadoc/");
        registry.addResourceHandler("/resources/static/"+globalConstants.JAVADOCS_SPECIAL_KEY()+"/javadoc/*/**").addResourceLocations("classpath:META-INF/resources/static/"+globalConstants.JAVADOCS_SPECIAL_KEY()+"/javadoc/");
        registry.addResourceHandler("/"+globalConstants.JAVADOCS_SPECIAL_KEY()+"/javadoc/*/**").addResourceLocations("classpath:META-INF/"+globalConstants.JAVADOCS_SPECIAL_KEY()+"/javadoc/");
        registry.addResourceHandler("/static/"+globalConstants.JAVADOCS_SPECIAL_KEY()+"/javadoc/**").addResourceLocations("classpath:/static/"+globalConstants.JAVADOCS_SPECIAL_KEY()+"/javadoc/");
        registry.addResourceHandler("/resources/static/"+globalConstants.JAVADOCS_SPECIAL_KEY()+"/javadoc/**").addResourceLocations("classpath:META-INF/resources/static/"+globalConstants.JAVADOCS_SPECIAL_KEY()+"/javadoc/");
        registry.addResourceHandler("/"+globalConstants.JAVADOCS_SPECIAL_KEY()+"/javadoc/**").addResourceLocations("classpath:META-INF/"+globalConstants.JAVADOCS_SPECIAL_KEY()+"/javadoc/");
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate(clientHttpRequestFactory());
    }

    private SimpleClientHttpRequestFactory clientHttpRequestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setReadTimeout(globalConstants.NETWORK_REQUEST_TIMEOUT_STANDARD());
        factory.setConnectTimeout(globalConstants.NETWORK_REQUEST_TIMEOUT_STANDARD());
        return factory;
    }

}
