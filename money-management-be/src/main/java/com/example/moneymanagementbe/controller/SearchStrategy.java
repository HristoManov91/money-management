package com.example.moneymanagementbe.controller;

import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.DateTimePath;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.Optional;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

public interface SearchStrategy<Q extends EntityPath<?>> extends QuerydslBinderCustomizer<Q> {

    Predicate getCustomPredicate(Predicate restPredicate, String criteria);

    default Predicate getCustomPredicate(Predicate restPredicate, Optional<String> criteria) {
        return getCustomPredicate(restPredicate, criteria.orElse(null));
    }

    default void customize(QuerydslBindings bindings, DateTimePath<Timestamp> dateTimePath) {
        bindings.bind(dateTimePath).all((path, value) -> {
            int dateCriteriaLimiter = 2;
            int size = value.size();
            if (isCorrectDateSize(dateCriteriaLimiter, size)) {
                Iterator<? extends Timestamp> iterator = value.iterator();
                return Optional.of(path.between(iterator.next(), iterator.next()));
            }
            return Optional.empty();
        });
    }

    private boolean isCorrectDateSize(int dateCriteriaLimiter, int size) {
        return size == dateCriteriaLimiter;
    }
}
