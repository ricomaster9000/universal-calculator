package org.greatgamesonly.core.universalcalculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication()
@ComponentScan({
		"org.greatgamesonly.core.universalcalculator.exception",
		"org.greatgamesonly.core.universalcalculator.controller",
		"org.greatgamesonly.core.universalcalculator.configuration",
		"org.greatgamesonly.core.universalcalculator.model.domain",
		"org.greatgamesonly.core.universalcalculator.model.repository",
		"org.greatgamesonly.core.universalcalculator.model.service",
		"org.greatgamesonly.core.universalcalculator.model.annotation"
})
public class UniversalCalculatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(UniversalCalculatorApplication.class, args);
	}

}
