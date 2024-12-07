package com.firstproject.razasoneji.demoproject.DTO;


// This is a POJO class, plain old java object,
// it has a parameterized constructor, default constructor, attributes, getters and setters.
// Generally a DTO is used to communicate between the controller and the Service object.

public class EmployeeDTO {
    private Integer id;
    private String name;
    private Integer rollNo;
    //Constructors
    public EmployeeDTO() {}
    public EmployeeDTO(Integer id, String name, Integer rollNo) {
        this.id = id;
        this.name = name;
        this.rollNo = rollNo;
    }
    //Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRollNo() {
        return rollNo;
    }

    public void setRollNo(Integer rollNo) {
        this.rollNo = rollNo;
    }
}
