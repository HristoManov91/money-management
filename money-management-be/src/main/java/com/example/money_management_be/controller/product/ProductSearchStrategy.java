package com.example.money_management_be.controller.product;

import static java.util.Objects.nonNull;

import com.example.money_management_be.controller.SearchStrategy;
import com.example.money_management_be.entity.QProductEntity;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Component;

@Component
public class ProductSearchStrategy implements SearchStrategy<QProductEntity> {

    @Override
    public Predicate getCustomPredicate(Predicate restPredicate, String criteria) {
        BooleanBuilder predicateBuilder = new BooleanBuilder();
        QProductEntity qEntity = QProductEntity.productEntity;
        predicateBuilder.and(restPredicate);

        if (nonNull(criteria)) {
            predicateBuilder.andAnyOf(
                qEntity.productType.name.containsIgnoreCase(criteria),
                qEntity.productType.brand.containsIgnoreCase(criteria),
                qEntity.productType.category.name.containsIgnoreCase(criteria)
            );
        }
        return predicateBuilder;
    }

    @Override
    public void customize(QuerydslBindings bindings, QProductEntity root) {

    }
}
