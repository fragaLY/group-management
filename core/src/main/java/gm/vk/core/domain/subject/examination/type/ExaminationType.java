package gm.vk.core.domain.subject.examination.type;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import gm.vk.core.domain.subject.examination.Examination;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity @Table(name = "examinationType", schema = "groupmanagement") public class ExaminationType {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id", unique = true, nullable = false) private Integer id;

  @Enumerated(value = EnumType.STRING) @Column(name = "type", unique = true, nullable = false) private Type type;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "type") private Set<Examination> examinations;

  public ExaminationType() {
  }

  public ExaminationType(Integer id, Type type) {
    this.id = id;
    this.type = type;
  }

  public ExaminationType(final Integer id, final Type type, final Set<Examination> examinations) {
    this.id = id;
    this.type = type;
    this.examinations = examinations;
  }

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

  public Set<Examination> getExaminations() {
    return examinations;
  }

  public void setExaminations(Set<Examination> examinations) {
    this.examinations = examinations;
  }

  @Override public boolean equals(Object o) {
    if (this == o)
      return true;

    if (!(o instanceof ExaminationType))
      return false;

    ExaminationType that = (ExaminationType)o;

    return new EqualsBuilder().append(id, that.id).append(type, that.type).isEquals();
  }

  @Override public int hashCode() {
    return new HashCodeBuilder(17, 37).append(id).append(type).toHashCode();
  }

  @Override public String toString() {
    return new ToStringBuilder(this).append("id", id).append("type", type).toString();
  }
}
