package gm.vk.core.domain.subject.examination.grade;

import gm.vk.core.domain.subject.examination.Examination;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "grade")
public class Grade {

    public Grade() {
    }

    public Grade(final Integer grade) {
        this.grade = grade;
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "grade")
    private List<Examination> examinations;

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