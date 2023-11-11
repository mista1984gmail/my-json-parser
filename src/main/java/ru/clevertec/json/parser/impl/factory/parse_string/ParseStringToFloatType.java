package ru.clevertec.json.parser.impl.factory.parse_string;

public class ParseStringToFloatType implements ParseStringToSpecificType{
    @Override
    public Float parseStringToSpecificType(String value) {
        if (value.equals("null")) {
            return null;
        } else {
            return Float.parseFloat(value);
        }
    }
}
