package ru.clevertec.json.parser.util;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class ClientTestData {
    private Long id;
    private String firstName;
    private String secondName;
    private LocalDate dateOfRegistration;
    private String email;
    private List<String> telephones;
    private AddressTestData address;

    public ClientTestData(Long id, String firstName, String secondName, LocalDate dateOfRegistration, String email, List<String> telephones, AddressTestData address) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.dateOfRegistration = dateOfRegistration;
        this.email = email;
        this.telephones = telephones;
        this.address = address;
    }

    public ClientTestData() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientTestData that = (ClientTestData) o;
        return Objects.equals(id, that.id) && Objects.equals(firstName, that.firstName) && Objects.equals(secondName, that.secondName) && Objects.equals(dateOfRegistration, that.dateOfRegistration) && Objects.equals(email, that.email) && Objects.equals(telephones, that.telephones) && Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, secondName, dateOfRegistration, email, telephones, address);
    }
}
