package ru.clevertec.json.parser.impl.factory.parse_set;

import ru.clevertec.json.parser.impl.factory.parse_string.ParseStringToLongType;

import java.util.HashSet;
import java.util.Set;

public class ParseStringToLongSetType implements ParseStringToSpecificSetType {
    @Override
    public Object parseStringToSpecificSetType(String value) {
        ParseStringToLongType parseStringToStringType = new ParseStringToLongType();
        Set<Long> elements = new HashSet<>();
        String [] arrays = value.substring(1, value.length()-1).split(",");
        for (int i = 0; i < arrays.length; i++) {
            elements.add(parseStringToStringType.parseStringToSpecificType(arrays[i]));
        }
        return elements;
    }
}
