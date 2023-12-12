package org.greatgamesonly.core.universalcalculator.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import static org.greatgamesonly.core.universalcalculator.GlobalConstants.JAVADOCS_SPECIAL_KEY;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        // css
        registry.addResourceHandler("/static/"+JAVADOCS_SPECIAL_KEY+"/javadoc/*/**.css").addResourceLocations("classpath:/static/"+JAVADOCS_SPECIAL_KEY+"/javadoc/");
        registry.addResourceHandler("/resources/static/"+JAVADOCS_SPECIAL_KEY+"/javadoc/*/**.css").addResourceLocations("classpath:META-INF/resources/static/"+JAVADOCS_SPECIAL_KEY+"/javadoc/");
        registry.addResourceHandler("/"+JAVADOCS_SPECIAL_KEY+"/javadoc/*/**.css").addResourceLocations("classpath:META-INF/"+JAVADOCS_SPECIAL_KEY+"/javadoc/");
        registry.addResourceHandler("/static/"+JAVADOCS_SPECIAL_KEY+"/javadoc/**.css").addResourceLocations("classpath:/static/"+JAVADOCS_SPECIAL_KEY+"/javadoc/");
        registry.addResourceHandler("/resources/static/"+JAVADOCS_SPECIAL_KEY+"/javadoc/**.css").addResourceLocations("classpath:META-INF/resources/static/"+JAVADOCS_SPECIAL_KEY+"/javadoc/");
        registry.addResourceHandler("/"+JAVADOCS_SPECIAL_KEY+"/javadoc/**.css").addResourceLocations("classpath:META-INF/"+JAVADOCS_SPECIAL_KEY+"/javadoc/");

        // js
        registry.addResourceHandler("/static/"+JAVADOCS_SPECIAL_KEY+"/javadoc/*/**.js").addResourceLocations("classpath:/static/"+JAVADOCS_SPECIAL_KEY+"/javadoc/");
        registry.addResourceHandler("/resources/static/"+JAVADOCS_SPECIAL_KEY+"/javadoc/*/**.js").addResourceLocations("classpath:META-INF/resources/static/"+JAVADOCS_SPECIAL_KEY+"/javadoc/");
        registry.addResourceHandler("/"+JAVADOCS_SPECIAL_KEY+"/javadoc/*/**.js").addResourceLocations("classpath:META-INF/"+JAVADOCS_SPECIAL_KEY+"/javadoc/");
        registry.addResourceHandler("/static/"+JAVADOCS_SPECIAL_KEY+"/javadoc/**.js").addResourceLocations("classpath:/static/"+JAVADOCS_SPECIAL_KEY+"/javadoc/");
        registry.addResourceHandler("/resources/static/"+JAVADOCS_SPECIAL_KEY+"/javadoc/**.js").addResourceLocations("classpath:META-INF/resources/static/"+JAVADOCS_SPECIAL_KEY+"/javadoc/");
        registry.addResourceHandler("/"+JAVADOCS_SPECIAL_KEY+"/javadoc/**.js").addResourceLocations("classpath:META-INF/"+JAVADOCS_SPECIAL_KEY+"/javadoc/");

        // png
        registry.addResourceHandler("/static/"+JAVADOCS_SPECIAL_KEY+"/javadoc/*/**.png").addResourceLocations("classpath:/static/"+JAVADOCS_SPECIAL_KEY+"/javadoc/");
        registry.addResourceHandler("/resources/static/"+JAVADOCS_SPECIAL_KEY+"/javadoc/*/**.png").addResourceLocations("classpath:META-INF/resources/static/"+JAVADOCS_SPECIAL_KEY+"/javadoc/");
        registry.addResourceHandler("/"+JAVADOCS_SPECIAL_KEY+"/javadoc/*/**.png").addResourceLocations("classpath:META-INF/"+JAVADOCS_SPECIAL_KEY+"/javadoc/");
        registry.addResourceHandler("/static/"+JAVADOCS_SPECIAL_KEY+"/javadoc/**.png").addResourceLocations("classpath:/static/"+JAVADOCS_SPECIAL_KEY+"/javadoc/");
        registry.addResourceHandler("/resources/static/"+JAVADOCS_SPECIAL_KEY+"/javadoc/**.png").addResourceLocations("classpath:META-INF/resources/static/"+JAVADOCS_SPECIAL_KEY+"/javadoc/");
        registry.addResourceHandler("/"+JAVADOCS_SPECIAL_KEY+"/javadoc/**.png").addResourceLocations("classpath:META-INF/"+JAVADOCS_SPECIAL_KEY+"/javadoc/");
    }

    @Bean
    public ClassLoaderTemplateResolver secondaryTemplateResolver() {
        ClassLoaderTemplateResolver secondaryTemplateResolver = new ClassLoaderTemplateResolver();
        secondaryTemplateResolver.setPrefix("/static/"+JAVADOCS_SPECIAL_KEY+"/javadoc/");
        secondaryTemplateResolver.setSuffix(".html");
        secondaryTemplateResolver.setTemplateMode(TemplateMode.HTML);
        secondaryTemplateResolver.setCharacterEncoding("UTF-8");
        secondaryTemplateResolver.setOrder(1);
        secondaryTemplateResolver.setCheckExistence(true);

        return secondaryTemplateResolver;
    }



}
