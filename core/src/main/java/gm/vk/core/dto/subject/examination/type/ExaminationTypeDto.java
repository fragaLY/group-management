package gm.vk.core.dto.subject.examination.type;


import com.fasterxml.jackson.annotation.JsonProperty;
import gm.vk.core.domain.subject.examination.type.Type;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.validation.constraints.NotNull;

public class ExaminationTypeDto {

    public ExaminationTypeDto() {
    }

    public ExaminationTypeDto(final Integer id, final Type type) {
        this.id = id;
        this.type = type;
    }

    @JsonProperty("ExaminationTypeId")
    private Integer id;

    @NotNull
    private Type type;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof ExaminationTypeDto)) return false;

        ExaminationTypeDto that = (ExaminationTypeDto) o;

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