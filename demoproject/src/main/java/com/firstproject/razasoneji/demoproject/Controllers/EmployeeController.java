package com.firstproject.razasoneji.demoproject.Controllers;

import com.firstproject.razasoneji.demoproject.DTO.EmployeeDTO;
import com.firstproject.razasoneji.demoproject.Services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    public ResponseEntity<EmployeeDTO> createEmployee(@PathVariable String password, @RequestBody EmployeeDTO employeeDTO) {
        EmployeeDTO employeeDTO1 = employeeService.createEmployee(employeeDTO,password);
        return new ResponseEntity<>(employeeDTO1, HttpStatus.CREATED);
    }

    // In the Read we generally have the object or list<object> returned and show it.
    @GetMapping(path = "/getById/{userId}")
    public ResponseEntity<EmployeeDTO> getEmployee(@PathVariable(name = "userId") Long id) {
        Optional<EmployeeDTO> employeeDTO = employeeService.getEmployeeById(id);
        return employeeDTO.map(employeeDTO1 -> ResponseEntity.ok(employeeDTO1)).orElse(ResponseEntity.notFound().build());

    }

    @GetMapping(path ="/getAll")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
         return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    //in the Update, we send the whole object and hence receive the whole object back.
    // whole object is changed not only a specific field.

    @PutMapping(path = "/update/{userId}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable(name = "userId") Long id, @RequestBody EmployeeDTO employeeDTO) {
        return    ResponseEntity.ok(employeeService.updateEmployee(id,employeeDTO));
    }

    // generally delete method has void or boolean
    @DeleteMapping(path = "/delete/{userId}")
    public ResponseEntity<Boolean> deleteEmployee(@PathVariable(name = "userId") Long id) {
       return  ResponseEntity.ok(employeeService.deleteById(id));
    }

    //generally when we don't update the whole object and just update the few things in the project,
    // we make sure we use the patch method , instead of put , because in put we will be changing whole of the object,
    // which is actually not required.
    @PatchMapping(path = "/patch/{userId}")
    public ResponseEntity<EmployeeDTO> patchEmployee(@PathVariable(name = "userId") Long id, @RequestBody Map<String,Object> updates){
        return ResponseEntity.ok(employeeService.patchEmployee(id,updates));
    }






}
