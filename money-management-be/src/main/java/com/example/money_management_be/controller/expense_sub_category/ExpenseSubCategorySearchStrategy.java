package com.example.money_management_be.controller.expense_sub_category;

import static java.util.Objects.nonNull;

import com.example.money_management_be.controller.SearchStrategy;
import com.example.money_management_be.entity.QExpenseSubCategoryEntity;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Component;

@Component
public class ExpenseSubCategorySearchStrategy implements SearchStrategy<QExpenseSubCategoryEntity> {

    @Override
    public Predicate getCustomPredicate(Predicate restPredicate, String criteria) {
        BooleanBuilder predicateBuilder = new BooleanBuilder();
        QExpenseSubCategoryEntity qEntity = QExpenseSubCategoryEntity.expenseSubCategoryEntity;
        predicateBuilder.and(restPredicate);

        if(nonNull(criteria)){
            predicateBuilder.and(qEntity.name.containsIgnoreCase(criteria));
        }

        return predicateBuilder;
    }

    @Override
    public void customize(QuerydslBindings bindings, QExpenseSubCategoryEntity root) {

    }
}
