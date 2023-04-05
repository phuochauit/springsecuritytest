package com.example.demo.dtos;

import lombok.Data;

@Data
public class BearerToken {
    private String accessToken;
    private String tokenType;

    public BearerToken(String accessToken, String tokenType) {
        this.accessToken = accessToken;
        this.tokenType = tokenType;
    }
}
