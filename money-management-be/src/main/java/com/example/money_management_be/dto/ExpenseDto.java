package com.example.money_management_be.dto;

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
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExpenseDto extends BaseDto {

    String category;
    String subCategory;
    Long userId;
    LocalDate date;
    List<ProductDto> products;
    BigDecimal price;
    BigDecimal discount;
    String description;
    String storeName;
}
