package ru.clevertec.json.parser.impl.factory.parse_string;

import org.apache.commons.lang3.StringUtils;

public class ParseStringToCharacterType implements ParseStringToSpecificType{
    @Override
    public Character parseStringToSpecificType(String value) {
        if (value.equals("null")) {
            return null;
        }
        else if (StringUtils.isNumeric(value)){
            int i = Integer.parseInt(value);
            return (char) i;
        }else {
            return value.charAt(0);
        }
    }
}
