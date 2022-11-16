package com.example.money_management_be.controller.expense_category;

import static java.util.Objects.nonNull;

import com.example.money_management_be.controller.SearchStrategy;
import com.example.money_management_be.entity.QExpenseCategoryEntity;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Component;

@Component
public class ExpenseCategorySearchStrategy implements SearchStrategy<QExpenseCategoryEntity> {

    @Override
    public Predicate getCustomPredicate(Predicate restPredicate, String criteria) {
        BooleanBuilder predicateBuilder = new BooleanBuilder();
        QExpenseCategoryEntity qEntity = QExpenseCategoryEntity.expenseCategoryEntity;
        predicateBuilder.and(restPredicate);

        if (nonNull(criteria)) {
            predicateBuilder.and(qEntity.name.containsIgnoreCase(criteria));
        }
        return predicateBuilder;
    }

    @Override
    public void customize(QuerydslBindings bindings, QExpenseCategoryEntity root) {

    }
}
