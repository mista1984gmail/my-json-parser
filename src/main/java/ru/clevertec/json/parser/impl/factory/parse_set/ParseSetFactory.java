package ru.clevertec.json.parser.impl.factory.parse_set;


import ru.clevertec.json.parser.util.Constants;

public class ParseSetFactory {
    public ParseStringToSpecificSetType createParser(String type) {
        ParseStringToSpecificSetType parseStringToSpecificSetType = null;

        if(type.equals(Constants.CLASS_STRING)){
            parseStringToSpecificSetType = new ParseStringToStringSetType();
        }
        else if(type.equals(Constants.CLASS_BYTE)){
            parseStringToSpecificSetType = new ParseStringToByteSetType();
        }
        else if(type.equals(Constants.CLASS_SHORT)){
            parseStringToSpecificSetType = new ParseStringToShortSetType();
        }
        else if(type.equals(Constants.CLASS_INTEGER)){
            parseStringToSpecificSetType = new ParseStringToIntegerSetType();
        }
        else if(type.equals(Constants.CLASS_LONG)){
            parseStringToSpecificSetType = new ParseStringToLongSetType();
        }
        else if(type.equals(Constants.CLASS_FLOAT)){
            parseStringToSpecificSetType = new ParseStringToFloatSetType();
        }
        else if(type.equals(Constants.CLASS_BOOLEAN)){
            parseStringToSpecificSetType = new ParseStringToBooleanSetType();
        }
        else if(type.equals(Constants.CLASS_DOUBLE)){
            parseStringToSpecificSetType = new ParseStringToDoubleSetType();
        }
        else if(type.equals(Constants.CLASS_CHARACTER)){
            parseStringToSpecificSetType = new ParseStringToCharacterSetType();
        }
        return parseStringToSpecificSetType;
}
}

