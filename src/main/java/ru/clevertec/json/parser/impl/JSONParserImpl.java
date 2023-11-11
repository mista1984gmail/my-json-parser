package ru.clevertec.json.parser.impl;

import ru.clevertec.json.parser.JSONParser;
import ru.clevertec.json.parser.exception.ConvertingHasBeenBrokenException;
import ru.clevertec.json.parser.exception.JsonNullException;
import ru.clevertec.json.parser.exception.ObjectNullException;
import ru.clevertec.json.parser.exception.WrongJsonStructureException;
import ru.clevertec.json.parser.validator.JsonValidator;

public class JSONParserImpl implements JSONParser {

    JsonToObject jsonToObject = new JsonToObject();
    ObjectToJson objectToJson = new ObjectToJson();
    @Override
    public  <T> T convertJsonToObject(String json, Class<T> tClass) throws RuntimeException {
        try {
            if (json == null) {
                throw new JsonNullException("JSON is null!!!");
            } else if((!json.startsWith("{")) || (!json.endsWith("}"))){
                throw new WrongJsonStructureException("JSON has the wrong structure!!!");
            } else if(JsonValidator.checkingEvenNumberBraces(json, '{', '}')){
            throw new WrongJsonStructureException("JSON has not even number curly braces!!!");
            }else if(JsonValidator.checkingEvenNumberBraces(json, '[', ']')){
                throw new WrongJsonStructureException("JSON has not even number square braces!!!");
            }
            return jsonToObject.getObjectFromJson(json, tClass);
        }
        catch (Exception e){
            throw new ConvertingHasBeenBrokenException("Converting has been broken: " + e.getMessage());
        }
    }

    @Override
    public String convertObjectToJson(Object o) throws RuntimeException {
        try {
            if(o == null){
                throw new ObjectNullException("Object is null");
            }
            return objectToJson.getJsonFromObject(o);
        } catch (Exception e){
            throw new ConvertingHasBeenBrokenException("Converting has been broken: " + e.getMessage());
        }
    }
}
