package gm.vk.core.dto.subject;

import com.fasterxml.jackson.annotation.JsonProperty;
import gm.vk.core.dto.subject.examination.ExaminationDto;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.validation.constraints.NotNull;

public class SubjectDto {

    public SubjectDto() {
    }

    public SubjectDto(final Integer id, final String name, final ExaminationDto examination) {
        this.id = id;
        this.name = name;
        this.examination = examination;
    }

    @JsonProperty("SubjectId")
    private Integer id;

    @NotNull
    private String name;

    @NotNull
    private ExaminationDto examination;

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

    public ExaminationDto getExamination() {
        return examination;
    }

    public void setExamination(ExaminationDto examination) {
        this.examination = examination;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof SubjectDto)) return false;

        SubjectDto that = (SubjectDto) o;

        return new EqualsBuilder()
                .append(id, that.id)
                .append(name, that.name)
                .append(examination, that.examination)
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

}