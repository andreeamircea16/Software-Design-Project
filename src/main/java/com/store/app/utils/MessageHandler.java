package com.store.app.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class MessageHandler {
    public static ResponseEntity responseSuccessMessageBuilder(HttpStatus status, String message, Object body) {
        ApiError responseMessage = new ApiError(status, body);
        responseMessage.setSuccessMessage(message);
        return ResponseEntity.status(status).body(responseMessage);
    }

    public static ResponseEntity responseErrorMessageBuilder(HttpStatus status, String message, Object body) {
        ApiError responseMessage = new ApiError(status, body);
        responseMessage.setErrorMessage(message);
        return ResponseEntity.status(status).body(responseMessage);
    }
}
