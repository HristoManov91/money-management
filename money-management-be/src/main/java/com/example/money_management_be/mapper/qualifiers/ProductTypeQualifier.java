package com.example.money_management_be.mapper.qualifiers;

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
        if (dto.getName() != null && dto.getUserId() != null) {
            Optional<ProductTypeEntity> productTypeOptional = repository.findByNameAndBrandAndUserId(dto.getName(), dto.getBrand(), dto.getUserId());
            if (productTypeOptional.isPresent()) {
                return productTypeOptional.get();
            } else {
                ProductCategoryEntity productCategory = productCategoryQualifier.fromNameAndUserId(dto);
                UserEntity user = userQualifier.findUserById(dto.getUserId());
                return new ProductTypeEntity(dto.getName(), dto.getBrand(), productCategory, user);
            }
        }

        return null;
    }
}
