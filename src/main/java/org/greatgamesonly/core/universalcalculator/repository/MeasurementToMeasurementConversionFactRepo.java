package org.greatgamesonly.core.universalcalculator.repository;

import org.greatgamesonly.core.universalcalculator.domain.formula.MeasurementUnitToMeasurementUnitConversionFactor;
import org.greatgamesonly.core.universalcalculator.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeasurementToMeasurementConversionFactRepo extends BaseRepository<MeasurementUnitToMeasurementUnitConversionFactor,Long> {
    // Method to find ConversionFactor by fromMeasurementUnit name
    List<MeasurementUnitToMeasurementUnitConversionFactor> findByFromMeasurementUnitName(String fromMeasurementUnitName);

    // Method to find ConversionFactor by toMeasurementUnit name
    List<MeasurementUnitToMeasurementUnitConversionFactor> findByToMeasurementUnitName(String toMeasurementUnitName);

    List<MeasurementUnitToMeasurementUnitConversionFactor> findByFromMeasurementUnitNameAndToMeasurementUnitName(String fromMeasurementUnitName, String toMeasurementUnitName);
}
