package ru.clevertec.json.parser.util;

import java.util.Objects;

public class AddressTestData {
    private Long id;
    private CountryForTest country;
    private String city;
    private String street;
    private Integer numberOfHouse;

    public AddressTestData(Long id, CountryForTest country, String city, String street, Integer numberOfHouse) {
        this.id = id;
        this.country = country;
        this.city = city;
        this.street = street;
        this.numberOfHouse = numberOfHouse;
    }

    public AddressTestData() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressTestData that = (AddressTestData) o;
        return Objects.equals(id, that.id) && Objects.equals(country, that.country) && Objects.equals(city, that.city) && Objects.equals(street, that.street) && Objects.equals(numberOfHouse, that.numberOfHouse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, country, city, street, numberOfHouse);
    }
}
