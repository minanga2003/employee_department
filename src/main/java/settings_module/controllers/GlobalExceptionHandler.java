package settings_module.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;
import settings_module.dto.responseDto.PayloadResponse;
import settings_module.util.CommonMessages;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<PayloadResponse<Object>> handleResponseStatusException(ResponseStatusException ex) {
        HttpStatus status = HttpStatus.valueOf(ex.getStatusCode().value());
        String message = StringUtils.hasText(ex.getReason()) ? ex.getReason() : ex.getMessage();
        return buildFailureResponse(status, message);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<PayloadResponse<Object>> handleGenericException(Exception ex) {
        return buildFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, CommonMessages.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<PayloadResponse<Object>> buildFailureResponse(HttpStatus status, String message) {
        return ResponseEntity.status(status)
                .body(PayloadResponse.builder()
                        .status_code(status.value())
                        .message(message)
                        .message_status(PayloadResponse.MessageStatus.FAILURE)
                        .data(null)
                        .build());
    }
}

