package com.example.blogsystem.ApiResponse;

import lombok.Data;

@Data
public class ApiResponse {
    private String massage;

    public ApiResponse(String massage) {
        this.massage = massage;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }
}
