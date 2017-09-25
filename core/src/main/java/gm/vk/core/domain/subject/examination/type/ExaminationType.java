package gm.vk.core.domain.subject.examination.type;

import gm.vk.core.domain.subject.examination.Examination;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "examinationType")
public class ExaminationType {

    public ExaminationType() {
    }

    public ExaminationType(final Type type) {
        this.type = type;
    }

    public ExaminationType(final Integer id, final Type type) {
        this.id = id;
        this.type = type;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "type")
    private Type type;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "examinationType")
    private List<Examination> examinations;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List<Examination> getExaminations() {
        return examinations;
    }

    public void setExaminations(List<Examination> examinations) {
        this.examinations = examinations;
    }

}