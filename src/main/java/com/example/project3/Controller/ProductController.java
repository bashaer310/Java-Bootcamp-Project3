package com.example.project3.Controller;


import com.example.project3.ApiResponse.ApiResponse;
import com.example.project3.Model.ProductModel;
import com.example.project3.Model.UserModel;
import com.example.project3.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/amazonclone/product")
@RequiredArgsConstructor
public class ProductController {


    private final ProductService productService;
    @GetMapping("/get")
    public ResponseEntity getProducts(){
        ArrayList<ProductModel> products = productService.getProducts();
        return ResponseEntity.status(200).body(products);
    }

    @PostMapping("/add")
    public ResponseEntity addProduct(@RequestBody @Valid ProductModel product, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        productService.addProduct(product);
        return ResponseEntity.status(200).body(new ApiResponse("Product added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateProduct(@PathVariable String id,@RequestBody @Valid ProductModel product, Errors errors)
    {
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isUpdated= productService.updateProduct(product,id);
        if (!isUpdated){
            return ResponseEntity.status(400).body(new ApiResponse("Id not found"));
        }
        return ResponseEntity.status(200).body(new ApiResponse("Product updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteProduct(@PathVariable String id)
    {
        boolean isDeleted= productService.deleteProduct(id);
        if (!isDeleted){
            return ResponseEntity.status(400).body(new ApiResponse("Id not found"));
        }
        return ResponseEntity.status(200).body(new ApiResponse("Product deleted"));
    }

    @GetMapping("/find/{productId}")
    public ResponseEntity findProduct(@PathVariable String productId)
    {
        ArrayList<ProductModel> products = productService.getProducts();
        int index= productService.findProduct(productId);
        if (index==-1){
            return ResponseEntity.status(400).body(new ApiResponse("Product not found "));
        }
        return ResponseEntity.status(200).body(products.get(index));
    }

    @GetMapping("/sort")
    public ResponseEntity sortProduct()
    {
        ArrayList<ProductModel> products = productService.getProducts();
        if (products.size()<=0){
            return ResponseEntity.status(400).body(new ApiResponse("Products not found "));
        }
        return ResponseEntity.status(200).body(productService.sortProducts());
    }

}
