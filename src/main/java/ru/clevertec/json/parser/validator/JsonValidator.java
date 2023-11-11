package ru.clevertec.json.parser.validator;

public class JsonValidator {
    public static boolean checkingEvenNumberBraces(String json, char openBrace, char closeBrace){
        char [] arrays = json.toCharArray();
        int numberOpenBraces = 0;
        int numberCloseBraces = 0;
        for (int i = 0; i < arrays.length; i++) {
            if(arrays[i]==openBrace){
                numberOpenBraces++;
            }
            if(arrays[i]==closeBrace){
                numberCloseBraces++;
            }
        }
        return numberOpenBraces != numberCloseBraces;
    }
}
