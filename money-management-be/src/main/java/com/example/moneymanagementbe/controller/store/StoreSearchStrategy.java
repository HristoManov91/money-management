package com.example.moneymanagementbe.controller.store;

import static java.util.Objects.nonNull;

import com.example.moneymanagementbe.controller.SearchStrategy;
import com.example.moneymanagementbe.entity.QStoreEntity;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Component;

@Component
public class StoreSearchStrategy implements SearchStrategy<QStoreEntity> {

    @Override
    public Predicate getCustomPredicate(Predicate restPredicate, String criteria) {
        BooleanBuilder predicateBuilder = new BooleanBuilder();
        QStoreEntity qEntity = QStoreEntity.storeEntity;
        predicateBuilder.and(restPredicate);

        if (nonNull(criteria)) {
            return predicateBuilder.andAnyOf(
                qEntity.name.containsIgnoreCase(criteria)
            );
        }

        return predicateBuilder;
    }

    @Override
    public void customize(QuerydslBindings bindings, QStoreEntity root) {
    }
}
