package ru.clevertec.json.parser.impl.factory.parse_string;

public class ParseStringToByteType implements ParseStringToSpecificType{
    @Override
    public Byte parseStringToSpecificType(String value) {
        if (value.equals("null")) {
            return null;
        } else {
            return Byte.parseByte(value);
        }
    }
}
