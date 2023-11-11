package ru.clevertec.json.parser.impl.factory.parse_string;

public class ParseStringToLongType implements ParseStringToSpecificType{
    @Override
    public Long parseStringToSpecificType(String value) {
        if (value.equals("null")) {
            return null;
        } else {
            return Long.parseLong(value);
        }
    }
}
