package com.example.project3.Model;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MerchantStockModel {

    @NotEmpty(message = "Id must not be empty")
    private String id;

    @NotEmpty(message = "Product id must not be empty")
    private String productId;


    @NotEmpty(message = "Merchant id must not be empty")
    private String merchantId;

    @NotNull(message = "Stock must not be empty ")
    @Min(value = 10, message = "Stock must be contains more than 10 products")
    private Integer stock;
}
