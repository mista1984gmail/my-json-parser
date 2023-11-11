package ru.clevertec.json.parser.impl;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.clevertec.json.parser.JSONParser;
import ru.clevertec.json.parser.exception.ConvertingHasBeenBrokenException;
import ru.clevertec.json.parser.util.*;

import static org.junit.jupiter.api.Assertions.*;

class JSONParserImplTest {

    ObjectMapper objectMapper;
    JSONParser jsonParser;

    @BeforeEach
    void setUp(){
        jsonParser = new JSONParserImpl();
        objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        objectMapper.findAndRegisterModules();
    }

    @Test
    void convertJsonToObject_withAllSupportedTypes() throws JsonProcessingException {
        // given
        String json = ConstantsForTest.JSON_PERSON_TEST_DATA_ALL_SUPPORTED_TYPES;

        //when
        PersonTestDataAllSupportedTypes actual =
                jsonParser.convertJsonToObject(json, PersonTestDataAllSupportedTypes.class);
        PersonTestDataAllSupportedTypes expected =
                objectMapper.readValue(json, PersonTestDataAllSupportedTypes.class);

        //then
        assertEquals(expected, actual);
    }

    @Test
    void convertObjectToJson_withAllSupportedTypes() throws JsonProcessingException {
        // given
        PersonTestDataAllSupportedTypes personToConvertToJson = DataForTest.buildPersonTestData();

        //when
        String actual = jsonParser.convertObjectToJson(personToConvertToJson);
        String expected = objectMapper.writeValueAsString(personToConvertToJson);

        //then
        assertEquals(expected, actual);
    }

    @Test
    void convertObjectToJson_withNestedObjects() throws JsonProcessingException {
        // given
        ClientTestData clientToConvertToJson = DataForTest.buildClientTestData();

        //when
        String actual = jsonParser.convertObjectToJson(clientToConvertToJson);
        String expected = objectMapper.writeValueAsString(clientToConvertToJson);

        //then
        assertEquals(expected, actual);
    }

    @Test
    void convertJsonToObject_withNestedObjects() throws JsonProcessingException {
        // given
        String json = ConstantsForTest.JSON_CLIENT_TEST_NESTED_OBJECT;

        //when
        ClientTestData actual =
                jsonParser.convertJsonToObject(json, ClientTestData.class);
        ClientTestData expected =
                objectMapper.readValue(json, ClientTestData.class);

        //then
        assertEquals(expected, actual);
    }

    @Test
    void convertObjectToJson_withAnnotationIgnoreFieldJson() throws JsonProcessingException {
        // given
        ProductForTest productToConvertToJson = DataForTest.buildProductForTest();

        //when
        String actual = jsonParser.convertObjectToJson(productToConvertToJson);
        String expected = objectMapper.writeValueAsString(productToConvertToJson);

        //then
        assertEquals(expected, actual);
    }

    @Test
    void convertJsonToObject_withAnnotationIgnoreFieldJson() throws JsonProcessingException {
        // given
        String json = ConstantsForTest.JSON_PRODUCT;

        //when
        ProductForTest actual =
                jsonParser.convertJsonToObject(json, ProductForTest.class);
        ProductForTest expected =
                objectMapper.readValue(json, ProductForTest.class);

        //then
        assertEquals(expected, actual);
    }

    @Test
    void convertObjectToJson_withNullNestedObjectAndField() throws JsonProcessingException {
        // given
        ClientTestData clientToConvertToJson = DataForTest.buildClientTestDataNullObject();

        //when
        String actual = jsonParser.convertObjectToJson(clientToConvertToJson);
        String expected = objectMapper.writeValueAsString(clientToConvertToJson);

        //then
        assertEquals(expected, actual);
    }

    @Test
    void convertJsonToObject_withNullNestedObjectAndField() throws JsonProcessingException {
        // given
        String json = ConstantsForTest.JSON_CLIENT_TEST_NULL_NESTED_OBJECT;

        //when
        ClientTestData actual =
                jsonParser.convertJsonToObject(json, ClientTestData.class);
        ClientTestData expected =
                objectMapper.readValue(json, ClientTestData.class);

        //then
        assertEquals(expected, actual);
    }

    @Test
    void throwJsonNullExceptionWhenConvertJsonToObject_withNullJSON() {
        // given
        String json = null;
        String errorMessages = "Converting has been broken: JSON is null!!!";

        //when
        ConvertingHasBeenBrokenException thrown = Assertions.assertThrows(ConvertingHasBeenBrokenException.class, () -> {
            jsonParser.convertJsonToObject(json, ClientTestData.class);
        });

        //then
        Assertions.assertEquals(errorMessages, thrown.getMessage());
    }
    @Test
    void throwWrongJsonStructureExceptionWhenConvertJsonToObject_withoutFirstBracket(){
        // given
        String json = ConstantsForTest.JSON_PRODUCT_WITHOUT_FIRST_BRACKET;
        String errorMessages = "Converting has been broken: JSON has the wrong structure!!!";

        //when
        ConvertingHasBeenBrokenException thrown = Assertions.assertThrows(ConvertingHasBeenBrokenException.class, () -> {
            jsonParser.convertJsonToObject(json, ClientTestData.class);
        });

        //then
        Assertions.assertEquals(errorMessages, thrown.getMessage());
    }

    @Test
    void throwWrongJsonStructureExceptionWhenConvertJsonToObject_whenJsonHasNotEvenNumberCurlyBraces() {
        // given
        String json = ConstantsForTest.JSON_PRODUCT_WITH_NOT_EVEN_CURLY_BRACKET;
        String errorMessages = "Converting has been broken: JSON has not even number curly braces!!!";

        //when
        ConvertingHasBeenBrokenException thrown = Assertions.assertThrows(ConvertingHasBeenBrokenException.class, () -> {
            jsonParser.convertJsonToObject(json, ClientTestData.class);
        });

        //then
        Assertions.assertEquals(errorMessages, thrown.getMessage());
    }

    @Test
    void throwWrongJsonStructureExceptionWhenConvertJsonToObject_whenJsonHasNotEvenNumberSquareBraces() {
        // given
        String json = ConstantsForTest.JSON_CLIENT_WITH_NOT_EVEN_SQUARE_BRACKET;
        String errorMessages = "Converting has been broken: JSON has not even number square braces!!!";

        //when
        ConvertingHasBeenBrokenException thrown = Assertions.assertThrows(ConvertingHasBeenBrokenException.class, () -> {
            jsonParser.convertJsonToObject(json, ClientTestData.class);
        });

        //then
        Assertions.assertEquals(errorMessages, thrown.getMessage());
    }

}