package ru.clevertec.json.parser.impl.handler.fill_map;

import ru.clevertec.json.parser.util.Constants;

import java.lang.reflect.Field;
import java.util.Map;

public class MapHandlerFillMap extends HandlerFillMap{
    @Override
    public void handleFillMap(Map<String, Object> jsonElements, Object object, Field field) throws IllegalAccessException {
        if (Constants.LIST_OF_MAPS.contains(getSimpleNameClass(object, field))) {
            String types = field.getGenericType().getTypeName();
            String [] keyValueTypeMap = types.substring(24, types.length()-1)
                    .replace(" java.lang.", "")
                    .split(",");
            String valueType = keyValueTypeMap[1];
            jsonElements.put(field.getName() + "." + valueType, field.get(object));
        } else if (next != null) {
            next.handleFillMap(jsonElements, object, field);
        }
    }
    private static String getSimpleNameClass(Object object, Field field) throws IllegalAccessException {
        return field.get(object).getClass().getSimpleName();
    }
}
