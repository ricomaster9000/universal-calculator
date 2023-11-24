package org.greatgamesonly.core.universalcalculator.repository;

import org.greatgamesonly.core.universalcalculator.domain.shared.InputParamSpecification;
import org.greatgamesonly.core.universalcalculator.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NumberInputParamSpecRepository extends BaseRepository<InputParamSpecification, Long> {
}
