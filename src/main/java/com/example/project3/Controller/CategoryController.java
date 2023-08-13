package com.example.project3.Controller;


import com.example.project3.ApiResponse.ApiResponse;
import com.example.project3.Model.CategoryModel;
import com.example.project3.Model.UserModel;
import com.example.project3.Service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/amazonclone/category")
@RequiredArgsConstructor
public class CategoryController {

    final private CategoryService categoryService;

    @GetMapping("/get")
    public ResponseEntity getCategoies(){
        ArrayList<CategoryModel> categories=categoryService.getCategories();
        return ResponseEntity.status(200).body(categories);
    }

    @PostMapping("/add")
    public ResponseEntity addCategory(@RequestBody @Valid CategoryModel category, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        categoryService.addCategory(category);
        return ResponseEntity.status(200).body(new ApiResponse("Category added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateCategory(@PathVariable String id,@RequestBody @Valid CategoryModel category, Errors errors)
    {
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isUpdated= categoryService.updateCategory(category,id);
        if (!isUpdated){
            return ResponseEntity.status(400).body(new ApiResponse("Id not found"));
        }
        return ResponseEntity.status(200).body(new ApiResponse("Category updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCategory(@PathVariable String id)
    {
        boolean isDeleted= categoryService.deleteCategory(id);
        if (!isDeleted){
            return ResponseEntity.status(400).body(new ApiResponse("Id not found"));
        }
        return ResponseEntity.status(200).body(new ApiResponse("Category deleted"));
    }

}
