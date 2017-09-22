package gm.vk.core.domain.data.contacts.address;

import gm.vk.core.domain.data.contacts.Contacts;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

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
        this.contacts = builder.contacts;
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

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "contacts", cascade = CascadeType.ALL)
    private Contacts contacts;

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

    public Contacts getContacts() {
        return contacts;
    }

    public void setContacts(Contacts contacts) {
        this.contacts = contacts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Address)) return false;

        Address address = (Address) o;

        return new EqualsBuilder()
                .append(id, address.id)
                .append(country, address.country)
                .append(city, address.city)
                .append(street, address.street)
                .append(home, address.home)
                .append(apartmentNumber, address.apartmentNumber)
                .append(contacts, address.contacts)
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
                .append(contacts)
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
                .append("contacts", contacts)
                .toString();
    }

    public static class Builder {

        private Integer id;
        private String country;
        private String city;
        private String street;
        private String home;
        private String apartmentNumber;
        private Contacts contacts;

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

        public Builder setContacts(final Contacts contacts) {
            this.contacts = contacts;
            return this;
        }

        public Address build() {
            return new Address(this);
        }

    }

}