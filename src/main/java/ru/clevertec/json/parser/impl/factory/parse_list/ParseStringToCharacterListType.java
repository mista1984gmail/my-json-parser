package ru.clevertec.json.parser.impl.factory.parse_list;


import ru.clevertec.json.parser.impl.factory.parse_string.ParseStringToCharacterType;

import java.util.ArrayList;
import java.util.List;

public class ParseStringToCharacterListType implements ParseStringToSpecificListType{
    @Override
    public Object parseStringToSpecificListType(String value) {
        ParseStringToCharacterType parseStringToStringType = new ParseStringToCharacterType();
        List<Character> elements = new ArrayList<>();
        String [] arrays = value.substring(1, value.length()-1).split(",");
        for (int i = 0; i < arrays.length; i++) {
            elements.add(parseStringToStringType.parseStringToSpecificType(arrays[i]));
        }
        return elements;
    }
}
