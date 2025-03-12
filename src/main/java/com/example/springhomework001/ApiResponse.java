package com.example.springhomework001;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ApiResponse <T>{
    private boolean success;
    private String messenger;
    private HttpStatus status;
    private T payload;
    private LocalDateTime timestamp;

    public ApiResponse(boolean success, String messenger, LocalDateTime timestamp, HttpStatus status, T payload) {
        this.success = success;
        this.messenger = messenger;
        this.timestamp = timestamp;
        this.status = status;
        this.payload = payload;
    }
    public ApiResponse(boolean success, String messenger,HttpStatus status , T payload, LocalDateTime timestamp) {
        this.success = success;
        this.messenger = messenger;
        this.timestamp = timestamp;
        this.status = status;
        this.payload = payload;
    }
    public ApiResponse(boolean success, String messenger,LocalDateTime timestamp, HttpStatus status){
        this.success = success;
        this.messenger = messenger;
        this.status = status;
        this.timestamp = timestamp;
    }
    public ApiResponse(boolean success, String messenger, HttpStatus status,LocalDateTime timestamp){
        this.success = success;
        this.messenger = messenger;
        this.status = status;
        this.timestamp = timestamp;
    }
    public ApiResponse(boolean success, String messenger){
        this.success = success;
        this.messenger = messenger;
    }
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessenger() {
        return messenger;
    }

    public void setMessenger(String messenger) {
        this.messenger = messenger;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
