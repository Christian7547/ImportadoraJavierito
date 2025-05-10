package com.javierito.javierito_importer.domain.models.error;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ErrorResponse {

    private LocalDateTime timestamp;

    private String message;

    private String uri;

    public ErrorResponse(String message, String uri) {
        this.setMessage(message);
        this.setUri(uri.replace("uri=", ""));
        this.setTimestamp(LocalDateTime.now());
    }
}
