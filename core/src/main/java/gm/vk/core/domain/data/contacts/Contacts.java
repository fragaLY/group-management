package gm.vk.core.domain.data.contacts;

import gm.vk.core.domain.data.contacts.address.Address;

import javax.persistence.*;

@Entity
@Table(name = "contacts")
public class Contacts {

    public Contacts() {
    }

    private Contacts(final Builder builder) {
        this.id = builder.id;
        this.phone = builder.phone;
        this.skype = builder.skype;
        this.email = builder.email;
        this.address = builder.address;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "phone", unique = true, nullable = false)
    private String phone;

    @Column(name = "skype", unique = true)
    private String skype;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public static class Builder {

        private Integer id;
        private String phone;
        private String skype;
        private String email;
        private Address address;

        public Builder setId(final Integer id) {
            this.id = id;
            return this;
        }

        public Builder setPhone(final String phone) {
            this.phone = phone;
            return this;
        }

        public Builder setSkype(final String skype) {
            this.skype = skype;
            return this;
        }

        public Builder setEmail(final String email) {
            this.email = email;
            return this;
        }

        public Builder setAddress(final Address address) {
            this.address = address;
            return this;
        }

        public Contacts build() {
            return new Contacts(this);
        }

    }

}