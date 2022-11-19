package com.example.money_management_be.mapper.qualifiers;

import static java.util.Objects.nonNull;

import com.example.money_management_be.entity.UserEntity;
import com.example.money_management_be.repository.UserRepository;
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

    @Named("FromIdToUser")
    public UserEntity findUserById(Long id){
        if (nonNull(id)) {
            Optional<UserEntity> userOptional = repository.findById(id);
            if (userOptional.isPresent()) {
                return userOptional.get();
            }
        }
        //TODO: return null is a good ?
        return null;
    }
}
