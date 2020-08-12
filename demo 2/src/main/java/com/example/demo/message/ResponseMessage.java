package com.example.demo.message;

public class ResponseMessage {
    private String message;
    private String id;

    public ResponseMessage(String message , String id) {
        this.message = message;
        this.id = id;
    }

    public String getMessage() {
        return message;
    }
    public String getId() {
        return id;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}