package com.example.money_management_be.mapper.qualifiers;

import static java.util.Objects.nonNull;

import com.example.money_management_be.dto.ProductTypeDto;
import com.example.money_management_be.entity.ProductCategoryEntity;
import com.example.money_management_be.entity.ProductTypeEntity;
import com.example.money_management_be.entity.UserEntity;
import com.example.money_management_be.repository.ProductTypeRepository;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ProductTypeQualifier {

    ProductTypeRepository repository;
    ProductCategoryQualifier productCategoryQualifier;
    UserQualifier userQualifier;

    @Named("FindProductType")
    public ProductTypeEntity findProductType(ProductTypeDto dto) {
        String name = dto.getName();
        Long userId = dto.getUserId();

        if (nonNull(name) && nonNull(userId)) {
            Optional<ProductTypeEntity> productTypeOptional = repository.findByNameAndBrandAndUserId(name, dto.getBrand(), userId);
            if (productTypeOptional.isPresent()) {
                return productTypeOptional.get();
            } else {
                ProductCategoryEntity productCategory = productCategoryQualifier.fromNameAndUserId(dto);
                UserEntity user = userQualifier.findUserById(userId);
                return new ProductTypeEntity(name, dto.getBrand(), productCategory, user);
            }
        }

        return null;
    }
}
