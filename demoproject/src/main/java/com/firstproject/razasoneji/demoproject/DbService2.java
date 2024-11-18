package com.firstproject.razasoneji.demoproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DbService2 {
    private  final CONFIGDB db;


    @Autowired
    public DbService2(CONFIGDB db) {
        this.db = db;

    }

    public String getData(){
        return db.printName();
    }
}
