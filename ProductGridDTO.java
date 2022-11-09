package com.basilisk.dto.product;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class ProductGridDTO {

    @Getter @Setter private Long id;
    @Getter @Setter private String productName;
    @Getter @Setter private String supplierCompany;
    @Getter @Setter private String categoryName;
    @Getter @Setter private Double price;
}
