package com.kingprice.insurance.springworkassessment.repository;

import com.kingprice.insurance.springworkassessment.domain.shared.InputParamSpecification;
import com.kingprice.insurance.springworkassessment.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NumberInputParamSpecRepository extends BaseRepository<InputParamSpecification, Long> {
}
