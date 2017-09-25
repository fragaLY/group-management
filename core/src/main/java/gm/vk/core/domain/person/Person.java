package gm.vk.core.domain.person;

import gm.vk.core.domain.data.contacts.Contacts;
import gm.vk.core.domain.data.personal.PersonalData;
import gm.vk.core.domain.person.role.PersonRole;

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
        this.login = builder.login;
        this.password = builder.password;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static class Builder {

        private Integer id;
        private Contacts contacts;
        private PersonalData personalData;
        private String login;
        private String password;

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

        public Builder setLogin(String login) {
            this.login = login;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Person build() {
            return new Person(this);
        }

    }

}