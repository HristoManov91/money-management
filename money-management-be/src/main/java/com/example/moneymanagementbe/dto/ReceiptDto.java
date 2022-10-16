package com.example.moneymanagementbe.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
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
public class ReceiptDto extends BaseDto {

    LocalDate date;
    UserDto user;
    List<ProductDto> products;
    BigDecimal totalPrice;
    BigDecimal totalDiscount;
    String description;
    StoreDto store;
}
