package org.greatgamesonly.core.universalcalculator.model.annotation;

import org.greatgamesonly.core.universalcalculator.model.domain.base.BaseEntity;
import org.greatgamesonly.core.universalcalculator.model.repository.base.BaseRepository;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface LinkedRepository {
    Class<? extends BaseRepository<? extends BaseEntity, Long>> value();
}
