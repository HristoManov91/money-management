package com.example.moneymanagementbe.controller.user;

import static java.util.Objects.nonNull;

import com.example.moneymanagementbe.controller.SearchStrategy;
import com.example.moneymanagementbe.entity.QUserEntity;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Component;

@Component
public class UserSearchStrategy implements SearchStrategy<QUserEntity> {

    @Override
    public Predicate getCustomPredicate(Predicate restPredicate, String criteria) {
        BooleanBuilder predicateBuilder = new BooleanBuilder();
        QUserEntity qEntity = QUserEntity.userEntity;
        predicateBuilder.and(restPredicate);

        if (nonNull(criteria)) {
            predicateBuilder.andAnyOf(
                qEntity.email.containsIgnoreCase(criteria),
                qEntity.fullName.containsIgnoreCase(criteria),
                qEntity.username.containsIgnoreCase(criteria)
            );
        }

        return predicateBuilder;
    }

    @Override
    public void customize(QuerydslBindings bindings, QUserEntity root) {
        customize(bindings, root.dateOfBirth);
        customize(bindings, root.createDate);
    }
}
