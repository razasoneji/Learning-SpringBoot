package com.firstproject.razasoneji.demoproject.Controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/Employees")
public class EmployeeController {

    @GetMapping(path="/")
    public String index() {
        return "All request match page";
    }

    @GetMapping(path = "/{userId}")
    public String sameName(@PathVariable int userId) {
        return "Same PathVariable name in function/method and url " + userId;
    }

    @PostMapping(path = "/welcome/{userId}")
    public String differentName(@PathVariable(name = "userId") Integer id) {
        return "Different Name of PathVariable in url and method/function parameter " + id;
    }

    @GetMapping(path = "/ulta-sidha-without-rename/{userId}/{username}")
    public String withoutRename(@PathVariable String username, @PathVariable int userId) {
        return "Without Rename jumbled also works "+username +" "+userId;
    }


    @GetMapping(path = "/ulta-sidha/{userId}/{userName}")
    public String ultaSidhaParams(@PathVariable(name = "userName") String name, @PathVariable(name = "userId") int id) {
        return "Userid is taken first and then name , but we can fetch in any order by name property of pathvariable " +
                "ID is : " + id + " and name is : " + name;
    }

    @GetMapping
    public String errorHandling() {
        return "This is the error or say no request match page get , only getmapping written with no path , same pages for put post delete";
    }

    @PutMapping
    public String putMapping() {
        return " no request match page put";
    }

    @PostMapping
    public String postMapping() {
        return " no request match page post";
    }

    @DeleteMapping
    public String deleteMapping() {
        return " no request match page delete";
    }

    @PatchMapping
    public String patchMapping() {
        return " no request match page patch";
    }






}
