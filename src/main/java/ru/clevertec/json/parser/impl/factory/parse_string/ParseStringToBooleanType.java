package ru.clevertec.json.parser.impl.factory.parse_string;

public class ParseStringToBooleanType implements ParseStringToSpecificType{
    @Override
    public Boolean parseStringToSpecificType(String value) {
        if (value.equals("null")) {
            return null;
        } else {
            return value.equals("true");
        }
    }
}
