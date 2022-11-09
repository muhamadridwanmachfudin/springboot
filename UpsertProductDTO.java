package com.basilisk.dto.product;

import lombok.*;

import javax.validation.constraints.*;


@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class UpsertProductDTO {


    @Getter @Setter private Long id;//hidden

    @NotBlank(message = "Product Name Harus Diisi.")
    @Size(max=200,message = "Tidak boleh lebih dari 200 char")
    @Getter @Setter private String name;//text box

    @Getter @Setter private Long supplierId;//dropdown

    @NotNull(message = "Tolong pilih category")
    @Getter @Setter private Long categoryId;//dropdown

    @Size(max=1000,message = "Tidak boleh lebih dari 1000 char.")
    @Getter @Setter private String description;//textarea

    @Digits(integer = 12,fraction = 2,message = "Format Angka Salah atau melewati limit.")
    @NotNull(message = "Harga harus diisi.")
    @Getter @Setter private Double price;//text box

    @Max(value = 9999, message = "Tidak bisa lebih dari 9.999.")
    @Min(value = 0,message = "Tidak bisa angka negatif.")
    @NotNull(message = "Stock harus diisi.")
    @Getter @Setter private Integer stock;//number stepper

    @Max(value = 9999, message = "Tidak bisa lebih dari 9.999.")
    @Min(value = 0,message = "Tidak bisa angka negatif.")
    @NotNull(message = "Order harus diisi.")
    @Getter @Setter private Integer onOrder;//number stepper

    @NotNull(message = "discontinue harus diisi.")
    @Getter @Setter private Boolean discontinue;//check box

}
