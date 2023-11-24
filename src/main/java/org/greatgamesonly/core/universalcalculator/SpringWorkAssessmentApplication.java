package org.greatgamesonly.core.universalcalculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication()
@ComponentScan({
		"org.greatgamesonly.core.universalcalculator.domain",
		"org.greatgamesonly.core.universalcalculator.exception",
		"org.greatgamesonly.core.universalcalculator.repository",
		"org.greatgamesonly.core.universalcalculator.service",
		"org.greatgamesonly.core.universalcalculator.controller",
		"org.greatgamesonly.core.universalcalculator.configuration",
		"org.greatgamesonly.core.universalcalculator.annotation"
})
public class SpringWorkAssessmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringWorkAssessmentApplication.class, args);
	}

}
