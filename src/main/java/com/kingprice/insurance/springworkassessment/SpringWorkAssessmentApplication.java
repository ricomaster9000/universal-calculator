package com.kingprice.insurance.springworkassessment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication()
@ComponentScan({
		"com.kingprice.insurance.springworkassessment.domain",
		"com.kingprice.insurance.springworkassessment.exception",
		"com.kingprice.insurance.springworkassessment.repository",
		"com.kingprice.insurance.springworkassessment.service",
		"com.kingprice.insurance.springworkassessment.controller",
		"com.kingprice.insurance.springworkassessment.configuration",
		"com.kingprice.insurance.springworkassessment.annotation"
})
public class SpringWorkAssessmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringWorkAssessmentApplication.class, args);
	}

}
