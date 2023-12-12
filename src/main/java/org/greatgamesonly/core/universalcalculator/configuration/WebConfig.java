package org.greatgamesonly.core.universalcalculator.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static org.greatgamesonly.core.universalcalculator.GlobalConstants.JAVADOCS_SPECIAL_KEY;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        // css
        registry.addResourceHandler("/static/"+JAVADOCS_SPECIAL_KEY+"/javadoc/*/**").addResourceLocations("classpath:/static/"+JAVADOCS_SPECIAL_KEY+"/javadoc/");
        registry.addResourceHandler("/resources/static/"+JAVADOCS_SPECIAL_KEY+"/javadoc/*/**").addResourceLocations("classpath:META-INF/resources/static/"+JAVADOCS_SPECIAL_KEY+"/javadoc/");
        registry.addResourceHandler("/"+JAVADOCS_SPECIAL_KEY+"/javadoc/*/**").addResourceLocations("classpath:META-INF/"+JAVADOCS_SPECIAL_KEY+"/javadoc/");
        registry.addResourceHandler("/static/"+JAVADOCS_SPECIAL_KEY+"/javadoc/**").addResourceLocations("classpath:/static/"+JAVADOCS_SPECIAL_KEY+"/javadoc/");
        registry.addResourceHandler("/resources/static/"+JAVADOCS_SPECIAL_KEY+"/javadoc/**").addResourceLocations("classpath:META-INF/resources/static/"+JAVADOCS_SPECIAL_KEY+"/javadoc/");
        registry.addResourceHandler("/"+JAVADOCS_SPECIAL_KEY+"/javadoc/**").addResourceLocations("classpath:META-INF/"+JAVADOCS_SPECIAL_KEY+"/javadoc/");
    }

}
