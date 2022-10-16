package com.example.moneymanagementbe.dto;

import com.example.moneymanagementbe.entity.enums.GenderEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(name = "User", description = "User description")
public class UserDto extends BaseDto {

    //TODO validations
    String username;
    String password;
    String fullName;
    LocalDate dateOfBirth;
    String email;
    GenderEnum genderEnum;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime createDate;
}
