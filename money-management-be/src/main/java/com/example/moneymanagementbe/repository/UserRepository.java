package com.example.moneymanagementbe.repository;

import com.example.moneymanagementbe.entity.UserEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<UserEntity, Long> {

}
