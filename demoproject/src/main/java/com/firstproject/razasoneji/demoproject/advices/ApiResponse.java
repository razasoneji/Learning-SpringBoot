package com.firstproject.razasoneji.demoproject.advices;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiResponse <T> {

    @JsonFormat(pattern = "hh:mm:ss  dd-MM-yyyy")
    private LocalDateTime timestamp;
    private T data;
    private ApiError error;

    public ApiResponse(){
        this.timestamp = LocalDateTime.now();
    }
    public ApiResponse(T data){
        this(); // calling the default constructor first
        this.data = data;
    }
    public ApiResponse(ApiError error){
        this(); // calling the default constructor first in order to have the timestamp mentioned.
        this.error = error;
    }
}
