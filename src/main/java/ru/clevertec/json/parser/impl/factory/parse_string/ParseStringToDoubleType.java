package ru.clevertec.json.parser.impl.factory.parse_string;

public class ParseStringToDoubleType implements ParseStringToSpecificType{
    @Override
    public Double parseStringToSpecificType(String value) {
        if (value.equals("null")) {
            return null;
        } else {
            return Double.parseDouble(value);
        }
    }
}
