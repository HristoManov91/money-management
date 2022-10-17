package com.example.moneymanagementbe.controller.receipt;

import static java.util.Objects.nonNull;

import com.example.moneymanagementbe.controller.SearchStrategy;
import com.example.moneymanagementbe.entity.QReceiptEntity;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Component;

@Component
public class ReceiptSearchStrategy implements SearchStrategy<QReceiptEntity> {


    @Override
    public Predicate getCustomPredicate(Predicate restPredicate, String criteria) {
        BooleanBuilder predicateBuilder = new BooleanBuilder();
        QReceiptEntity qEntity = QReceiptEntity.receiptEntity;
        predicateBuilder.and(restPredicate);

        if (nonNull(criteria)) {
            return predicateBuilder.andAnyOf(
                qEntity.description.containsIgnoreCase(criteria)
                //TODO if i need more fields check for criteria and inner object
            );
        }
        return predicateBuilder;
    }

    @Override
    public void customize(QuerydslBindings bindings, QReceiptEntity root) {
        customize(bindings, root.date);
    }
}
