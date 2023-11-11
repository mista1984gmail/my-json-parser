package ru.clevertec.json.parser.util;

import java.util.List;

public class Constants {
    public final static List<String> CLASSES_WITHOUT_MAP_LIST_AND_SET = List.of("Byte", "Short", "Integer", "Long", "Float", "Double", "Boolean", "String", "Character", "LocalDate", "LocalDateTime");
    public final static List<String> CLASSES_WITHOUT_QUOTES = List.of("Byte", "Short", "Integer", "Long", "Float", "Double", "Boolean");
    public final static List<String> CLASSES_WITH_QUOTES = List.of("String", "Character");
    public final static List<String> CLASSES_LIST_AND_SET = List.of("ListN", "Set12");
    public final static int START_INDEX_FOR_SUBSTRING_LIST = 25;
    public final static int START_INDEX_FOR_SUBSTRING_SET= 24;
    public final static String CLASS_LIST_N = "ListN";
    public final static String CLASS_LIST = "List";
    public final static String CLASS_INTEGER = "Integer";
    public final static String CLASS_BYTE = "Byte";
    public final static String CLASS_SHORT = "Short";
    public final static String CLASS_LONG = "Long";
    public final static String CLASS_FLOAT = "Float";
    public final static String CLASS_DOUBLE = "Double";
    public final static String CLASS_BOOLEAN= "Boolean";
    public final static String CLASS_SET_12 = "Set12";
    public final static String CLASS_LOCAL_DATE = "LocalDate";
    public final static String CLASS_LOCAL_DATE_TIME = "LocalDateTime";
    public final static String CLASS_STRING = "String";
    public final static String CLASS_CHARACTER = "Character";
    public final static String CLASS_MAP = "Map";
    public final static String CLASS_LINKED_HASH_MAP = "LinkedHashMap";
    public final static String CLASS_HASH_MAP = "HashMap";
    public final static String CLASS_TREE_MAP = "TreeMap";
    public final static List<String> LIST_OF_MAPS = List.of("LinkedHashMap", "HashMap", "TreeMap");
    public final static String CLASS_SET= "Set";
    public final static String NULL = "null";
}
