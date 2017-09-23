package gm.vk.core.domain.person;

import gm.vk.core.domain.data.contacts.Contacts;
import gm.vk.core.domain.data.personal.PersonalData;
import gm.vk.core.domain.person.role.PersonRole;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;

@Entity
@Table(name = "person")
public class Person {

    public Person() {
    }

    private Person(final Builder builder) {
        this.id = builder.id;
        this.contacts = builder.contacts;
        this.personalData = builder.personalData;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    private PersonRole role;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "contacts_id")
    private Contacts contacts;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "personaldata_id")
    private PersonalData personalData;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PersonRole getRole() {
        return role;
    }

    public void setRole(PersonRole role) {
        this.role = role;
    }

    public Contacts getContacts() {
        return contacts;
    }

    public void setContacts(Contacts contacts) {
        this.contacts = contacts;
    }

    public PersonalData getPersonalData() {
        return personalData;
    }

    public void setPersonalData(PersonalData personalData) {
        this.personalData = personalData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Person)) return false;

        Person person = (Person) o;

        return new EqualsBuilder()
                .append(id, person.id)
                .append(role, person.role)
                .append(contacts, person.contacts)
                .append(personalData, person.personalData)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(role)
                .append(contacts)
                .append(personalData)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("role", role)
                .append("contacts", contacts)
                .append("personalData", personalData)
                .toString();
    }

    public static class Builder {

        private Integer id;
        private Contacts contacts;
        private PersonalData personalData;

        public Builder setId(final Integer id) {
            this.id = id;
            return this;
        }

        public Builder setContacts(final Contacts contacts) {
            this.contacts = contacts;
            return this;
        }

        public Builder setPersonalData(final PersonalData personalData) {
            this.personalData = personalData;
            return this;
        }

        public Person build() {
            return new Person(this);
        }

    }

}