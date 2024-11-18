package com.firstproject.razasoneji.demoproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DbService {

    // we will have the constructor DI here
    // Construtor Dependency Injection.

    // it can hold DevDb also and ProdDb also,
    // now on which we will write the @primary will be executed here
    private final DB db;

    //final field only through static block or constructor or direct initialized.

    //constructor injection instead of field injection.
    // constructor injection is recommmended .
    @Autowired
    DbService(DB db) {
        this.db = db;
    }

    String printmsg(){
       return db.getmockdata();
    }


}
