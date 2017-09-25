package gm.vk.core.domain.subject;

import gm.vk.core.domain.subject.examination.Examination;

import javax.persistence.*;

@Entity
@Table(name = "subject")
public class Subject {

    public Subject() {
    }

    public Subject(final Integer id, final String name, final Examination examination) {
        this.id = id;
        this.name = name;
        this.examination = examination;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "name")
    private String name;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "subject", cascade = CascadeType.ALL)
    private Examination examination;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Examination getExamination() {
        return examination;
    }

    public void setExamination(Examination examination) {
        this.examination = examination;
    }

}