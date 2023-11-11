package ru.clevertec.json.parser.impl.factory.parse_list;

import ru.clevertec.json.parser.util.Constants;

public class ParseListFactory {
    public ParseStringToSpecificListType createParser(String type) {
        ParseStringToSpecificListType parseStringToSpecificListType = null;

        if(type.equals(Constants.CLASS_STRING)){
            parseStringToSpecificListType = new ParseStringToStringListType();
        }
        else if(type.equals(Constants.CLASS_BYTE)){
            parseStringToSpecificListType = new ParseStringToByteListType();
        }
        else if(type.equals(Constants.CLASS_SHORT)){
            parseStringToSpecificListType = new ParseStringToShortListType();
        }
        else if(type.equals(Constants.CLASS_INTEGER)){
            parseStringToSpecificListType = new ParseStringToIntegerListType();
        }
        else if(type.equals(Constants.CLASS_LONG)){
            parseStringToSpecificListType = new ParseStringToLongListType();
        }
        else if(type.equals(Constants.CLASS_FLOAT)){
            parseStringToSpecificListType = new ParseStringToFloatListType();
        }
        else if(type.equals(Constants.CLASS_BOOLEAN)){
            parseStringToSpecificListType = new ParseStringToBooleanListType();
        }
        else if(type.equals(Constants.CLASS_DOUBLE)){
            parseStringToSpecificListType = new ParseStringToDoubleListType();
        }
        else if(type.equals(Constants.CLASS_CHARACTER)){
            parseStringToSpecificListType = new ParseStringToCharacterListType();
        }
        return parseStringToSpecificListType;
}
}

