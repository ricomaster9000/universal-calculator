package org.greatgamesonly.core.universalcalculator.model.repository;

import org.greatgamesonly.core.universalcalculator.model.domain.shared.InputParamSpecification;
import org.greatgamesonly.core.universalcalculator.model.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NumberInputParamSpecRepository extends BaseRepository<InputParamSpecification, Long> {
}
