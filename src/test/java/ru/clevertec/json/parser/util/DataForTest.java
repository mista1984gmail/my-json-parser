package ru.clevertec.json.parser.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DataForTest {

    public static ProductForTest buildProductForTest(){
        return new ProductForTest(
                1L,
                "white cup of tea",
                "cup",
                15.99d,
                "12345678910"
        );
    }
    public static ClientTestData buildClientTestDataNullObject(){
        return new ClientTestData(
                1L,
                "Ivan",
                null,
                LocalDate.of(2021, Month.SEPTEMBER, 12),
                "ivan@email.com",
                List.of("+375291234567", "+375297654321", "+375291111111"),
                null
        );
    }

    public static ClientTestData buildClientTestData(){
        return new ClientTestData(
                1L,
                "Ivan",
                "Ivanov",
                LocalDate.of(2021, Month.SEPTEMBER, 12),
                "ivan@email.com",
                List.of("+375291234567", "+375297654321", "+375291111111"),
                buildAddressTestData()
                );
    }

    public static AddressTestData buildAddressTestData(){
        return new AddressTestData(
                1L,
                buildCountryForTest(),
                "Minsk",
                "Korotkevicha",
                154
        );
    }
    public static CountryForTest buildCountryForTest(){
        return new CountryForTest(
                1L,
                17,
                "Belarus",
                List.of(45.5d, 78.23d, 58.2d, 89.4d)
        );
    }

    public static PersonTestDataAllSupportedTypes buildPersonTestData(){

        return new PersonTestDataAllSupportedTypes(
                "Name",
                39,
                (byte) 73,
                (short) 25649,
                12_000_000L,
                15.89f,
                799.12d,
                'A',
                true,
                LocalDate.of(1984, Month.SEPTEMBER, 12),
                LocalDateTime.of(1984,1,15,15,35),
                Set.of("first","second"),
                List.of(1,2,3,4,5,6,7,8,9),
                getMap());
    }

    public static Map<Long, String>getMap(){
        Map<Long, String> str = new HashMap<>();
        str.put(1L, "first");
        str.put(2L, "second");
        return str;
    }


}
