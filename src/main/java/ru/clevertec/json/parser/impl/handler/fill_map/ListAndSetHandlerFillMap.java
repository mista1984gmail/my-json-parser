package ru.clevertec.json.parser.impl.handler.fill_map;

import ru.clevertec.json.parser.util.Constants;

import java.lang.reflect.Field;
import java.util.Map;

public class ListAndSetHandlerFillMap extends HandlerFillMap {
    @Override
    public void handleFillMap(Map<String, Object> jsonElements, Object object, Field field) throws IllegalAccessException {
        if (Constants.CLASSES_LIST_AND_SET.contains(getSimpleNameClass(object, field))) {
            putElementsForListAndSet(object, jsonElements, field);
        } else if (next != null) {
            next.handleFillMap(jsonElements, object, field);
        }
    }

    private static void putElementsForListAndSet(Object object, Map<String, Object> jsonElements, Field field) throws IllegalAccessException {
        String simpleNameOfClass = getSimpleNameClass(object, field);
        int startIndexForSubstringType = 0;
        if(simpleNameOfClass.equals(Constants.CLASS_LIST_N)){
            startIndexForSubstringType = Constants.START_INDEX_FOR_SUBSTRING_LIST;
        }
        if(simpleNameOfClass.equals(Constants.CLASS_SET_12)){
            startIndexForSubstringType = Constants.START_INDEX_FOR_SUBSTRING_SET;
        }
        String genericType = field.getGenericType().getTypeName();
        String genericTypeSimpleName = genericType.substring(startIndexForSubstringType, genericType.length()-1);
        if(Constants.CLASSES_WITH_QUOTES.contains(genericTypeSimpleName)){
            jsonElements.put(field.getName() + genericTypeSimpleName, field.get(object));}
        else {
            jsonElements.put(field.getName(), field.get(object));}
    }

    private static String getSimpleNameClass(Object object, Field field) throws IllegalAccessException {
        return field.get(object).getClass().getSimpleName();
    }
}
