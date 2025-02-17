package com.HW6.demo.Controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/plan")
public class PlanController {

    @GetMapping("/free")
    public String free() {
        return "This is a controller for the FREE subscription, no others can use it.";
    }

    @GetMapping("/basic")
    public String basic() {
        return "This is a controller for the BASIC subscription, no others can use it.";
    }

    @GetMapping("/premium")
    public String premium() {
        return "This is a controller for the PREMIUM subscription, no others can use it.";
    }

}
