package mx.com.rc.accesosam3.exception;

import java.time.LocalDateTime;

public class ErrorMessage {

    private int statusCode;
    private LocalDateTime timestamp;
    private String menssage;
    private String description;

    public ErrorMessage(int statusCode, LocalDateTime timestamp, String menssage, String description) {
        this.statusCode = statusCode;
        this.timestamp = timestamp;
        this.menssage = menssage;
        this.description = description;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getMenssage() {
        return menssage;
    }

    public String getDescription() {
        return description;
    }
}
