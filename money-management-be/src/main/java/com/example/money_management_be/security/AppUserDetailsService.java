package com.example.money_management_be.security;

import com.example.money_management_be.entity.UserEntity;
import com.example.money_management_be.entity.UserRoleEntity;
import com.example.money_management_be.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class AppUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public AppUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        return
            userRepository
                .findByEmail(email)
                .map(this::mapUE)
                .orElseThrow(() -> new UsernameNotFoundException("User with this " + email + " email not found!"));
    }

    private UserDetails mapUE(UserEntity userEntity) {

        return new AppUserDetails(
            userEntity.getId(),
            userEntity.getEmail(),
            userEntity.getPassword(),
            userEntity.
                getRoles().
                stream().
                map(this::mapGA).
                toList()
        );
    }

    private GrantedAuthority mapGA(UserRoleEntity userRole) {
        return new SimpleGrantedAuthority("ROLE_" +
            userRole.
                getRole().name());
    }
}
