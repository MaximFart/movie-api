package ru.sber.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;

import java.util.Objects;

@Schema(description = "Error Entity")
public class ErrorDto {

    @Schema(description = "Response status")
    private HttpStatus status;
    @Schema(description = "Response message")
    private String message;

//    public HttpStatus getStatus(HttpStatus status) {
//        return this.status;
//    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ErrorDto errorDto = (ErrorDto) o;
        return status.equals(errorDto.status) &&
                message.equals(errorDto.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, message);
    }

    @Override
    public String toString() {
        return "ErrorDto{" +
                "status=" + status +
                ", message='" + message + '\'' +
                '}';
    }
}
