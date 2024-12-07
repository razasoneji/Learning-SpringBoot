package com.firstproject.razasoneji.demoproject.Services;


import com.firstproject.razasoneji.demoproject.DTO.EmployeeDTO;
import com.firstproject.razasoneji.demoproject.Entities.EmployeeEntity;
import com.firstproject.razasoneji.demoproject.Repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // to denote it is a service bean
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO, String password) {
       EmployeeEntity employeeEntity = modelMapper.map(employeeDTO, EmployeeEntity.class); // it return  b Type of the (a,b)
       employeeEntity.setPassword(password);
       employeeRepository.save(employeeEntity);
       return modelMapper.map(employeeEntity, EmployeeDTO.class);
    }


    public EmployeeDTO getEmployeeById(Long id) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id).orElse(null);
        return modelMapper.map(employeeEntity, EmployeeDTO.class);

    }

    public List<EmployeeDTO> getAllEmployees() {
        // using the streams to make it proper like a collection and
        // have the model mapping at individual object level.
        return employeeRepository.findAll()
                            .stream()
                            .map(x -> modelMapper.map(x, EmployeeDTO.class)).toList();

    }


}
