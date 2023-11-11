package ru.clevertec.json.parser.impl.factory.parse_list;

import ru.clevertec.json.parser.impl.factory.parse_string.ParseStringToDoubleType;

import java.util.ArrayList;
import java.util.List;

public class ParseStringToDoubleListType implements ParseStringToSpecificListType{
    @Override
    public Object parseStringToSpecificListType(String value) {
        ParseStringToDoubleType parseStringToStringType = new ParseStringToDoubleType();
        List<Double> elements = new ArrayList<>();
        String [] arrays = value.substring(1, value.length()-1).split(",");
        for (int i = 0; i < arrays.length; i++) {
            elements.add(parseStringToStringType.parseStringToSpecificType(arrays[i]));
        }
        return elements;
    }
}
