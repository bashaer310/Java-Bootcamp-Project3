package com.example.project3.Controller;


import com.example.project3.ApiResponse.ApiResponse;
import com.example.project3.Model.UserModel;
import com.example.project3.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/amazonclone/user")
@RequiredArgsConstructor
public class UserController {

     private final UserService userService;
    @GetMapping("/get")
    public ResponseEntity getUsers(){
        ArrayList<UserModel> users = userService.getUsers();
        return ResponseEntity.status(200).body(users);
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody @Valid UserModel user, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        userService.addUser(user);
        return ResponseEntity.status(200).body(new ApiResponse("User added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable String id,@RequestBody @Valid UserModel user, Errors errors)
    {
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isUpdated= userService.updateUser(user,id);
        if (!isUpdated){
            return ResponseEntity.status(400).body(new ApiResponse("Id not found"));
        }
        return ResponseEntity.status(200).body(new ApiResponse("User updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable String id)
    {
        boolean isDeleted= userService.deleteUser(id);
        if (!isDeleted){
            return ResponseEntity.status(400).body(new ApiResponse("Id not found"));
        }
        return ResponseEntity.status(200).body(new ApiResponse("User deleted"));
    }

    @GetMapping("/find/{userId}")
    public ResponseEntity findUser(@PathVariable String userId)
    {
        ArrayList<UserModel> users = userService.getUsers();
        int index= userService.findUser(users,userId);
        if (index==-1){
            return ResponseEntity.status(400).body(new ApiResponse("User not found "));
        }
        return ResponseEntity.status(200).body(users.get(index));
    }
    @PutMapping("/buy/{userId}/{productId}/{merchantId}")
    public ResponseEntity buyProduct(@PathVariable String userId,@PathVariable String productId,@PathVariable String merchantId)
    {
        boolean Bought= userService.buyProduct(userId,productId,merchantId);
        if (!Bought){
            return ResponseEntity.status(400).body(new ApiResponse("Unsuccessful purchase "));
        }
        return ResponseEntity.status(200).body(new ApiResponse("Successful purchase "));
    }



}
