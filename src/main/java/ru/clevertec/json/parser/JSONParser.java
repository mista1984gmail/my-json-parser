package ru.clevertec.json.parser;

public interface JSONParser {
    /**
     * Конвертирует строку, представленную в формате json, в объект
     *
     * Поддерживает следующие типы данных в качестве полей объекта:
     * Byte, Short, Integer, Long, Float, Double, Character, Boolean, String, LocalDate, LocalDateTime;
     * List, параметризированный следующими типами данных:
     * Byte, Short, Integer, Long, Float, Double, Character, Boolean, String;
     * Set, параметризированный следующими типами данных:
     * Byte, Short, Integer, Long, Float, Double, Character, Boolean, String;
     * Map, параметризированный следующими типами данных:
     * <String, String>, <String, Integer>, <Long, String>, <Long, Integer>, <Integer, Integer>
     * Также поддерживаются другие пользовательские классы, имеющиеся в качестве полей
     * в конвертируемом объекте
     * Поддерживает многоуровневую вложенность объектов друг в друга
     *
     * Работает с полем или объектом, если они null
     *
     * Имеется возможность аннотировать пользовательской аннотацией @IgnoreFieldJson
     * те поля объекта, которые пользователь не хочет, чтобы отображались в json
     *
     *
     * @param json String - строка в формате json
     * @param tClass Class - класс объекта, в который необходимо конвертировать json
     * @return объект класса tClass, полученный в результате конвертации
     */
    <T> T convertJsonToObject(String json, Class<T> tClass) throws RuntimeException;

    /**
     * Конвертирует объект, в строку в формате json
     *
     * Поддерживает следующие типы данных в качестве полей объекта:
     * Byte, Short, Integer, Long, Float, Double, Character, Boolean, String, LocalDate, LocalDateTime;
     * List, параметризированный следующими типами данных:
     * Byte, Short, Integer, Long, Float, Double, Character, Boolean, String;
     * Set, параметризированный следующими типами данных:
     * Byte, Short, Integer, Long, Float, Double, Character, Boolean, String;
     * Map, параметризированный следующими типами данных:
     * <String, String>, <String, Integer>, <Long, String>, <Long, Integer>, <Integer, Integer>
     * Также поддерживаются другие пользовательские классы, имеющиеся в качестве полей
     * в конвертируемом объекте
     * Поддерживает многоуровневую вложенность объектов друг в друга
     *
     * Работает с полем или объектом, если они null
     *
     * Имеется возможность аннотировать пользовательской аннотацией @IgnoreFieldJson
     * те поля объекта, которые пользователь не хочет, чтобы отображались в json
     *
     *
     * @param o Object - объект, который необходимо конвертировать в json
     * @return String - строку в формате json
     */
    String convertObjectToJson(Object o) throws RuntimeException;
}
