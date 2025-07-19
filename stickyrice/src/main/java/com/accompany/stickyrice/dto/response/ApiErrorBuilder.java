package com.accompany.stickyrice.dto.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

public class ApiErrorBuilder {
    public static ResponseEntity<ApiErrorResponse> build(HttpStatus status, String message) {
        ApiErrorResponse error = new ApiErrorResponse(
                status.value(),
                message,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(error, status);
    }

}
