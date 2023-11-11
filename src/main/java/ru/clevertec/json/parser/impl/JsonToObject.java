package ru.clevertec.json.parser.impl;

import ru.clevertec.json.parser.annotation.IgnoreFieldJson;
import ru.clevertec.json.parser.impl.factory.parse_list.ParseListFactory;
import ru.clevertec.json.parser.impl.factory.parse_list.ParseStringToSpecificListType;
import ru.clevertec.json.parser.impl.factory.parse_set.ParseSetFactory;
import ru.clevertec.json.parser.impl.factory.parse_set.ParseStringToSpecificSetType;
import ru.clevertec.json.parser.impl.factory.parse_string.ParseFactory;
import ru.clevertec.json.parser.impl.factory.parse_string.ParseStringToSpecificType;
import ru.clevertec.json.parser.impl.parser_for_map.ParsingMap;
import ru.clevertec.json.parser.util.Constants;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

public class JsonToObject {

    private ParseFactory parseFactory = new ParseFactory();
    private ParseListFactory parseListFactory = new ParseListFactory();
    private ParseSetFactory parseSetFactory = new ParseSetFactory();

    public <T> T getObjectFromJson(String json, Class<T> tClass) throws Exception {
        Constructor<T> constructor = tClass.getConstructor();
        constructor.setAccessible(true);
        T instance = constructor.newInstance();

        Map<String, String> keyValueMap = new LinkedHashMap<>();
        String[] arraysElementsKeyPlusValue = null;
        String innerClassName = null;
        String jsonInnerObject = null;
        String jsonExternalObject = null;

        String jsonWithoutCurlyBraces = json.substring(1, json.length() - 1);

        if (jsonWithoutCurlyBraces.contains("{")) {
            int firstIndexCurlyBraces = jsonWithoutCurlyBraces.indexOf("{");
            int lastIndexCurlyBraces = jsonWithoutCurlyBraces.lastIndexOf("}");
            jsonInnerObject = jsonWithoutCurlyBraces.substring(firstIndexCurlyBraces, lastIndexCurlyBraces + 1);
            if (lastIndexCurlyBraces < jsonWithoutCurlyBraces.length() - 1) {
                jsonExternalObject = jsonWithoutCurlyBraces.substring(0, firstIndexCurlyBraces)
                        + "null" + jsonWithoutCurlyBraces.substring(lastIndexCurlyBraces + 1);
                innerClassName = getInnerClassName(jsonWithoutCurlyBraces, firstIndexCurlyBraces);
            } else {
                jsonExternalObject = jsonWithoutCurlyBraces.substring(0, firstIndexCurlyBraces) + "null";
                innerClassName = getInnerClassName(jsonWithoutCurlyBraces, firstIndexCurlyBraces);
            }
            arraysElementsKeyPlusValue = getArraysWithoutQuotes(jsonExternalObject);
        } else {
            arraysElementsKeyPlusValue = getArraysWithoutQuotes(jsonWithoutCurlyBraces);
        }
        for (int i = 0; i < arraysElementsKeyPlusValue.length; i++) {
            String[] arraysKeyAndValue = arraysElementsKeyPlusValue[i].split(":");
            keyValueMap.put(arraysKeyAndValue[0], arraysKeyAndValue[1]);
        }
        if (keyValueMap.containsKey(innerClassName)) {
            keyValueMap.put(innerClassName, jsonInnerObject);
        }
        for (Field field : instance.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(IgnoreFieldJson.class)) {
            } else if (Constants.CLASSES_WITHOUT_MAP_LIST_AND_SET.contains(field.getType().getSimpleName())) {
                ParseStringToSpecificType parserValue = parseFactory.createParser(field.getType());
                field.set(instance, parserValue.parseStringToSpecificType(keyValueMap.get(field.getName())));
            } else if (field.getType().getSimpleName().equals(Constants.CLASS_MAP)) {
                field.set(instance, ParsingMap.parsingAndCreatingMap(keyValueMap.get(field.getName()), field.getGenericType().getTypeName()));
            } else if(field.getType().getSimpleName().equals(Constants.CLASS_LIST)){
                String type = field.getGenericType().getTypeName().substring(25, field.getGenericType().getTypeName().length() - 1);
                ParseStringToSpecificListType parseValue = parseListFactory.createParser(type);
                field.set(instance, parseValue.parseStringToSpecificListType(keyValueMap.get(field.getName())));
            }else if(field.getType().getSimpleName().equals(Constants.CLASS_SET)){
                String type = field.getGenericType().getTypeName().substring(24, field.getGenericType().getTypeName().length() - 1);
                ParseStringToSpecificSetType parseValue = parseSetFactory.createParser(type);
                field.set(instance, parseValue.parseStringToSpecificSetType(keyValueMap.get(field.getName())));
            }
            else {
                if (keyValueMap.get(field.getName()).equals(Constants.NULL)) {
                    field.set(instance, null);
                } else {
                    field.set(instance, getObjectFromJson(keyValueMap.get(field.getName()), field.getType()));
                }
            }
        }
        return instance;
    }

    private static String getInnerClassName(String jsonWithoutCurlyBraces, int firstIndexCurlyBraces) {
        String stringConsistInnerClassName = jsonWithoutCurlyBraces.substring(0, firstIndexCurlyBraces);
        String stringWithoutQuotesAfterInnerClassName = stringConsistInnerClassName.substring(0, stringConsistInnerClassName.length()-2);
        int indexQuotesMarksBeforeInnerClassName = stringWithoutQuotesAfterInnerClassName.lastIndexOf("\"");
        return stringWithoutQuotesAfterInnerClassName.substring(indexQuotesMarksBeforeInnerClassName + 1);
    }

    private static String[] getArraysWithoutQuotes(String stringObject) {
        String[] arraysElementsKeyPlusValue = removeDoubleQuotesFromSquareBraces(stringObject).split(",\"");
        for (int i = 0; i < arraysElementsKeyPlusValue.length; i++) {
            arraysElementsKeyPlusValue[i] = arraysElementsKeyPlusValue[i].replace("\"", "");
            arraysElementsKeyPlusValue[i] = arraysElementsKeyPlusValue[i].replace("\'", "");
        }
        return arraysElementsKeyPlusValue;
    }

    public static String removeDoubleQuotesFromSquareBraces(String stringObject) {
        char[] arraysOfChar = stringObject.toCharArray();
        int numberOpenBraces = 0;
        int numberCloseBraces = 0;
        int indexOfOpenBraces;
        int indexOfCloseBraces;
        StringBuilder resultStringObject = new StringBuilder();

        for (int i = 0; i < arraysOfChar.length; i++) {
            if (arraysOfChar[i] == '[') {
                numberOpenBraces++;
            }
            if (arraysOfChar[i] == ']') {
                numberCloseBraces++;
            }
        }
        if (numberOpenBraces > 0 && numberOpenBraces == numberCloseBraces) {
            indexOfOpenBraces = stringObject.indexOf("[");
            indexOfCloseBraces = stringObject.indexOf("]");

            resultStringObject = resultStringObject.append(stringObject.substring(0, indexOfOpenBraces));
            String unverifiedStringObject = stringObject;
            int lastIndexVerifiedStringObject = resultStringObject.length();
            for (int i = 0; i < numberOpenBraces; i++) {
                String elementsInSquareBraces = unverifiedStringObject.substring(indexOfOpenBraces, indexOfCloseBraces + 1);
                String elementsInSquareBracesWithoutQuotes = elementsInSquareBraces.replaceAll("\"", "");
                resultStringObject = resultStringObject.append(elementsInSquareBracesWithoutQuotes);
                if (i == numberOpenBraces - 1) {
                    lastIndexVerifiedStringObject = lastIndexVerifiedStringObject + elementsInSquareBraces.length();
                    unverifiedStringObject = stringObject.substring(lastIndexVerifiedStringObject);
                    resultStringObject = resultStringObject.append(stringObject.substring(lastIndexVerifiedStringObject));
                    break;
                }
                lastIndexVerifiedStringObject = lastIndexVerifiedStringObject + elementsInSquareBraces.length();
                unverifiedStringObject = stringObject.substring(lastIndexVerifiedStringObject);
                indexOfOpenBraces = unverifiedStringObject.indexOf("[");
                indexOfCloseBraces = unverifiedStringObject.indexOf("]");
                resultStringObject = resultStringObject.append(unverifiedStringObject.substring(0, indexOfOpenBraces));
                lastIndexVerifiedStringObject = lastIndexVerifiedStringObject + indexOfOpenBraces;
            }
            return resultStringObject.toString();
        } else {
            return stringObject;
        }
    }
}
