**Проект: JSON парсер.**

**Функциональность:** 
- конвертирует строку, представленную в формате json, в объект;
- конвертирует объект, в строку в формате json.

**Поддерживает следующие типы данных в качестве полей объекта:**
- Byte, Short, Integer, Long, Float, Double, Character, Boolean, String, LocalDate, LocalDateTime;
- List, параметризированный следующими типами данных:
Byte, Short, Integer, Long, Float, Double, Character, Boolean, String;
- Set, параметризированный следующими типами данных:
Byte, Short, Integer, Long, Float, Double, Character, Boolean, String;
- Map, параметризированный следующими типами данных:
<String, String>, <String, Integer>, <Long, String>, <Long, Integer>, <Integer, Integer>
- Также поддерживаются другие пользовательские классы, имеющиеся в качестве полей
в конвертируемом объекте.

Поддерживает многоуровневую вложенность объектов друг в друга.

Работает с полем или объектом, если они null.

**Дополнительная функциональность:**
- Имеется возможность аннотировать пользовательской аннотацией @IgnoreFieldJson
те поля объекта, которые пользователь не хочет, чтобы отображались в json.

**Примеры использования:**

**Создаем json с обьекта:**

JSONParser jsonParser = new JSONParserImpl();

String personJson = jsonParser.convertObjectToJson(person);

**Создаем объект из json:**

JSONParser jsonParser = new JSONParserImpl();

Person person = jsonParser.convertJsonToObject(jsonPerson, Person.class);

**Аннотируем поле пользовательской аннотацией:**

@IgnoreFieldJson

private String name;
