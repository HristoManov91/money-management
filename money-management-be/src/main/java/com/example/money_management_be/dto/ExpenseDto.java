package com.example.money_management_be.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
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

    @NotBlank
    @Size(min = 2, max = 50)
    String category;
    @Size(min = 2, max = 50)
    String subCategory;
    Long userId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @NotNull
    @PastOrPresent
    LocalDate date;

    @Size(max = 50)
    List<ProductDto> products;
    @NotNull
    @Positive
    @DecimalMax("1000000")
    BigDecimal price;

    @PositiveOrZero
    @DecimalMax("1000000")
    BigDecimal discount;

    @Size(max = 100)
    String description;

    @Size(max = 50)
    String storeName;
}
