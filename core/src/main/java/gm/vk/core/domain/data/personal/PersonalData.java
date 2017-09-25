package gm.vk.core.domain.data.personal;

import javax.persistence.*;

@Entity
@Table(name = "personalData")
public class PersonalData {

    public PersonalData() {
    }

    private PersonalData(final Builder builder) {
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.secondName = builder.secondName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "secondName")
    private String secondName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public static class Builder {

        private Integer id;
        private String firstName;
        private String secondName;

        public Builder setId(final Integer id) {
            this.id = id;
            return this;
        }

        public Builder setFirstName(final String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setSecondName(final String secondName) {
            this.secondName = secondName;
            return this;
        }

        public PersonalData build() {
            return new PersonalData(this);
        }

    }

}