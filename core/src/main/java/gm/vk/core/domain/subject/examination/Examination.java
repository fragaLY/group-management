package gm.vk.core.domain.subject.examination;

import gm.vk.core.domain.subject.examination.grade.Grade;
import gm.vk.core.domain.subject.examination.type.ExaminationType;

import javax.persistence.*;

@Entity
@Table(name = "examination")
public class Examination {

    public Examination() {
    }

    public Examination(final Integer id, final ExaminationType type, final Grade grade) {
        this.id = id;
        this.type = type;
        this.grade = grade;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id")
    private ExaminationType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "grade_id")
    private Grade grade;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ExaminationType getType() {
        return type;
    }

    public void setType(ExaminationType type) {
        this.type = type;
    }

}