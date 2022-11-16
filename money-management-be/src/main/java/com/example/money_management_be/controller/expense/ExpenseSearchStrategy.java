package com.example.money_management_be.controller.expense;

import static java.util.Objects.nonNull;

import com.example.money_management_be.controller.SearchStrategy;
import com.example.money_management_be.entity.QExpenseEntity;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Component;

@Component
public class ExpenseSearchStrategy implements SearchStrategy<QExpenseEntity> {


    @Override
    public Predicate getCustomPredicate(Predicate restPredicate, String criteria) {
        BooleanBuilder predicateBuilder = new BooleanBuilder();
        QExpenseEntity qEntity = QExpenseEntity.expenseEntity;
        predicateBuilder.and(restPredicate);

        if (nonNull(criteria)) {
            predicateBuilder.andAnyOf(
                qEntity.description.containsIgnoreCase(criteria)
                //TODO if i need more fields check for criteria and inner object
            );
        }
        return predicateBuilder;
    }

    @Override
    public void customize(QuerydslBindings bindings, QExpenseEntity root) {
        customize(bindings, root.date);
    }
}
