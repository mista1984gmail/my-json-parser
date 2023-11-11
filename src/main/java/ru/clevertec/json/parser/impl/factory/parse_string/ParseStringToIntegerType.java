package ru.clevertec.json.parser.impl.factory.parse_string;

public class ParseStringToIntegerType implements ParseStringToSpecificType{
    @Override
    public Integer parseStringToSpecificType(String value) {
        if (value.equals("null")) {
            return null;
        } else {
            return Integer.parseInt(value);
        }
    }
}
