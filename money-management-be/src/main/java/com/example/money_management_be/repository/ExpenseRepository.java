package com.example.money_management_be.repository;

import com.example.money_management_be.entity.ExpenseEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends BaseRepository<ExpenseEntity, Long> {

}
