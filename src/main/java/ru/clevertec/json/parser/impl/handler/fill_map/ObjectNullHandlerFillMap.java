package ru.clevertec.json.parser.impl.handler.fill_map;

import java.lang.reflect.Field;
import java.util.Map;

public class ObjectNullHandlerFillMap extends HandlerFillMap {
    @Override
    public void handleFillMap(Map<String, Object> jsonElements, Object object, Field field) throws IllegalAccessException {
        if (field.get(object) == null) {
            jsonElements.put(field.getName(),"null");
        } else if (next != null) {
            next.handleFillMap(jsonElements, object, field);
        }
    }
}
