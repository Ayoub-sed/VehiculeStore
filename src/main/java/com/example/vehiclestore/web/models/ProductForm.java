package com.example.vehiclestore.web.models;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductForm {

    @NotBlank
    String code;

    @NotEmpty
    @Size(min = 4, max = 30)
    private String name;

    @NotNull
    Double price;

    @Min(1)
    @NotNull
    Integer quantity;

    String image;

}