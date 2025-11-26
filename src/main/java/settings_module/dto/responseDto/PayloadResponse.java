package settings_module.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PayloadResponse<T> {
    private int status_code;
    private String message;
    private MessageStatus message_status;
    private T data;

    public enum MessageStatus {
        SUCCESS,
        FAILURE
    }
}

