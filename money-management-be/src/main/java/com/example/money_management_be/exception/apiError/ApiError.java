package com.example.money_management_be.exception.apiError;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

@Data
@AllArgsConstructor
public class ApiError {

    private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;
    private String message;
    private String path;
    private String method;
    private String errId;
    private Map<String, String> customErrors;
    private List<ApiSubError> subErrors;

    private ApiError() {
        this.status = HttpStatus.BAD_REQUEST;
        this.timestamp = LocalDateTime.now();
    }

    public ApiError(String message) {
        this();
        this.message = message;
    }

    public ApiError(HttpStatus status, WebRequest request) {
        this.status = status;
        this.timestamp = LocalDateTime.now();
        path = ((ServletWebRequest) request).getRequest().getRequestURI();
        method = Objects.requireNonNull(((ServletWebRequest) request).getHttpMethod()).toString();
    }

    public ApiError(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
        this.timestamp = LocalDateTime.now();
    }

    public ApiError(HttpStatus status, String message, WebRequest request) {
        this.status = status;
        this.message = message;
        path = ((ServletWebRequest) request).getRequest().getRequestURI();
        method = Objects.requireNonNull(((ServletWebRequest) request).getHttpMethod()).toString();
    }

    public ApiError(List<ApiSubError> subErrors) {
        this();
        this.subErrors = subErrors;
    }
}
