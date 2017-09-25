package gm.vk.core.domain.data.contacts.address;

import javax.persistence.*;

@Entity
@Table(name = "address")
public class Address {

    public Address() {
    }

    private Address(final Builder builder) {
        this.id = builder.id;
        this.country = builder.country;
        this.city = builder.city;
        this.street = builder.street;
        this.home = builder.home;
        this.apartmentNumber = builder.apartmentNumber;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    @Column(name = "home")
    private String home;

    @Column(name = "apartmentNumber")
    private String apartmentNumber;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(String apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public static class Builder {

        private Integer id;
        private String country;
        private String city;
        private String street;
        private String home;
        private String apartmentNumber;

        public Builder setId(final Integer id) {
            this.id = id;
            return this;
        }

        public Builder setCountry(final String country) {
            this.country = country;
            return this;
        }

        public Builder setCity(final String city) {
            this.city = city;
            return this;
        }

        public Builder setStreet(final String street) {
            this.street = street;
            return this;
        }

        public Builder setHome(final String home) {
            this.home = home;
            return this;
        }

        public Builder setApartmentNumber(final String apartmentNumber) {
            this.apartmentNumber = apartmentNumber;
            return this;
        }

        public Address build() {
            return new Address(this);
        }

    }

}