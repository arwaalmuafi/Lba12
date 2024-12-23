package com.example.blogsystem.ApiResponse;

public class ApiException extends RuntimeException {

    public ApiException(String massage){
        super(massage);
    }
}
