package com.firstproject.razasoneji.demoproject.Controllers;

import com.firstproject.razasoneji.demoproject.DTO.EmployeeDTO;
import com.firstproject.razasoneji.demoproject.Services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // for having request body along with controller
@RequestMapping(path = "/Employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    // Constructor DI
    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping(path = "/create/{password}")
    public EmployeeDTO createEmployee(@PathVariable String password, @RequestBody EmployeeDTO employeeDTO) {
        return employeeService.createEmployee(employeeDTO,password);
    }

    @GetMapping(path = "/getById/{userId}")
    public EmployeeDTO getEmployee(@PathVariable(name = "userId") Long id) {
        return employeeService.getEmployeeById(id);
    }

    @GetMapping(path ="/getAll")
    public List<EmployeeDTO> getAllEmployees() {
        return employeeService.getAllEmployees();
    }







}
