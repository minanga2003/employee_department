package settings_module.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import settings_module.dto.responseDto.PayloadResponse;

public final class ResponseBuilder {

    private ResponseBuilder() {
       
    }

    public static <T> ResponseEntity<PayloadResponse<T>> build(HttpStatus status,
                                                               String message,
                                                               PayloadResponse.MessageStatus messageStatus,
                                                               T data) {
        return ResponseEntity.status(status)
                .body(PayloadResponse.<T>builder()
                        .status_code(status.value())
                        .message(message)
                        .message_status(messageStatus)
                        .data(data)
                        .build());
    }
}

