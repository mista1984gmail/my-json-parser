package ru.clevertec.json.parser.util;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ru.clevertec.json.parser.annotation.IgnoreFieldJson;

import java.util.Objects;

public class ProductForTest {
    private Long id;
    private String fullName;
    @JsonIgnore
    @IgnoreFieldJson
    private String simplyName;
    private Double price;
    private String barcode;

    public ProductForTest(Long id, String fullName, String simplyName, Double price, String barcode) {
        this.id = id;
        this.fullName = fullName;
        this.simplyName = simplyName;
        this.price = price;
        this.barcode = barcode;
    }

    public ProductForTest() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductForTest that = (ProductForTest) o;
        return Objects.equals(id, that.id) && Objects.equals(fullName, that.fullName) && Objects.equals(simplyName, that.simplyName) && Objects.equals(price, that.price) && Objects.equals(barcode, that.barcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, simplyName, price, barcode);
    }
}
