package com.example.money_management_be.controller;

import com.example.money_management_be.controller.expense.ExpenseSearchStrategy;
import com.example.money_management_be.controller.expense_category.ExpenseCategorySearchStrategy;
import com.example.money_management_be.controller.expense_sub_category.ExpenseSubCategorySearchStrategy;
import com.example.money_management_be.controller.product.ProductSearchStrategy;
import com.example.money_management_be.controller.product_category.ProductCategorySearchStrategy;
import com.example.money_management_be.controller.product_type.ProductTypeSearchStrategy;
import com.example.money_management_be.controller.store.StoreSearchStrategy;
import com.example.money_management_be.controller.user.UserSearchStrategy;
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
    EXPENSE {
        @Override
        public SearchStrategy<?> getSearchStrategy() {
            return expenseSearchStrategy;
        }
    },
    EXPENSE_CATEGORY {
        @Override
        public SearchStrategy<?> getSearchStrategy() {
            return expenseCategorySearchStrategy;
        }
    },
    EXPENSE_SUB_CATEGORY {
        @Override
        public SearchStrategy<?> getSearchStrategy() {
            return expenseSubCategorySearchStrategy;
        }
    },
    STORE {
        @Override
        public SearchStrategy<?> getSearchStrategy() {
            return storeSearchStrategy;
        }
    },
    PRODUCT {
        @Override
        public SearchStrategy<?> getSearchStrategy() {
            return productSearchStrategy;
        }
    },
    PRODUCT_TYPE {
        @Override
        public SearchStrategy<?> getSearchStrategy() {
            return productTypeSearchStrategy;
        }
    },
    PRODUCT_CATEGORY {
        @Override
        public SearchStrategy<?> getSearchStrategy() {
            return productCategorySearchStrategy;
        }
    };

    private static UserSearchStrategy userSearchStrategy;
    private static ExpenseSearchStrategy expenseSearchStrategy;
    private static ExpenseCategorySearchStrategy expenseCategorySearchStrategy;
    private static ExpenseSubCategorySearchStrategy expenseSubCategorySearchStrategy;
    private static StoreSearchStrategy storeSearchStrategy;
    private static ProductSearchStrategy productSearchStrategy;
    private static ProductTypeSearchStrategy productTypeSearchStrategy;
    private static ProductCategorySearchStrategy productCategorySearchStrategy;

    @Component
    @RequiredArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
    public static class SearchStrategyInjector {

        StoreSearchStrategy storeSearchStrategy;
        UserSearchStrategy userSearchStrategy;
        ExpenseSearchStrategy expenseSearchStrategy;
        ExpenseCategorySearchStrategy expenseCategorySearchStrategy;
        ExpenseSubCategorySearchStrategy expenseSubCategorySearchStrategy;
        ProductSearchStrategy productSearchStrategy;
        ProductTypeSearchStrategy productTypeSearchStrategy;
        ProductCategorySearchStrategy productCategorySearchStrategy;

        @PostConstruct
        public void postConstruct() {
            SearchEnum.userSearchStrategy = userSearchStrategy;
            SearchEnum.expenseSearchStrategy = expenseSearchStrategy;
            SearchEnum.expenseCategorySearchStrategy = expenseCategorySearchStrategy;
            SearchEnum.expenseSubCategorySearchStrategy = expenseSubCategorySearchStrategy;
            SearchEnum.storeSearchStrategy = storeSearchStrategy;
            SearchEnum.productSearchStrategy = productSearchStrategy;
            SearchEnum.productTypeSearchStrategy = productTypeSearchStrategy;
            SearchEnum.productCategorySearchStrategy = productCategorySearchStrategy;
        }
    }
}
