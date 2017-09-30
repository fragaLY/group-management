package gm.vk.core.domain.person.role;

import gm.vk.core.domain.person.Person;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "personRole")
public class PersonRole {

    public PersonRole() {
    }

    public PersonRole(Integer id, Role role, List<Person> persons) {
        this.id = id;
        this.role = role;
        this.persons = persons;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    private Role role;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "person")//todo vk: fix
    @Column(name = "persons")
    private List<Person> persons;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

}