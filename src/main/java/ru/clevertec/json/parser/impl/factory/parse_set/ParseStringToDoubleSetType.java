package ru.clevertec.json.parser.impl.factory.parse_set;

import ru.clevertec.json.parser.impl.factory.parse_string.ParseStringToDoubleType;

import java.util.HashSet;
import java.util.Set;

public class ParseStringToDoubleSetType implements ParseStringToSpecificSetType {
    @Override
    public Object parseStringToSpecificSetType(String value) {
        ParseStringToDoubleType parseStringToStringType = new ParseStringToDoubleType();
        Set<Double> elements = new HashSet<>();
        String [] arrays = value.substring(1, value.length()-1).split(",");
        for (int i = 0; i < arrays.length; i++) {
            elements.add(parseStringToStringType.parseStringToSpecificType(arrays[i]));
        }
        return elements;
    }
}
