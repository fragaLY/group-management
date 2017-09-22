package gm.vk.core.domain.examination.ExaminationType;

import gm.vk.core.domain.examination.Examination;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "examinationType", cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE})
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof ExaminationType)) return false;

        ExaminationType that = (ExaminationType) o;

        return new EqualsBuilder()
                .append(id, that.id)
                .append(type, that.type)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(type)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("type", type)
                .toString();
    }

}