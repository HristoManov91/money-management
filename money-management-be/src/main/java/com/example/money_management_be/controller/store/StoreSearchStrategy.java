package com.example.money_management_be.controller.store;

import static java.util.Objects.nonNull;

import com.example.money_management_be.controller.SearchStrategy;
import com.example.money_management_be.entity.QStoreEntity;
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
            predicateBuilder.andAnyOf(
                qEntity.name.containsIgnoreCase(criteria)
            );
        }

        return predicateBuilder;
    }

    @Override
    public void customize(QuerydslBindings bindings, QStoreEntity root) {
    }
}
