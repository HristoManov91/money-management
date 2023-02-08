package com.example.money_management_be.mapper.qualifiers;

import static java.util.Objects.nonNull;

import com.example.money_management_be.dto.ExpenseDto;
import com.example.money_management_be.entity.ExpenseCategoryEntity;
import com.example.money_management_be.repository.ExpenseCategoryRepository;
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
public class ExpenseCategoryQualifier {

    ExpenseCategoryRepository repository;
    UserRepository userRepository;

    @Named("FromCategoryNameAndUserId")
    public ExpenseCategoryEntity findEntity(ExpenseDto dto) {
        String name = dto.getCategory();
        Long userId = dto.getUserId();
        if (nonNull(name) && nonNull(userId)) {
            Optional<ExpenseCategoryEntity> optionalExpenseCategory = repository.findByNameAndUserId(name, userId);
            return optionalExpenseCategory.orElseGet(() -> new ExpenseCategoryEntity(name, userRepository.findById(userId).get()));
        }
        return ExpenseCategoryEntity.builder().name(name).build();
    }
}
