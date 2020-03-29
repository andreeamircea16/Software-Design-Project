package com.store.app.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiError {
    HttpStatus status;
    String successMessage;
    String errorMessage;
    Object body;

    public ApiError(HttpStatus status, Object body) {
        this.status = status;
        this.body = body;
    }

    public void setSuccessMessage(String message) {
        this.successMessage = message;
    }

    public void setErrorMessage(String message) {
        this.errorMessage = message;
    }
}
