package com.example.money_management_be.mapper.qualifiers;

import static java.util.Objects.nonNull;

import com.example.money_management_be.dto.ExpenseDto;
import com.example.money_management_be.entity.ExpenseCategoryEntity;
import com.example.money_management_be.entity.ExpenseSubCategoryEntity;
import com.example.money_management_be.repository.ExpenseCategoryRepository;
import com.example.money_management_be.repository.ExpenseSubCategoryRepository;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ExpenseSubCategoryQualifier {

    ExpenseSubCategoryRepository repository;
    ExpenseCategoryRepository categoryRepository;

    @Named("FromSubCategoryNameAndCategoryId")
    public ExpenseSubCategoryEntity findEntity(ExpenseDto dto) {

        Optional<ExpenseCategoryEntity> categoryEntityOptional = categoryRepository.findByNameAndUserId(dto.getCategory(), dto.getUserId());
        String subCategory = dto.getSubCategory();
        if (categoryEntityOptional.isPresent() && nonNull(subCategory)) {

            Optional<ExpenseSubCategoryEntity> subCategoryOptional =
                repository.findByNameAndExpenseCategoryId(subCategory, categoryEntityOptional.get().getId());

            return subCategoryOptional.orElseGet(() -> new ExpenseSubCategoryEntity(subCategory, categoryEntityOptional.get()));

        } else {
            return ExpenseSubCategoryEntity.builder().name(subCategory).build();
        }
    }
}
