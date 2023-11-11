package ru.clevertec.json.parser.impl.factory.parse_string;

public class ParseStringToShortType implements ParseStringToSpecificType{
    @Override
    public Short parseStringToSpecificType(String value) {
        if (value.equals("null")) {
            return null;
        } else {
            return Short.parseShort(value);
        }
    }
}
