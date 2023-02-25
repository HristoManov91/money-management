package com.example.money_management_be.mapper.qualifiers;

import static java.util.Objects.nonNull;

import com.example.money_management_be.entity.UserEntity;
import com.example.money_management_be.entity.UserRoleEntity;
import com.example.money_management_be.entity.enums.UserRole;
import com.example.money_management_be.exception.exceptionClasses.ResourceNotFoundException;
import com.example.money_management_be.repository.UserRepository;
import com.example.money_management_be.repository.UserRoleRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UserQualifier {

    UserRepository repository;
    UserRoleRepository roleRepository;

    @Named("FromIdToUser")
    public UserEntity findUserById(Long id) {
        if (nonNull(id)) {
            Optional<UserEntity> userOptional = repository.findById(id);
            if (userOptional.isPresent()) {
                return userOptional.get();
            }
        }
        //TODO: return null is a good ?
        return null;
    }

    @Named("MapRolesFromString")
    public List<UserRoleEntity> mapRolesFromString(List<String> roles) {
        if (nonNull(roles)) {
            return roles.stream()
                    .map(r -> roleRepository.findByRole(UserRole.valueOf(r)).orElseThrow(() ->new ResourceNotFoundException(r)))
                    .toList();
        } else {
            return roleRepository.findByRole(UserRole.USER).stream().toList();
        }
    }

    @Named("MapRolesFromEntity")
    public List<String> mapRolesFromEntity(List<UserRoleEntity> roles){
        if (nonNull(roles) && roles.size() > 0){
            return roles.stream().map(r -> r.getRole().name()).toList();
        } else {
            return new ArrayList<>();
        }
    }
}
