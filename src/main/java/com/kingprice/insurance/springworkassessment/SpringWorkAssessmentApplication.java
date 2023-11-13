package com.kingprice.insurance.springworkassessment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication()
@ComponentScan({
		"com.kingprice.insurance.springworkassessment.model",
		"com.kingprice.insurance.springworkassessment.exception",
		"com.kingprice.insurance.springworkassessment.repositories",
		"com.kingprice.insurance.springworkassessment.services",
		"com.kingprice.insurance.springworkassessment.controllers",
		"com.kingprice.insurance.springworkassessment.configuration"
})
public class SpringWorkAssessmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringWorkAssessmentApplication.class, args);
	}

}
