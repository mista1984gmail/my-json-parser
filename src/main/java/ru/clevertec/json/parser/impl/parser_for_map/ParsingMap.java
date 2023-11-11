package ru.clevertec.json.parser.impl.parser_for_map;

import ru.clevertec.json.parser.impl.factory.parse_string.ParseStringToIntegerType;
import ru.clevertec.json.parser.impl.factory.parse_string.ParseStringToLongType;
import ru.clevertec.json.parser.impl.factory.parse_string.ParseStringToStringType;
import ru.clevertec.json.parser.util.Constants;

import java.util.HashMap;
import java.util.Map;

public class ParsingMap {
    public static Object parsingAndCreatingMap(String jsonMap, String types){
        String [] keyValueTypeMap = types.substring(24, types.length()-1)
                                    .replace(" java.lang.", "")
                                    .split(",");

        String keyType = keyValueTypeMap[0];
        String valueType = keyValueTypeMap[1];

        String keyValueElements[] = getArraysWithoutQuotes(jsonMap.substring(1, jsonMap.length()-1));

        if(keyType.equals(Constants.CLASS_STRING) && valueType.equals(Constants.CLASS_STRING)){
            Map<String, String> mapElement = new HashMap<>();
            for (int i = 0; i < keyValueElements.length; i++) {
                String [] arrays = keyValueElements[i].split(":");
                ParseStringToStringType parseStringToStringType = new ParseStringToStringType();
                mapElement.put(parseStringToStringType.parseStringToSpecificType(arrays[0]),
                        parseStringToStringType.parseStringToSpecificType(arrays[1]));
            }
            return mapElement;
        }
        else if(keyType.equals(Constants.CLASS_STRING) && valueType.equals(Constants.CLASS_INTEGER)){
            Map<String, Integer> mapElement = new HashMap<>();
            for (int i = 0; i < keyValueElements.length; i++) {
                String [] arrays = keyValueElements[i].split(":");
                ParseStringToStringType parseStringToStringType = new ParseStringToStringType();
                ParseStringToIntegerType parseStringToIntegerType = new ParseStringToIntegerType();
                mapElement.put(parseStringToStringType.parseStringToSpecificType(arrays[0]),
                        parseStringToIntegerType.parseStringToSpecificType(arrays[1]));
            }
            return mapElement;
        }
        else if(keyType.equals(Constants.CLASS_LONG) && valueType.equals(Constants.CLASS_STRING)){
            Map<Long, String> mapElement = new HashMap<>();
            for (int i = 0; i < keyValueElements.length; i++) {
                String [] arrays = keyValueElements[i].split(":");
                ParseStringToLongType parseStringToLongType = new ParseStringToLongType();
                ParseStringToStringType parseStringToStringType = new ParseStringToStringType();
                mapElement.put(parseStringToLongType.parseStringToSpecificType(arrays[0]),
                        parseStringToStringType.parseStringToSpecificType(arrays[1]));
            }
            return mapElement;
        }
        else if(keyType.equals(Constants.CLASS_LONG) && valueType.equals(Constants.CLASS_INTEGER)){
            Map<Long, Integer> mapElement = new HashMap<>();
            for (int i = 0; i < keyValueElements.length; i++) {
                String [] arrays = keyValueElements[i].split(":");
                ParseStringToLongType parseStringToLongType = new ParseStringToLongType();
                ParseStringToIntegerType parseStringToIntegerType = new ParseStringToIntegerType();
                mapElement.put(parseStringToLongType.parseStringToSpecificType(arrays[0]),
                        parseStringToIntegerType.parseStringToSpecificType(arrays[1]));
            }
            return mapElement;
        }
        else if(keyType.equals(Constants.CLASS_INTEGER) && valueType.equals(Constants.CLASS_INTEGER)){
            Map<Integer, Integer> mapElement = new HashMap<>();
            for (int i = 0; i < keyValueElements.length; i++) {
                String [] arrays = keyValueElements[i].split(":");
                ParseStringToIntegerType parseStringToIntegerType = new ParseStringToIntegerType();
                mapElement.put(parseStringToIntegerType.parseStringToSpecificType(arrays[0]),
                        parseStringToIntegerType.parseStringToSpecificType(arrays[1]));
            }
            return mapElement;
        }
        return null;
    }
    private static String[] getArraysWithoutQuotes(String stringObject) {
        String[] arraysElementsKeyPlusValue = stringObject.split(",\"");
        for (int i = 0; i < arraysElementsKeyPlusValue.length; i++) {
            arraysElementsKeyPlusValue[i] = arraysElementsKeyPlusValue[i].replace("\"", "");
            arraysElementsKeyPlusValue[i] = arraysElementsKeyPlusValue[i].replace("\'", "");
        }
        return arraysElementsKeyPlusValue;
    }
}
