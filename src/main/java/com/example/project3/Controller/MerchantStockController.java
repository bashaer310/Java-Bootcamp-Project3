package com.example.project3.Controller;


import com.example.project3.ApiResponse.ApiResponse;
import com.example.project3.Model.MerchantStockModel;
import com.example.project3.Service.MerchantStockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/amazonclone/merchantstock")
@RequiredArgsConstructor
public class MerchantStockController {


    private final MerchantStockService merchantStockService;
    @GetMapping("/get")
    public ResponseEntity getMerchantStocks(){
        ArrayList<MerchantStockModel> merchantStocks = merchantStockService.getMerchantStocks();
        return ResponseEntity.status(200).body(merchantStocks);
    }

    @PostMapping("/add")
    public ResponseEntity addMerchantStock(@RequestBody @Valid MerchantStockModel merchantStock, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        merchantStockService.addMerchantStock(merchantStock);
        return ResponseEntity.status(200).body(new ApiResponse("Merchant Stock added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateMerchantStock(@PathVariable String id,@RequestBody @Valid MerchantStockModel merchantStock, Errors errors)
    {
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isUpdated= merchantStockService.updateMerchantStock(merchantStock,id);
        if (!isUpdated){
            return ResponseEntity.status(400).body(new ApiResponse("Id not found"));
        }
        return ResponseEntity.status(200).body(new ApiResponse("Merchant Stock updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMerchantStock(@PathVariable String id)
    {
        boolean isDeleted= merchantStockService.deleteMerchantStock(id);
        if (!isDeleted){
            return ResponseEntity.status(400).body(new ApiResponse("Id not found"));
        }
        return ResponseEntity.status(200).body(new ApiResponse("Merchant Stock deleted"));
    }


}
