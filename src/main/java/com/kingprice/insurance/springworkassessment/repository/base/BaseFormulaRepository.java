package com.kingprice.insurance.springworkassessment.repository.base;

import com.kingprice.insurance.springworkassessment.domain.formula.base.Formula;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseFormulaRepository<T extends Formula<?>> extends BaseRepository<T, Long> {

}
