package com.firstproject.razasoneji.demoproject.Controllers;

import com.firstproject.razasoneji.demoproject.DTO.EmployeeDTO;
import com.firstproject.razasoneji.demoproject.Services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController // for having request body along with controller
@RequestMapping(path = "/Employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    // Constructor DI
    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // In create we generally have the boolean (created or not) or EmployeeDTO returned.
    @PostMapping(path = "/create/{password}")
    public EmployeeDTO createEmployee(@PathVariable String password, @RequestBody EmployeeDTO employeeDTO) {
        return employeeService.createEmployee(employeeDTO,password);
    }

    // In the Read we generally have the object or list<object> returned and show it.
    @GetMapping(path = "/getById/{userId}")
    public EmployeeDTO getEmployee(@PathVariable(name = "userId") Long id) {
        return employeeService.getEmployeeById(id);
    }

    @GetMapping(path ="/getAll")
    public List<EmployeeDTO> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    //in the Update, we send the whole object and hence receive the whole object back.
    // whole object is changed not only a specific field.

    @PutMapping(path = "/update/{userId}")
    public EmployeeDTO updateEmployee(@PathVariable(name = "userId") Long id, @RequestBody EmployeeDTO employeeDTO) {
        return employeeService.updateEmployee(id,employeeDTO);
    }

    // generally delete method has void or boolean
    @DeleteMapping(path = "/delete/{userId}")
    public boolean deleteEmployee(@PathVariable(name = "userId") Long id) {
       return employeeService.deleteById(id);
    }

    //generally when we dont update the whole object and just update the few things in the project,
    // we make sure we use the patch method , instead of put , because in put we will be changing whole of the object,
    // which is actually not required.
    @PatchMapping(path = "/patch/{userId}")
    public EmployeeDTO patchEmployee(@PathVariable(name = "userId") Long id, @RequestBody Map<String,Object> updates){
        return employeeService.patchEmployee(id,updates);
    }






}
