package ru.clevertec.json.parser.impl.factory.parse_string;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ParseStringToLocalDateType implements ParseStringToSpecificType{
    @Override
    public Object parseStringToSpecificType(String value) {
        if (value.equals("null")) {
            return null;
        } else {
            DateTimeFormatter formatterForLocalDate = DateTimeFormatter.ofPattern("yyyy,M,dd");
            return LocalDate.parse(value.substring(1,value.length()-1), formatterForLocalDate);
        }
    }
}
