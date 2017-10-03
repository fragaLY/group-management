package gm.vk.core.dto.subject.examination.type;

import com.fasterxml.jackson.annotation.JsonProperty;
import gm.vk.core.domain.subject.examination.type.Type;
import gm.vk.core.dto.subject.examination.ExaminationDto;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.validation.constraints.NotNull;
import java.util.Set;

public class ExaminationTypeDto {

  @JsonProperty("ExaminationTypeId")
  private Integer id;
  @NotNull private Type type;
  private Set<ExaminationDto> examinations;

  public ExaminationTypeDto() {}

  public ExaminationTypeDto(Integer id, Type type) {
    this.id = id;
    this.type = type;
  }

  public ExaminationTypeDto(
      final Integer id, final Type type, final Set<ExaminationDto> examinations) {
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

  public Set<ExaminationDto> getExaminations() {
    return examinations;
  }

  public void setExaminations(Set<ExaminationDto> examinations) {
    this.examinations = examinations;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;

    if (!(o instanceof ExaminationTypeDto)) return false;

    ExaminationTypeDto that = (ExaminationTypeDto) o;

    return new EqualsBuilder()
        .append(id, that.id)
        .append(type, that.type)
        .append(examinations, that.examinations)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(id).append(type).append(examinations).toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
        .append("id", id)
        .append("type", type)
        .append("examinations", examinations)
        .toString();
  }
}
