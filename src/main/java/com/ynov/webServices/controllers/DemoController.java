package com.ynov.webServices.controllers;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("demo")
public class DemoController {

    @GetMapping("hello-world")
    public String helloWorld (){
        return "Hello World !";
    }

    @GetMapping("hello-ynov")
    public String helloYnov(){
        return "Hello Ynov";
    }

    @GetMapping("add/{x}/{y}")
    public int add (@PathVariable int x,@PathVariable int y){
        return x+y;
    }

    @PostMapping("name")
    public String printName (@RequestBody Map<String, String> map){
        String firstname = map.get("firstname");
        String lastname = map.get("lastname");
        return "the full name is : "+firstname+" "+lastname;
    }

}
