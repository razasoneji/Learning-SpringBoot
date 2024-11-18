package com.firstproject.razasoneji.demoproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class StarterProjectApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(StarterProjectApplication.class, args);
		// Apple apple1 = new Apple();
		// apple1.eatApple() can be executed but this is not a bean.

		// To create a bean and to use the method inside it , we need to declare it outside of this
		// as a local variable cannot be a bean , instance variable can be a bean.
	}


	@Autowired
	Banana banana1;

	private final DbService dbService;

	//Constructor DI
	@Autowired
	StarterProjectApplication(DbService dbService){
		this.dbService = dbService;
	}

	// This is a direct field injection.
	// This is not some sort of constructor or setter injection.
	@Autowired
	Apple apple1 ;

	@Autowired
	Guava guava1;
	@Autowired
	Guava guava2;

	// we need to override the run method as the interface CommandLineRunner has it and if we implement it
	// we need to provide some implementation for its methods (basic rule of implementing a class)

	@Override
	public void run(String... args) throws Exception {
		apple1.eatApple();
		banana1.eatBanana();
		// As two instances created for it. while for singleton only one.
		System.out.println("Are both the guava's equal : " + (guava1.hashCode()==guava2.hashCode()));

		System.out.println(dbService.printmsg());



	}

	// post construct in order of creation ie first declared first di, second declared second di
	// pre destroy in opposite order of creation.



}
