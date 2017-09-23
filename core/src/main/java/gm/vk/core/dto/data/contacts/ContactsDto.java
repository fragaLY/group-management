package gm.vk.core.dto.data.contacts;

import com.fasterxml.jackson.annotation.JsonProperty;
import gm.vk.core.dto.data.contacts.address.AddressDto;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.validation.constraints.Pattern;

public class ContactsDto {

    private static final String PHONE_REGEXP = "[+]\\d{3}[(]\\d{2}[)]\\d{3}[\\-]\\d{4}";
    private static final String EMAIL_REGEXP = "\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,6}";
    private static final String SKYPE_REGEXP = "^[a-zA-Z][a-zA-Z0-9_.,-]{5,31}$";

    public ContactsDto() {
    }

    private ContactsDto(final Builder builder) {
        this.id = builder.id;
        this.phone = builder.phone;
        this.skype = builder.skype;
        this.email = builder.email;
        this.address = builder.address;
    }

    @JsonProperty("ContactsId")
    private Integer id;

    @Pattern(regexp = PHONE_REGEXP)
    private String phone;

    @Pattern(regexp = SKYPE_REGEXP)
    private String skype;

    @Pattern(regexp = EMAIL_REGEXP)
    private String email;

    private AddressDto address;

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

    public AddressDto getAddress() {
        return address;
    }

    public void setAddress(AddressDto address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof ContactsDto)) return false;

        ContactsDto that = (ContactsDto) o;

        return new EqualsBuilder()
                .append(id, that.id)
                .append(phone, that.phone)
                .append(skype, that.skype)
                .append(email, that.email)
                .append(address, that.address)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(phone)
                .append(skype)
                .append(email)
                .append(address)
                .toHashCode();
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("phone", phone)
                .append("skype", skype)
                .append("email", email)
                .append("address", address)
                .toString();
    }

    public static class Builder {

        private Integer id;
        private String phone;
        private String skype;
        private String email;
        private AddressDto address;

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

        public Builder setAddress(final AddressDto address) {
            this.address = address;
            return this;
        }

        public ContactsDto build() {
            return new ContactsDto(this);
        }

    }

}