package com.example.money_management_be.controller.user;

import com.example.money_management_be.controller.SearchEnum;
import com.example.money_management_be.controller.SearchStrategy;
import com.example.money_management_be.dto.UserDto;
import com.example.money_management_be.dto.UserLoginDto;
import com.example.money_management_be.entity.UserEntity;
import com.example.money_management_be.security.AppUserDetails;
import com.example.money_management_be.security.AuthResponse;
import com.example.money_management_be.security.jwt.JwtTokenUtil;
import com.example.money_management_be.service.CrudService;
import com.example.money_management_be.service.UserService;
import com.querydsl.core.types.Predicate;
import java.util.Optional;
import javax.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    UserService service;
    AuthenticationManager authManager;
    JwtTokenUtil jwtUtil;

    @Override
    public CrudService<UserDto, UserEntity> crudService() {
        return service;
    }

    @Override
    public ResponseEntity<Page<UserDto>> findAll(
        Optional<String> criteria,
        @QuerydslPredicate(root = UserEntity.class, bindings = UserSearchStrategy.class) Predicate predicate,
        Pageable pageable) {

        SearchStrategy<?> searchStrategy = SearchEnum.USER.getSearchStrategy();
        return ResponseEntity.ok().body(service.findAll(searchStrategy.getCustomPredicate(predicate, criteria), pageable));
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody UserLoginDto userDto) {

        try {
            Authentication authenticate = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword()));

            AppUserDetails user = (AppUserDetails) authenticate.getPrincipal();
            String accessToken = jwtUtil.generateAccessToken(user);
            AuthResponse response = new AuthResponse(user.getEmail(), accessToken);

            return ResponseEntity.ok().body(response);

        } catch (BadCredentialsException ex) {

            return new ResponseEntity<>("Email or Password don't match!", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Boolean> registerUser(@Valid @RequestBody UserDto userDto) {
        boolean result = service.registerUser(userDto);

        return new ResponseEntity<>(result, result ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST);
    }

}
