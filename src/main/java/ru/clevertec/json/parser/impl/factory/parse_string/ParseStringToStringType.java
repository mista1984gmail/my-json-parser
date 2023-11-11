package ru.clevertec.json.parser.impl.factory.parse_string;

public class ParseStringToStringType implements ParseStringToSpecificType{
    @Override
    public String parseStringToSpecificType(String value) {
        if (value.equals("null")) {
            return null;
        } else {
            return value;
        }
    }
}
