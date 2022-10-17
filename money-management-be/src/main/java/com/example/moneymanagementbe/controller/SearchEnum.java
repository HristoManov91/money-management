package com.example.moneymanagementbe.controller;

import com.example.moneymanagementbe.controller.receipt.ReceiptSearchStrategy;
import com.example.moneymanagementbe.controller.store.StoreSearchStrategy;
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
    },
    RECEIPT {
        @Override
        public SearchStrategy<?> getSearchStrategy() {
            return receiptSearchStrategy;
        }
    },
    STORE {
        @Override
        public SearchStrategy<?> getSearchStrategy() {
            return storeSearchStrategy;
        }
    };

    private static UserSearchStrategy userSearchStrategy;
    private static ReceiptSearchStrategy receiptSearchStrategy;
    private static StoreSearchStrategy storeSearchStrategy;

    @Component
    @RequiredArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
    public static class SearchStrategyInjector {

        StoreSearchStrategy storeSearchStrategy;
        UserSearchStrategy userSearchStrategy;
        ReceiptSearchStrategy receiptSearchStrategy;

        @PostConstruct
        public void postConstruct() {
            SearchEnum.userSearchStrategy = userSearchStrategy;
            SearchEnum.receiptSearchStrategy = receiptSearchStrategy;
            SearchEnum.storeSearchStrategy = storeSearchStrategy;
        }
    }
}
