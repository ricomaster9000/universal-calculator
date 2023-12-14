package org.greatgamesonly.core.universalcalculator.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static org.greatgamesonly.core.universalcalculator.GlobalConstants.JAVADOCS_SPECIAL_KEY;
import static org.greatgamesonly.core.universalcalculator.GlobalConstants.NETWORK_REQUEST_TIMEOUT_STANDARD;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/static/"+JAVADOCS_SPECIAL_KEY+"/javadoc/*/**").addResourceLocations("classpath:/static/"+JAVADOCS_SPECIAL_KEY+"/javadoc/");
        registry.addResourceHandler("/resources/static/"+JAVADOCS_SPECIAL_KEY+"/javadoc/*/**").addResourceLocations("classpath:META-INF/resources/static/"+JAVADOCS_SPECIAL_KEY+"/javadoc/");
        registry.addResourceHandler("/"+JAVADOCS_SPECIAL_KEY+"/javadoc/*/**").addResourceLocations("classpath:META-INF/"+JAVADOCS_SPECIAL_KEY+"/javadoc/");
        registry.addResourceHandler("/static/"+JAVADOCS_SPECIAL_KEY+"/javadoc/**").addResourceLocations("classpath:/static/"+JAVADOCS_SPECIAL_KEY+"/javadoc/");
        registry.addResourceHandler("/resources/static/"+JAVADOCS_SPECIAL_KEY+"/javadoc/**").addResourceLocations("classpath:META-INF/resources/static/"+JAVADOCS_SPECIAL_KEY+"/javadoc/");
        registry.addResourceHandler("/"+JAVADOCS_SPECIAL_KEY+"/javadoc/**").addResourceLocations("classpath:META-INF/"+JAVADOCS_SPECIAL_KEY+"/javadoc/");
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate(clientHttpRequestFactory());
    }

    private SimpleClientHttpRequestFactory clientHttpRequestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setReadTimeout(NETWORK_REQUEST_TIMEOUT_STANDARD);
        factory.setConnectTimeout(NETWORK_REQUEST_TIMEOUT_STANDARD);
        return factory;
    }

}
