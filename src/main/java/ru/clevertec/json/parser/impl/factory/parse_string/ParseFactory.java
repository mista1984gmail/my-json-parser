package ru.clevertec.json.parser.impl.factory.parse_string;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ParseFactory {
    public ParseStringToSpecificType createParser(Class<?> clazz) {
        ParseStringToSpecificType parseStringToSpecificType = null;

        if(String.class.isAssignableFrom(clazz)){
            parseStringToSpecificType = new ParseStringToStringType();
        }
        else if(Integer.class.isAssignableFrom(clazz)){
            parseStringToSpecificType = new ParseStringToIntegerType();
        }
        else if(Double.class.isAssignableFrom(clazz)){
            parseStringToSpecificType = new ParseStringToDoubleType();
        }
        else if(Byte.class.isAssignableFrom(clazz)){
            parseStringToSpecificType = new ParseStringToByteType();
        }
        else if(Short.class.isAssignableFrom(clazz)){
            parseStringToSpecificType = new ParseStringToShortType();
        }
        else if(Long.class.isAssignableFrom(clazz)){
            parseStringToSpecificType = new ParseStringToLongType();
        }
        else if(Float.class.isAssignableFrom(clazz)){
            parseStringToSpecificType = new ParseStringToFloatType();
        }
        else if(Character.class.isAssignableFrom(clazz)){
            parseStringToSpecificType = new ParseStringToCharacterType();
        }
        else if(Boolean.class.isAssignableFrom(clazz)){
            parseStringToSpecificType = new ParseStringToBooleanType();
        }
        else if(LocalDate.class.isAssignableFrom(clazz)){
            parseStringToSpecificType = new ParseStringToLocalDateType();
        }
        else if(LocalDateTime.class.isAssignableFrom(clazz)){
            parseStringToSpecificType = new ParseStringToLocalDateTimeType();
        }
        return parseStringToSpecificType;
    }
}
