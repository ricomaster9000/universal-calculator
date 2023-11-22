package com.kingprice.insurance.springworkassessment;

import com.kingprice.insurance.springworkassessment.SpringWorkAssessmentApplication;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTestContextBootstrapper;
import org.springframework.test.context.BootstrapWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = SpringWorkAssessmentApplication.class,
        properties = {
                "spring.datasource.url=jdbc:derby:memory:local;create=true",
                "spring.datasource.driver-class-name=org.apache.derby.jdbc.EmbeddedDriver",
                "spring.datasource.username=derbyuser",
                "spring.datasource.password=password",
                "spring.jpa.hibernate.ddl-auto=update",
                "DISABLE_HTTP_SECURITY=TRUE"
        })
@AutoConfigureMockMvc
@BootstrapWith(SpringBootTestContextBootstrapper.class)
@ExtendWith({SpringExtension.class})
/*
this annotation below here was a lifesaver, I dug it up after doing some digging,
this is what makes it able to use JPA repositories in test classes with the Spring Boot test annotations you see above here
 */
@AutoConfigureDataJpa
@ExtendWith({SpringExtension.class})
@Retention(RetentionPolicy.RUNTIME)
public @interface SpringBootTestWrapper {}
