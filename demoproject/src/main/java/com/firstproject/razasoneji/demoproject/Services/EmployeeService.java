package com.firstproject.razasoneji.demoproject.Services;


import com.firstproject.razasoneji.demoproject.DTO.EmployeeDTO;
import com.firstproject.razasoneji.demoproject.Entities.EmployeeEntity;
import com.firstproject.razasoneji.demoproject.Exceptions.ResourceNotFoundException;
import com.firstproject.razasoneji.demoproject.Repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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


    public Optional<EmployeeDTO> getEmployeeById(Long id) {
//        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(id);
//        Optional<EmployeeDTO> map = modelMapper.map(employeeEntity, EmployeeDTO.class);
//        return map;
          return employeeRepository.findById(id).map(employeeEntity -> modelMapper.map(employeeEntity, EmployeeDTO.class));
          // map returns the optional.
    }

    public List<EmployeeDTO> getAllEmployees() {
        // using the streams to make it proper like a collection and
        // have the model mapping at individual object level.
        return employeeRepository.findAll()
                            .stream()
                            .map(x -> modelMapper.map(x, EmployeeDTO.class)).toList();

    }


    public EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO) throws ResourceNotFoundException {
        if(!isExistsEmployeeById(id)){
            throw new ResourceNotFoundException("Employee not found");
        }
        EmployeeEntity employeeEntity = employeeRepository.findById(id).orElse(null);
        String password ;
        if(null == employeeEntity) {
            password = "password123";
        }
        else{
            password = employeeEntity.getPassword();
        }
//        boolean exists = isExistsEmployeeById(id);
//        String password;
//        EmployeeEntity employeeEntity ;
//        if(!exists){
//            password= "abcd123";
//        } and then fetch again by the employeeRepository

        employeeEntity = modelMapper.map(employeeDTO, EmployeeEntity.class);
        employeeEntity.setPassword(password);
        employeeRepository.save(employeeEntity);
        return modelMapper.map(employeeEntity, EmployeeDTO.class);
    }


    public Boolean deleteById(Long id) {
        boolean exists = isExistsEmployeeById(id);
        if(!exists){
            return false;
        }
        EmployeeEntity employeeEntity = employeeRepository.findById(id).get();
        employeeRepository.delete(employeeEntity);
        return true;
    }

    public EmployeeDTO patchEmployee(Long id, Map<String, Object> updates) {
        boolean exists = isExistsEmployeeById(id);
        if(!exists){
            return null;
        }
        EmployeeEntity employeeEntity = employeeRepository.findById(id).get();
        // we shall use the lambda expression here for the map iteration.
        // xyz.forEach((k,v)->{.....})
        // Reflections utils is a wrapper class. Field field.
        updates.forEach((key,value) -> {
            Field field = ReflectionUtils.findField(EmployeeEntity.class, key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, employeeEntity, value);

        });

        return modelMapper.map(employeeEntity,EmployeeDTO.class);

    }

    public boolean isExistsEmployeeById(Long id){
        return employeeRepository.existsById(id);
    }
}
