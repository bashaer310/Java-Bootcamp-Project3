package com.example.project3.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MerchantModel {


    @NotEmpty(message = "Id must not be empty")
    private String id;

    @NotEmpty(message = "Name must not be empty")
    @Size(min = 3, message = "Name length must be more than 3")
    private String name;
}
