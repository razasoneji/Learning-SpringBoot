package com.firstproject.razasoneji.demoproject.Repositories;

import com.firstproject.razasoneji.demoproject.Entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// this will be injected as a bean in the controller now,
// recommended practice is to have the service layer in between but
// we will just use this for our h2 database in memmory implementation
// as it is an interface , it needs an implementation,
// but the implementation is done by the jpa provider ,
// hibernate and hence we need to just define in the interface,
// its class/ implementation will be made itself.

@Repository
// needs to be written @repository as it will be injected as a bean , and for syntaxual example too.
// @ bean or @component needs to be mentioned in the class but here we will write it upon the repository

public interface EmployeeRepository extends JpaRepository<EmployeeEntity,Long> {
    // we can define our own custom methods here.
}
