package ru.clevertec.json.parser.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class PersonTestDataAllSupportedTypes {

    private String stringValue;
    private Integer integerValue;
    private Byte byteValue;
    private Short shortValue;
    private Long longValue;
    private Float floatValue;
    private Double doubleValue;
    private Character characterValue;
    private Boolean booleanValue;
    private LocalDate localDateValue;
    private LocalDateTime localDateTimeValue;
    private Set<String> valueOfSet;
    private List<Integer> listValue;
    private Map<Long, String> mapValue;

    public PersonTestDataAllSupportedTypes(String stringValue, Integer integerValue, Byte byteValue, Short shortValue, Long longValue, Float floatValue, Double doubleValue, Character characterValue, Boolean booleanValue, LocalDate localDateValue, LocalDateTime localDateTimeValue, Set<String> valueOfSet, List<Integer> listValue, Map<Long, String> mapValue) {
        this.stringValue = stringValue;
        this.integerValue = integerValue;
        this.byteValue = byteValue;
        this.shortValue = shortValue;
        this.longValue = longValue;
        this.floatValue = floatValue;
        this.doubleValue = doubleValue;
        this.characterValue = characterValue;
        this.booleanValue = booleanValue;
        this.localDateValue = localDateValue;
        this.localDateTimeValue = localDateTimeValue;
        this.valueOfSet = valueOfSet;
        this.listValue = listValue;
        this.mapValue = mapValue;
    }

    public PersonTestDataAllSupportedTypes() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonTestDataAllSupportedTypes that = (PersonTestDataAllSupportedTypes) o;
        return Objects.equals(stringValue, that.stringValue) && Objects.equals(integerValue, that.integerValue) && Objects.equals(byteValue, that.byteValue) && Objects.equals(shortValue, that.shortValue) && Objects.equals(longValue, that.longValue) && Objects.equals(floatValue, that.floatValue) && Objects.equals(doubleValue, that.doubleValue) && Objects.equals(characterValue, that.characterValue) && Objects.equals(booleanValue, that.booleanValue) && Objects.equals(localDateValue, that.localDateValue) && Objects.equals(localDateTimeValue, that.localDateTimeValue) && Objects.equals(valueOfSet, that.valueOfSet) && Objects.equals(listValue, that.listValue) && Objects.equals(mapValue, that.mapValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stringValue, integerValue, byteValue, shortValue, longValue, floatValue, doubleValue, characterValue, booleanValue, localDateValue, localDateTimeValue, valueOfSet, listValue, mapValue);
    }
}
