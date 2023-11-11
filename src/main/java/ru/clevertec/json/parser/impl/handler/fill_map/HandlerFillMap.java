package ru.clevertec.json.parser.impl.handler.fill_map;

import java.lang.reflect.Field;
import java.util.Map;

public abstract  class HandlerFillMap {
    protected HandlerFillMap next;

    public void setNext(HandlerFillMap next) {
        this.next = next;
    }

    public abstract void handleFillMap(Map<String, Object> jsonElements, Object object, Field field) throws IllegalAccessException;
}
