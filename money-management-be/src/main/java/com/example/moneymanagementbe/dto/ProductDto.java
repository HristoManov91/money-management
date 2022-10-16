package com.example.moneymanagementbe.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDto extends BaseDto {

    //TODO validations
    ProductNameDto productName;
    BigDecimal standardPrice;
    BigDecimal priceDiscount;
    BigDecimal percentDiscount;
    BigDecimal price;
    BigDecimal quantity;
    BigDecimal totalPrice;
    CategoryDto category;
    LocalDate expirationDate;
}
