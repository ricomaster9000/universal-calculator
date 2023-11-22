package com.kingprice.insurance.springworkassessment.annotation;

import com.kingprice.insurance.springworkassessment.domain.base.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface LinkedRepository {
    Class<? extends JpaRepository<? extends BaseEntity, Long>> value();
}
