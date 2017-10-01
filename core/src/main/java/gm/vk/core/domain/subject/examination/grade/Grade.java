package gm.vk.core.domain.subject.examination.grade;

import gm.vk.core.domain.subject.examination.Examination;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "grade")
public class Grade {

    public Grade() {
    }

    public Grade(final Integer id, final Integer grade, final Set<Examination> examinations) {
        this.id = id;
        this.grade = grade;
        this.examinations = examinations;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "grade")
    private Integer grade;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "grade")
    private Set<Examination> examinations;

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

    public Set<Examination> getExaminations() {
        return examinations;
    }

    public void setExaminations(Set<Examination> examinations) {
        this.examinations = examinations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Grade)) return false;

        Grade grade1 = (Grade) o;

        return new EqualsBuilder()
                .append(id, grade1.id)
                .append(grade, grade1.grade)
                .append(examinations, grade1.examinations)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(grade)
                .append(examinations)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("grade", grade)
                .append("examinations", examinations)
                .toString();
    }
}