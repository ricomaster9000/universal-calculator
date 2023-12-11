package org.greatgamesonly.core.universalcalculator.configuration;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.stream.Stream;

import static java.util.Collections.emptyList;

@Converter
public class DataSpecificIdsListConverter implements AttributeConverter<List<Long>, String> {
    private static final String SPLIT_CHAR = ";";

    @Override
    public String convertToDatabaseColumn(List<Long> stringList) {
        return stringList != null ? StringUtils.join(SPLIT_CHAR, stringList) : null;
    }

    @Override
    public List<Long> convertToEntityAttribute(String string) {
        return string != null ? Stream.of(StringUtils.split(string, SPLIT_CHAR)).map(Long::parseLong).toList() : emptyList();
    }
}
