package gm.vk.core.domain.subject;

import gm.vk.core.domain.examination.Examination;
import gm.vk.core.domain.person.Person;
import gm.vk.core.domain.subject.grade.Grade;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "subject")
public class Subject {

    public Subject() {
    }

    private Subject(final Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.grade = builder.grade;
        this.examination = builder.examination;
        this.students = builder.students;
        this.lecturer = builder.lecturer;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "name")
    private String name;

    private Grade grade;

    private Examination examination;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "subject", cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE})
    private List<Person> students;

    @ManyToOne(fetch = FetchType.LAZY)
    private Person lecturer;

    public static class Builder{

        private Integer id;
        private String name;
        private Grade grade;
        private Examination examination;
        private List<Person> students;
        private Person lecturer;

        public Builder setId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setGrade(Grade grade) {
            this.grade = grade;
            return this;
        }

        public Builder setExamination(Examination examination) {
            this.examination = examination;
            return this;
        }

        public Builder setStudents(List<Person> students) {
            this.students = students;
            return this;
        }

        public Builder setLecturer(Person lecturer) {
            this.lecturer = lecturer;
            return this;
        }

        public Subject build(){
            return new Subject(this);
        }

    }

}