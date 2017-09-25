package gm.vk.core.dto.subject.examination.grade;

import com.fasterxml.jackson.annotation.JsonProperty;
import gm.vk.core.dto.subject.examination.ExaminationDto;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.validation.constraints.NotNull;
import java.util.List;

public class GradeDto {

    public GradeDto() {
    }

    public GradeDto(final Integer id, final Integer grade, final List<ExaminationDto> examinations) {
        this.id = id;
        this.grade = grade;
        this.examinations = examinations;
    }

    @JsonProperty("GradeId")
    private Integer id;

    @NotNull
    private Integer grade;

    @NotNull
    private List<ExaminationDto> examinations;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public List<ExaminationDto> getExaminations() {
        return examinations;
    }

    public void setExaminations(List<ExaminationDto> examinations) {
        this.examinations = examinations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof GradeDto)) return false;

        GradeDto gradeDto = (GradeDto) o;

        return new EqualsBuilder()
                .append(id, gradeDto.id)
                .append(grade, gradeDto.grade)
                .append(examinations, gradeDto.examinations)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(grade)
                .append(examinations)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("grade", grade)
                .append("examinations", examinations)
                .toString();
    }
}