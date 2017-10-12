package gm.vk.core.domain.subject.examination;

import gm.vk.core.domain.subject.examination.grade.Grade;
import gm.vk.core.domain.subject.examination.type.ExaminationType;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;

@Entity
@Table(name = "examination", schema = "groupmanagement")
public class Examination {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", unique = true, nullable = false)
  private Integer id;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "examinationtype_id", referencedColumnName = "id")
  private ExaminationType type;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "grade_id", referencedColumnName = "id")
  private Grade grade;

  public Examination() {}

  public Examination(final Integer id, final ExaminationType type, final Grade grade) {
    this.id = id;
    this.type = type;
    this.grade = grade;
  }

  public Grade getGrade() {
    return grade;
  }

  public void setGrade(Grade grade) {
    this.grade = grade;
  }

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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;

    if (!(o instanceof Examination)) return false;

    Examination that = (Examination) o;

    return new EqualsBuilder()
        .append(id, that.id)
        .append(type, that.type)
        .append(grade, that.grade)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(id).append(type).append(grade).toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
        .append("id", id)
        .append("type", type)
        .append("grade", grade)
        .toString();
  }
}
