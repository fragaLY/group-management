package gm.vk.core.dto.subject.examination;

import com.fasterxml.jackson.annotation.JsonProperty;
import gm.vk.core.dto.subject.examination.grade.GradeDto;
import gm.vk.core.dto.subject.examination.type.ExaminationTypeDto;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.validation.constraints.NotNull;

public class ExaminationDto {

    public ExaminationDto() {
    }

    public ExaminationDto(final Integer id, final ExaminationTypeDto type, final GradeDto grade) {
        this.id = id;
        this.type = type;
        this.grade = grade;
    }

    @JsonProperty("ExaminationId")
    private Integer id;

    @NotNull
    private ExaminationTypeDto type;

    @NotNull
    private GradeDto grade;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof ExaminationDto)) return false;

        ExaminationDto that = (ExaminationDto) o;

        return new EqualsBuilder()
                .append(id, that.id)
                .append(type, that.type)
                .append(grade, that.grade)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(type)
                .append(grade)
                .toHashCode();
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