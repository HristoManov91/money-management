package com.example.money_management_be.repository;

import com.example.money_management_be.entity.UserEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<UserEntity, Long> {

}
