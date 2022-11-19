package com.example.money_management_be.mapper.qualifiers;

import static java.util.Objects.nonNull;

import com.example.money_management_be.dto.ProductTypeDto;
import com.example.money_management_be.entity.ProductCategoryEntity;
import com.example.money_management_be.repository.ProductCategoryRepository;
import com.example.money_management_be.repository.UserRepository;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ProductCategoryQualifier {

    ProductCategoryRepository repository;
    UserRepository userRepository;

    @Named("FromNameAndUserId")
    public ProductCategoryEntity fromNameAndUserId(ProductTypeDto dto) {
        String categoryName = dto.getProductCategory();
        Long userId = dto.getUserId();
        if (nonNull(categoryName) && nonNull(userId)) {
            Optional<ProductCategoryEntity> userOptional = repository.findByNameAndUserId(categoryName, userId);
            return userOptional.orElseGet(() -> new ProductCategoryEntity(categoryName, userRepository.findById(userId).get()));
        }
        return null;
    }
}
