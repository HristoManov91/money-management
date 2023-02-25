package com.example.money_management_be.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
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
public class ProductDto extends BaseDto {

    @NotNull
    @Valid
    ProductTypeDto productType;

    @NotNull
    @DecimalMax("1000000")
    @PositiveOrZero
    BigDecimal standardPrice;

    @DecimalMax("1000000")
    @PositiveOrZero
    BigDecimal priceDiscount;

    @NotNull
    @PositiveOrZero
    @DecimalMax("1000000")
    BigDecimal priceAfterDiscount;

    @NotNull
    @PositiveOrZero
    @DecimalMax("1000000")
    BigDecimal quantity;

    @NotNull
    @PositiveOrZero
    @DecimalMax("1000000")
    BigDecimal finalPrice;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    LocalDate date;
}
