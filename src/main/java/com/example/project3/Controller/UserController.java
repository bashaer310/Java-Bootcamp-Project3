package com.example.project3.Controller;


import com.example.project3.ApiResponse.ApiResponse;
import com.example.project3.Model.UserModel;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/amazonclone/user")
public class UserController {

    ArrayList<UserModel> users=new ArrayList<UserModel>();

    @GetMapping("/get")
    public ArrayList<UserModel> getUsers(){
        return users;
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody @Valid UserModel user, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        users.add(user);
        return ResponseEntity.status(200).body(new ApiResponse("User added"));
    }
}
