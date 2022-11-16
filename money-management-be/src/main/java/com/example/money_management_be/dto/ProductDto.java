package com.example.money_management_be.dto;

import java.math.BigDecimal;
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
    ProductTypeDto productType;
    BigDecimal standardPrice;
    BigDecimal priceDiscount;
    BigDecimal percentDiscount;
    BigDecimal priceAfterDiscount;
    BigDecimal quantity;
    BigDecimal totalPrice;
}
