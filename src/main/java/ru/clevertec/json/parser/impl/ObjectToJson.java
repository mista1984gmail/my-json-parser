package ru.clevertec.json.parser.impl;

import ru.clevertec.json.parser.impl.handler.fill_map.*;
import ru.clevertec.json.parser.util.Constants;
import ru.clevertec.json.parser.util.ConstantsPredicate;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringJoiner;

public class ObjectToJson {

    public String getJsonFromObject(Object object) throws Exception{
        Map<String, Object> jsonElements = new LinkedHashMap<>();
        HandlerFillMap objectNullHandler = getHandlerFillMap();
        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            objectNullHandler.handleFillMap(jsonElements, object, field);
        }
        StringJoiner joiner = new StringJoiner(",");
        createJsonFromMap(jsonElements, joiner);

        return String.format("{%s}", joiner);
    }

    private void createJsonFromMap(Map<String, Object> jsonElements, StringJoiner joiner) throws Exception {
        for(Map.Entry<String, Object> entry : jsonElements.entrySet()){
            Class<?> clazz = entry.getValue().getClass();
            String simpleNameOfClass = clazz.getSimpleName();
            String element = "";

            if(ConstantsPredicate.PREDICATE_CLASSES_WITHOUT_QUOTES.test(simpleNameOfClass)
                    || ConstantsPredicate.PREDICATE_VALUE_IS_NULL.test(entry.getValue().toString())) {
                element = getElementWithoutQuotes(entry);
            }
            else if(ConstantsPredicate.PREDICATE_CLASS_EQUALS_LOCAL_DATE.test(simpleNameOfClass)
                    || ConstantsPredicate.PREDICATE_CLASS_EQUALS_LOCAL_DATE_TIME.test(simpleNameOfClass)){
                element = getElementDateOrDateTime(entry, simpleNameOfClass);
            }
            else if(ConstantsPredicate.PREDICATE_CLASS_EQUALS_LIST.test(simpleNameOfClass)
                    || ConstantsPredicate.PREDICATE_CLASS_EQUALS_SET.test(simpleNameOfClass)){
                element = getElementListOrSet(entry);
            }
            else if(ConstantsPredicate.PREDICATE_CLASS_EQUALS_STRING.test(simpleNameOfClass)
                    || ConstantsPredicate.PREDICATE_CLASS_EQUALS_CHARACTER.test(simpleNameOfClass)){
                element = getElementWithQuotes(entry);
            }
            else if(ConstantsPredicate.PREDICATE_CLASS_EQUALS_MAP.test(simpleNameOfClass)){
                element = getStringFromMap(entry);
            }
            else {
                element = "\"" + entry.getKey() + "\":" + getJsonFromObject(entry.getValue());
            }
            joiner.add(element);
        }
    }

    private static String getStringFromMap(Map.Entry<String, Object> entry) {
        String elementMap;
        String getValueWithoutCurlyBrackets = entry.getValue().toString().substring(1, entry.getValue().toString().length() -1);
        StringJoiner joiner = new StringJoiner(",");
        int indexOfPoint = entry.getKey().lastIndexOf(".");
        String [] keyPlusValueArrays = getValueWithoutCurlyBrackets.split(", ");
        if(entry.getKey().endsWith(Constants.CLASS_STRING) || entry.getKey().endsWith(Constants.CLASS_CHARACTER)){
            for (int i = 0; i < keyPlusValueArrays.length; i++) {
                String keyAndValue [] = keyPlusValueArrays[i].split("=");
                joiner.add(keyAndValue[1].equals(Constants.NULL) ?
                        String.format("\"%s\":%s", keyAndValue[0], keyAndValue[1]) :
                        String.format("\"%s\":\"%s\"", keyAndValue[0], keyAndValue[1]));
            }
        }else {
            for (int i = 0; i < keyPlusValueArrays.length; i++) {
                String keyAndValue [] = keyPlusValueArrays[i].split("=");
                joiner.add(String.format("\"%s\":%s", keyAndValue[0], keyAndValue[1]));
            }
        }
        elementMap = String.format("\"%s\":{%s}",entry.getKey().substring(0, indexOfPoint), joiner);
        return elementMap;
    }

    private static HandlerFillMap getHandlerFillMap() {
        HandlerFillMap objectNullHandler = new ObjectNullHandlerFillMap();
        HandlerFillMap ignoreFieldJsonHandler = new IgnoreFieldJsonHandlerFillMap();
        HandlerFillMap listAndSetHandler = new ListAndSetHandlerFillMap();
        HandlerFillMap mapHandler = new MapHandlerFillMap();
        HandlerFillMap defaultHandler = new DefaultHandlerFillMap();

        objectNullHandler.setNext(ignoreFieldJsonHandler);
        ignoreFieldJsonHandler.setNext(listAndSetHandler);
        listAndSetHandler.setNext(mapHandler);
        mapHandler.setNext(defaultHandler);

        return objectNullHandler;
    }

    private static String getElementWithQuotes(Map.Entry<String, Object> entry) {
        return String.format("\"%s\":\"%s\"",
                entry.getKey(),
                entry.getValue());
    }

    private static String getElementListOrSet(Map.Entry<String, Object> entry) {
        String elementListOrSet = "";
        int endOfIndexForSubstring = entry.getKey().endsWith(Constants.CLASS_STRING) ? 6 : 9;
        if(entry.getKey().endsWith(Constants.CLASS_STRING) || entry.getKey().endsWith(Constants.CLASS_CHARACTER)){
            String getValueWithoutSquareBrackets = entry.getValue().toString()
                    .substring(1, entry.getValue().toString().length()-1);
            String [] arraysOfElements = getValueWithoutSquareBrackets.split(", ");
            StringBuilder sb = new StringBuilder();
            for (String array : arraysOfElements) {
                sb.append("\"").append(array).append("\",");
            }
            elementListOrSet = String.format("\"%s\":[%s]",
                    entry.getKey().substring(0, entry.getKey().length()-endOfIndexForSubstring),
                    sb.substring(0, sb.toString().length()-1));
        }else {
            String[] arraysOfElements = entry.getValue().toString().split(", ");
            StringBuilder sb = new StringBuilder();
            for (String array : arraysOfElements) {
                sb.append(array).append(",");
            }
            elementListOrSet = String.format("\"%s\":%s",
                    entry.getKey(),
                    sb.substring(0, sb.toString().length() - 1));
        }
        return elementListOrSet;
    }

    private String getElementDateOrDateTime(Map.Entry<String, Object> entry, String simpleNameOfClass) {
        DateTimeFormatter formatterForLocalDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter formatterForLocalDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        String elementDate = "";
        if (simpleNameOfClass.equals(Constants.CLASS_LOCAL_DATE)){
            LocalDate formatLocalDate = LocalDate.parse(entry.getValue().toString(), formatterForLocalDate);
            elementDate = formatLocalDate.format(DateTimeFormatter.ofPattern("yyyy,M,dd"));
        }
        if (simpleNameOfClass.equals(Constants.CLASS_LOCAL_DATE_TIME)){
            LocalDateTime formatLocalDateTime = LocalDateTime.parse(entry.getValue().toString(), formatterForLocalDateTime);
            elementDate = formatLocalDateTime.format(DateTimeFormatter.ofPattern("yyyy,M,dd,HH,mm"));
        }
        return String.format("\"%s\":[%s]", entry.getKey(), elementDate);
    }

    private static String getElementWithoutQuotes(Map.Entry<String, Object> entry) {
        return String.format("\"%s\":%s", entry.getKey(), entry.getValue());
    }
}
