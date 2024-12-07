package com.firstproject.razasoneji.demoproject.Controllers;

import com.firstproject.razasoneji.demoproject.DTO.EmployeeDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/Employees")
public class EmployeeController {

    // we can use any method like get put post delete for any of the operations , like we can use the get mehtod for delete,
    // post method for update, patch method for getting some resource
    // but it is advisible to follow some syntactical meaning and have the methods defined as per the usage , like getting something with get
    // updating something with put , small update with patch  , delete with delete and so on
    // we have the required variables as path variables , through dynamic url and it is a direct name connection but we can change it and have it
    // as we have the name property in the @PathVariable, url would work only if the parameter is present and works.
    // path variable is used when the parameter is an essential part of the url path that identifies the resource.
    //we also have some other dynamic url via the requestparam ,
    // use this optional when we have filtering , sorting , personalize preferences and so on ..
    // an it is optional , without it also the url works.
    // we can have the request param 's required = false and the default value = "something" when nothing is provided.
    // request body is sent as json form and converted to the DTO / pojo object and vice versa via the
    // jackson present in
    // spring-boot-starter-web


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



    // request params are optional and are in the form of :
    // endpoints?name=raza&age=19&active=true
    // need NOT to be specified in the url with endpoints/{userId}


    // here the request parameter is required one
    @PutMapping(path = "/use-of-request-param") // url in form of /Employees/use-of-request-param?userId=123
    public String useOfRequestParam(@RequestParam String userId) {
        return "The userId is : " +  userId ;
    }


    // here the request parameter is not required one and also has a default value.
    @PutMapping(path = "/use-of-reqdefault")
    public String useOfReqParam(@RequestParam(defaultValue = "786") Integer userId) {
        return "The userId is : "+  userId;
    }

    // this is a not required one and has null value as default
    // we cannot user 'int' primitive , instead we will have to use the Integer as it can hold null value and has other benefits of being a wrapper class too
    @PutMapping(path = "/use-of-req-require")
    public String useOfReqParams(@RequestParam(required = false) Integer userId) {
        return " The userId is not required/optional is : "+ userId;
    }



    // request body can be anything, primitives or objects, it is just fetched from the client, obj or String abc , int rollno ,anything.
    @PostMapping(path = "/use-of-req-body")
    public EmployeeDTO useOfReqBody(@RequestBody EmployeeDTO employeeDTO) {
        employeeDTO.setId(120);
        return employeeDTO;
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
