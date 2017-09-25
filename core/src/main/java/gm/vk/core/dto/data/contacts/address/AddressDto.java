package gm.vk.core.dto.data.contacts.address;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.validation.constraints.Size;

public class AddressDto {

    public AddressDto(){
    }

    private AddressDto(final Builder builder) {
        this.id = builder.id;
        this.country = builder.country;
        this.city = builder.city;
        this.street = builder.street;
        this.home = builder.home;
        this.apartmentNumber = builder.apartmentNumber;
    }

    @JsonProperty("AddressId")
    private Integer id;

    @Size(min = 2, max = 50)
    private String country;

    @Size(min = 2, max = 50)
    private String city;

    @Size(min = 2, max = 50)
    private String street;

    @Size(min = 1, max = 10)
    private String home;

    @Size(min = 1, max = 10)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof AddressDto)) return false;

        AddressDto that = (AddressDto) o;

        return new EqualsBuilder()
                .append(id, that.id)
                .append(country, that.country)
                .append(city, that.city)
                .append(street, that.street)
                .append(home, that.home)
                .append(apartmentNumber, that.apartmentNumber)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(country)
                .append(city)
                .append(street)
                .append(home)
                .append(apartmentNumber)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("country", country)
                .append("city", city)
                .append("street", street)
                .append("home", home)
                .append("apartmentNumber", apartmentNumber)
                .toString();
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

        public AddressDto build() {
            return new AddressDto(this);
        }

    }
}