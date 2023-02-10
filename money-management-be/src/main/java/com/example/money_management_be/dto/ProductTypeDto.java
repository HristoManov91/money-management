package com.example.money_management_be.dto;

import javax.validation.constraints.NotBlank;
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
public class ProductTypeDto extends BaseDto {

    @NotBlank
    @Size(min = 2, max = 50)
    String name;
    @Size(max = 50)
    String brand;

    @NotBlank
    @Size(min = 2, max = 50)
    String productCategory;
    Long userId;
}
