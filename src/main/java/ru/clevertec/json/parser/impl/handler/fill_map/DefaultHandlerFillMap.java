package ru.clevertec.json.parser.impl.handler.fill_map;

import java.lang.reflect.Field;
import java.util.Map;

public class DefaultHandlerFillMap extends HandlerFillMap {
    @Override
    public void handleFillMap(Map<String, Object> jsonElements, Object object, Field field) throws IllegalAccessException {
        jsonElements.put(field.getName(),field.get(object));
    }
}
