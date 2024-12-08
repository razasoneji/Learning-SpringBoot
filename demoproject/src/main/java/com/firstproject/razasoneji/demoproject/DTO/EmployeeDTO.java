package com.firstproject.razasoneji.demoproject.DTO;


// This is a POJO class, plain old java object,
// it has a parameterized constructor, default constructor, attributes, getters and setters.
// Generally a DTO is used to communicate between the controller and the Service object.
// in the EmployeeDTO too we will use the lombok expressions.
//We try to have the validation in the controller itself and send the validated object to the service layer.
//

import com.firstproject.razasoneji.demoproject.annotations.GenderValidation;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

    private Integer id;
    // @NotNull(message = "Name cannot be null.") in case of name not passed. doesnt account for "" empty string.
    //@NotEmpty(message = "Name should be passed and shouldn't be empty.")
    @NotBlank(message = "String cannot be empty as well as cannot blank and cannot be null")
    @Size(min = 3,max = 20,message = "The Name must be within the range of 3 to 20.")
    private String name;

    @Max(value = 180 , message = "Cannot have rollNo more than 180.")
    @Positive(message = "Roll No. Cannot be negative.")
    private Integer rollNo;

    @NotBlank(message = "Email Cannot be blank here.")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@ddu\\.ac\\.in$",message = "It should be a valid ddu id , xyz@ddu.ac.in")
    private String email;

    @Past(message = "Enter a valid past date.")
    private Date dateOfBirth;

    //@Pattern(regexp = "^(male|female)$")

    @GenderValidation
    private String gender;


}
