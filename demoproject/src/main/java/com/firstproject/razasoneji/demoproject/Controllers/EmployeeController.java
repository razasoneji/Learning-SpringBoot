package com.firstproject.razasoneji.demoproject.Controllers;

import com.firstproject.razasoneji.demoproject.DTO.EmployeeDTO;
import com.firstproject.razasoneji.demoproject.Entities.EmployeeEntity;
import com.firstproject.razasoneji.demoproject.Repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/Employees")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    // Constructor injection
    @Autowired
    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @PostMapping(path = "/create")
    public EmployeeEntity createEmployee(@RequestBody EmployeeEntity employee) {
        employeeRepository.save(employee);
        return employee;
    }

    @GetMapping(path = "getById/{userId}")
    public EmployeeEntity getById(@PathVariable(name = "userId") Long id){
        return employeeRepository.findById(id).orElse(null);

    }


}
