package org.greatgamesonly.core.universalcalculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import static org.greatgamesonly.core.universalcalculator.GlobalConstants.CORE_PACKAGE_NAME;

@SpringBootApplication()
@ComponentScan({
		CORE_PACKAGE_NAME+".exception",
		CORE_PACKAGE_NAME+".controller",
		CORE_PACKAGE_NAME+".configuration",
		CORE_PACKAGE_NAME+".model.domain",
		CORE_PACKAGE_NAME+".model.repository",
		CORE_PACKAGE_NAME+".model.service",
		CORE_PACKAGE_NAME+".model.annotation",
		CORE_PACKAGE_NAME+".model.validation"
})
public class UniversalCalculatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(UniversalCalculatorApplication.class, args);
	}

}
