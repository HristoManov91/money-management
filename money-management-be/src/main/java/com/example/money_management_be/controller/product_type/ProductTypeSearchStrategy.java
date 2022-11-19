package com.example.money_management_be.controller.product_type;

import static java.util.Objects.nonNull;

import com.example.money_management_be.controller.SearchStrategy;
import com.example.money_management_be.entity.QProductTypeEntity;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Component;

@Component
public class ProductTypeSearchStrategy implements SearchStrategy<QProductTypeEntity> {

    @Override
    public Predicate getCustomPredicate(Predicate restPredicate, String criteria) {
        BooleanBuilder predicateBuilder = new BooleanBuilder();
        QProductTypeEntity qEntity = QProductTypeEntity.productTypeEntity;
        predicateBuilder.and(restPredicate);

        if (nonNull(criteria)){
            predicateBuilder.andAnyOf(
                qEntity.name.containsIgnoreCase(criteria),
                qEntity.brand.containsIgnoreCase(criteria),
                qEntity.productCategory.name.containsIgnoreCase(criteria)
            );
        }

        return predicateBuilder;
    }

    @Override
    public void customize(QuerydslBindings bindings, QProductTypeEntity root) {

    }
}
