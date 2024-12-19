package com.drake7v.accounts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import java.time.LocalDateTime;

@Data @AllArgsConstructor
public class ErrorResponseDto {

    private String apiPath;
    private HttpStatusCode errorCode;
    private String errorMessage;
    private LocalDateTime errorTime;

    public ErrorResponseDto(String message, HttpStatus status, String details, LocalDateTime timestamp) {
        this.apiPath = message;
        this.errorCode = status;
        this.errorMessage = details;
        this.errorTime = timestamp;
    }

}
