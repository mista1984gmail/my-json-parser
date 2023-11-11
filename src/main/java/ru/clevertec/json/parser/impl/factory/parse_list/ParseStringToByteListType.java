package ru.clevertec.json.parser.impl.factory.parse_list;


import ru.clevertec.json.parser.impl.factory.parse_string.ParseStringToByteType;

import java.util.ArrayList;
import java.util.List;

public class ParseStringToByteListType implements ParseStringToSpecificListType{
    @Override
    public Object parseStringToSpecificListType(String value) {
        ParseStringToByteType parseStringToStringType = new ParseStringToByteType();
        List<Byte> elements = new ArrayList<>();
        String [] arrays = value.substring(1, value.length()-1).split(",");
        for (int i = 0; i < arrays.length; i++) {
            elements.add(parseStringToStringType.parseStringToSpecificType(arrays[i]));
        }
        return elements;
    }
}
