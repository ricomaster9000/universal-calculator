package org.greatgamesonly.core.universalcalculator.model.repository.base;

import org.greatgamesonly.core.universalcalculator.model.domain.formula.base.Formula;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseFormulaRepository<T extends Formula<?>> extends BaseRepository<T, Long> {

}
