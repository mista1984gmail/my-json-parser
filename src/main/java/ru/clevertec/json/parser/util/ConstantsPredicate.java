package ru.clevertec.json.parser.util;

import java.util.function.Predicate;

public class ConstantsPredicate {
    public final static Predicate<String> PREDICATE_CLASSES_WITHOUT_QUOTES = Constants.CLASSES_WITHOUT_QUOTES::contains;
    public final static Predicate<String> PREDICATE_VALUE_IS_NULL = (value) -> value.equals("null");
    public final static Predicate<String> PREDICATE_CLASS_EQUALS_LOCAL_DATE = (simpleName) -> simpleName.equals(Constants.CLASS_LOCAL_DATE);
    public final static Predicate<String> PREDICATE_CLASS_EQUALS_LOCAL_DATE_TIME = (simpleName) -> simpleName.equals(Constants.CLASS_LOCAL_DATE_TIME);
    public final static Predicate<String> PREDICATE_CLASS_EQUALS_SET = (simpleName) -> simpleName.equals(Constants.CLASS_SET_12);
    public final static Predicate<String> PREDICATE_CLASS_EQUALS_LIST = (simpleName) -> simpleName.equals(Constants.CLASS_LIST_N);
    public final static Predicate<String> PREDICATE_CLASS_EQUALS_STRING = (simpleName) -> simpleName.equals(Constants.CLASS_STRING);
    public final static Predicate<String> PREDICATE_CLASS_EQUALS_CHARACTER = (simpleName) -> simpleName.equals(Constants.CLASS_CHARACTER);
    public final static Predicate<String> PREDICATE_CLASS_EQUALS_MAP = Constants.LIST_OF_MAPS::contains;
}
