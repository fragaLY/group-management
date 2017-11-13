package gm.vk.controllers.exceptions.error;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.http.HttpStatus;

import java.util.Set;

public class ErrorDetails {

    @JsonProperty("status")
    private HttpStatus status;
    @JsonProperty("message")
    private String message;
    private Set<String> errors;

    public ErrorDetails() {
    }

    private ErrorDetails(final Builder builder) {
        this.status = builder.status;
        this.message = builder.outputMessage;
        this.errors = builder.errors;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Set<String> getErrors() {
        return errors;
    }

    public void setErrors(Set<String> errors) {
        this.errors = errors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof ErrorDetails)) return false;

        ErrorDetails error = (ErrorDetails) o;

        return new EqualsBuilder()
                .append(status, error.status)
                .append(message, error.message)
                .append(errors, error.errors)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(status).append(message).append(errors).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("status", status)
                .append("message", message)
                .append("errors", errors)
                .toString();
    }

    public static final class Builder {

        private HttpStatus status;
        private String outputMessage;
        private Set<String> errors;

        public Builder setStatus(HttpStatus status) {
            this.status = status;
            return this;
        }

        public Builder setOutputMessage(String outputMessage) {
            this.outputMessage = outputMessage;
            return this;
        }

        public Builder setErrors(Set<String> errors) {
            this.errors = errors;
            return this;
        }

        public ErrorDetails build() {
            return new ErrorDetails(this);
        }
    }
}
