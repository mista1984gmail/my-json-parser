package ru.clevertec.json.parser.impl.factory.parse_string;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ParseStringToLocalDateTimeType implements ParseStringToSpecificType{
    @Override
    public Object parseStringToSpecificType(String value) {
        if (value.equals("null")) {
            return null;
        } else {
            DateTimeFormatter formatterForLocalDate = DateTimeFormatter.ofPattern("yyyy,M,dd,HH,mm");
            return LocalDateTime.parse(value.substring(1,value.length()-1), formatterForLocalDate);
        }
    }
}
