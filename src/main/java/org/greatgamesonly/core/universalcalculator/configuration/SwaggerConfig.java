package org.greatgamesonly.core.universalcalculator.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import org.greatgamesonly.core.universalcalculator.model.validation.MaxDoubleOpenApiCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi publicApi(MaxDoubleOpenApiCustomizer maxDoubleCustomizer) {
        return GroupedOpenApi.builder()
                .group("public-apis")
                .packagesToScan("org.greatgamesonly.core.universalcalculator.controller") // specify the package containing your controllers
                .pathsToMatch("/api/**") // specify patterns to exclude
                .addOpenApiCustomizer(maxDoubleCustomizer)
                .build();
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().addServersItem(new Server().url("https://universal-calculator.net"));
    }
}