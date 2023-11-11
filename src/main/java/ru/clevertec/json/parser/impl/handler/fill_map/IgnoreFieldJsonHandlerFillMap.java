package ru.clevertec.json.parser.impl.handler.fill_map;


import ru.clevertec.json.parser.annotation.IgnoreFieldJson;

import java.lang.reflect.Field;
import java.util.Map;

public class IgnoreFieldJsonHandlerFillMap extends HandlerFillMap {
    @Override
    public void handleFillMap(Map<String, Object> jsonElements, Object object, Field field) throws IllegalAccessException {
        if (field.isAnnotationPresent(IgnoreFieldJson.class)) {
        } else if (next != null) {
            next.handleFillMap(jsonElements, object, field);
        }
    }
}
