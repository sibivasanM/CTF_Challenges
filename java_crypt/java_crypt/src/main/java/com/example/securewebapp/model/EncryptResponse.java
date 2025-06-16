package com.example.securewebapp.model;

public class EncryptResponse {
    private String encrypted;

    public EncryptResponse(String encrypted) {
        this.encrypted = encrypted;
    }

    public String getEncrypted() {
        return encrypted;
    }

    public void setEncrypted(String encrypted) {
        this.encrypted = encrypted;
    }
}

