package org.greatgamesonly.core.universalcalculator.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public-apis")
                .packagesToScan("org.greatgamesonly.core.universalcalculator.controller") // specify the package containing your controllers
                .pathsToMatch("/api/**") // specify patterns to exclude
                .build();
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().addServersItem(new Server().url("https://universal-calculator.net"));
    }
}