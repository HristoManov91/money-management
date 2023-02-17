package com.example.money_management_be.repository;

import com.example.money_management_be.entity.UserRoleEntity;
import com.example.money_management_be.entity.enums.UserRole;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {

    Optional<UserRoleEntity> findByRole(UserRole role);
}
