package gm.vk.core.dto.subject.examination;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import gm.vk.core.dto.subject.examination.grade.GradeDto;
import gm.vk.core.dto.subject.examination.type.ExaminationTypeDto;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.hateoas.ResourceSupport;

@JsonInclude(JsonInclude.Include.NON_NULL) @JsonIgnoreProperties(ignoreUnknown = true) public class ExaminationDto
    extends ResourceSupport {

  @JsonProperty("ExaminationId") private Integer id;

  @NotNull(message = "Examination type could not be a null") private ExaminationTypeDto type;

  private GradeDto grade;

  public ExaminationDto() {
  }

  public ExaminationDto(final Integer id, final ExaminationTypeDto type, final GradeDto grade) {
    this.id = id;
    this.type = type;
    this.grade = grade;
  }

  public Integer getExaminationId() {
    return id;
  }

  public void setExaminationId(Integer id) {
    this.id = id;
  }

  public ExaminationTypeDto getType() {
    return type;
  }

  public void setType(ExaminationTypeDto type) {
    this.type = type;
  }

  public GradeDto getGrade() {
    return grade;
  }

  public void setGrade(GradeDto grade) {
    this.grade = grade;
  }

  @Override public boolean equals(Object o) {
    if (this == o)
      return true;

    if (!(o instanceof ExaminationDto))
      return false;

    ExaminationDto that = (ExaminationDto)o;

    return new EqualsBuilder().append(id, that.id).isEquals();
  }

  @Override public int hashCode() {
    return new HashCodeBuilder(17, 37).append(id).toHashCode();
  }

  @Override public String toString() {
    return new ToStringBuilder(this).append("id", id).toString();
  }
}
