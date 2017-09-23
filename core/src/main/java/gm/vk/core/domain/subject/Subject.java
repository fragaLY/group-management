package gm.vk.core.domain.subject;

import gm.vk.core.domain.subject.examination.Examination;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;

@Entity
@Table(name = "subject")
public class Subject {

    public Subject() {
    }

    private Subject(final Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.examination = builder.examination;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Subject)) return false;

        Subject subject = (Subject) o;

        return new EqualsBuilder()
                .append(id, subject.id)
                .append(name, subject.name)
                .append(examination, subject.examination)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(name)
                .append(examination)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("name", name)
                .append("examination", examination)
                .toString();
    }

    public static class Builder {

        private Integer id;
        private String name;
        private Examination examination;

        public Builder setId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setExamination(Examination examination) {
            this.examination = examination;
            return this;
        }

        public Subject build() {
            return new Subject(this);
        }

    }

}