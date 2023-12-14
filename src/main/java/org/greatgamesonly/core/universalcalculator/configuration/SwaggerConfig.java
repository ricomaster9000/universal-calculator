package org.greatgamesonly.core.universalcalculator.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import org.greatgamesonly.core.universalcalculator.model.validation.maxdouble.MaxDoubleOpenApiCustomizer;
import org.greatgamesonly.core.universalcalculator.model.validation.unique.UniqueOpenApiCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.greatgamesonly.core.universalcalculator.GlobalConstants.ALL_INTERNAL_FULL_CLASS_NAMES;
import static org.greatgamesonly.core.universalcalculator.GlobalConstants.CORE_PACKAGE_NAME;

@Configuration
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi publicApi(
            MaxDoubleOpenApiCustomizer maxDoubleCustomizer,
            UniqueOpenApiCustomizer uniqueOpenApiCustomizer
    ) {

        return GroupedOpenApi.builder()
                .group("public-apis")
                .packagesToScan(CORE_PACKAGE_NAME+".controller") // specify the package containing your controllers
                .pathsToMatch("/api/**") // specify patterns to exclude
                .addOpenApiCustomizer(maxDoubleCustomizer)
                .addOpenApiCustomizer(uniqueOpenApiCustomizer)
                .build();
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().addServersItem(new Server().url("https://universal-calculator.net"));
    }
}