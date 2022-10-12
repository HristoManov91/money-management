package com.example.moneymanagementbe.controller;

import com.example.moneymanagementbe.controller.user.UserSearchStrategy;
import javax.annotation.PostConstruct;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

public enum SearchEnum implements SearchType {

    USER {
        @Override
        public SearchStrategy<?> getSearchStrategy() {
            return userSearchStrategy;
        }
    };

    private static UserSearchStrategy userSearchStrategy;

    @Component
    @RequiredArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
    public static class SearchStrategyInjector {

        UserSearchStrategy userSearchStrategy;

        @PostConstruct
        public void postConstruct() {
            SearchEnum.userSearchStrategy = userSearchStrategy;
        }
    }
}
