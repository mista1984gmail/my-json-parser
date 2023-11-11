package ru.clevertec.json.parser.util;

import java.util.List;
import java.util.Objects;

public class CountryForTest {
    private Long id;
    private Integer index;
    private String name;

    private List<Double> coordinates;

    public CountryForTest(Long id, Integer index, String name, List<Double> coordinates) {
        this.id = id;
        this.index = index;
        this.name = name;
        this.coordinates = coordinates;
    }

    public CountryForTest() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountryForTest that = (CountryForTest) o;
        return Objects.equals(id, that.id) && Objects.equals(index, that.index) && Objects.equals(name, that.name) && Objects.equals(coordinates, that.coordinates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, index, name, coordinates);
    }
}
