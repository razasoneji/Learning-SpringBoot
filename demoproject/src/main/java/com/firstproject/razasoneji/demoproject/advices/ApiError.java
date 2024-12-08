package com.firstproject.razasoneji.demoproject.advices;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data // getter setter ToString equals hashcode requiredArgs(all) constructor with notnull on each field.
@Builder // generates builder pattern which is a design pattern
// starts with ApiError.builder().status().message().build();

public class ApiError { // instead of returning a string we return an object.

    private HttpStatus status;
    private String message;
    // we can also have a List<String> for the having the errors that occur along side the validation of dto.
}
