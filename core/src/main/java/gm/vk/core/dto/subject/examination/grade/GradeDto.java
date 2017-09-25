package gm.vk.core.dto.subject.examination.grade;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class GradeDto {

    public GradeDto() {
    }

    public GradeDto(final Integer id, final Integer grade) {
        this.id = id;
        this.grade = grade;
    }

    @JsonProperty("GradeId")
    private Integer id;

    private Integer grade;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof GradeDto)) return false;

        GradeDto gradeDto = (GradeDto) o;

        return new EqualsBuilder()
                .append(id, gradeDto.id)
                .append(grade, gradeDto.grade)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(grade)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("grade", grade)
                .toString();
    }
}