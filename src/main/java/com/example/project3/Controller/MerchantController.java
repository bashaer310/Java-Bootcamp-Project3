package com.example.project3.Controller;


import com.example.project3.ApiResponse.ApiResponse;
import com.example.project3.Model.MerchantModel;
import com.example.project3.Model.UserModel;
import com.example.project3.Service.MerchantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/amazonclone/merchant")
@RequiredArgsConstructor
public class MerchantController {


    final private MerchantService merchantService;

    @GetMapping("/get")
    public ResponseEntity getMerchants(){
        ArrayList<MerchantModel> merchants=merchantService.getMerchants();
        return ResponseEntity.status(200).body(merchants);
    }

    @PostMapping("/add")
    public ResponseEntity addMerchant(@RequestBody @Valid MerchantModel merchant, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        merchantService.addMerchant(merchant);
        return ResponseEntity.status(200).body(new ApiResponse("Merchant added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateMerchant(@PathVariable String id,@RequestBody @Valid MerchantModel merchant, Errors errors)
    {
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isUpdated= merchantService.updateMerchant(merchant,id);
        if (!isUpdated){
            return ResponseEntity.status(400).body(new ApiResponse("Id not found"));
        }
        return ResponseEntity.status(200).body(new ApiResponse("Merchant updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMerchant(@PathVariable String id)
    {
        boolean isDeleted= merchantService.deleteMerchant(id);
        if (!isDeleted){
            return ResponseEntity.status(400).body(new ApiResponse("Id not found"));
        }
        return ResponseEntity.status(200).body(new ApiResponse("Merchant deleted"));
    }

    @PutMapping("/updatestock/{productid}/{merchantid}/{amount}")
    public ResponseEntity updateStock(@PathVariable String productid,@PathVariable String merchantid, @PathVariable Integer amount)
    {
        boolean isUpdated = merchantService.updateStock(productid,merchantid,amount);
        if (isUpdated)
            return ResponseEntity.status(200).body(new ApiResponse("Added more a stock"));

        return ResponseEntity.status(400).body(new ApiResponse("Ids not found in a merchant stocks list"));
    }

    @GetMapping("/find/{merchantId}")
    public ResponseEntity findUser(@PathVariable String merchantId)
    {
        ArrayList<MerchantModel> merchants = merchantService.getMerchants();
        int index= merchantService.findMerchant(merchantId);
        if (index==-1){
            return ResponseEntity.status(400).body(new ApiResponse("Merchant not found "));
        }
        return ResponseEntity.status(200).body(merchants.get(index));
    }
}
