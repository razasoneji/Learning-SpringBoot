package com.firstproject.razasoneji.demoproject.DTO;


// This is a POJO class, plain old java object,
// it has a parameterized constructor, default constructor, attributes, getters and setters.
// Generally a DTO is used to communicate between the controller and the Service object.
// in the EmployeeDTO too we will use the lombok expressions.

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    private Integer id;
    private String name;
    private Integer rollNo;

}
