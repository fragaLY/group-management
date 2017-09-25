package gm.vk.core.domain.subject.examination.grade;

import javax.persistence.*;

@Entity
@Table(name = "grade")
public class Grade {

    public Grade() {
    }

    public Grade(final Integer id, final Integer grade) {
        this.id = id;
        this.grade = grade;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "grade")
    private Integer grade;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

}