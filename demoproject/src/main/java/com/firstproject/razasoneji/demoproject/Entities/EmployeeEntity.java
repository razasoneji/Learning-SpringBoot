package com.firstproject.razasoneji.demoproject.Entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;


// entity is from jakarta and hence we need the spring data jpa dependency

@Entity
@Table(name = "Employees")     // We want table name as employee.
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
// we need the lombok dependency in order to have the all getter setter and constructors, so that
// our PRIVATE  data members can be used by the jpa and put to the database.
//This class has more members than the DTO, this class contains sensitive data that should not be present in the
//presentation layer(controller)
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String password;
    private String name;
    private Integer rollNo;

}


