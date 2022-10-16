package com.example.moneymanagementbe.entity;

import com.example.moneymanagementbe.entity.enums.GenderEnum;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

    @Column(name = "username", nullable = false, length = 50, unique = true)
    String username;

    @Column(name = "password", nullable = false, length = 50)
    String password;

    @Column(name = "full_name", nullable = false, length = 100)
    String fullName;

    @Column(name = "date_of_birth", nullable = false)
    LocalDate dateOfBirth;

    @Column(name = "email", nullable = false, length = 50, unique = true)
    String email;

    @Column(name = "gender", nullable = false, length = 10)
    GenderEnum genderEnum;

    @CreationTimestamp
    @Column(name = "create_date", updatable = false, nullable = false, columnDefinition = "timestamp(0)")
    LocalDateTime createDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserEntity)) {
            return false;
        }
        UserEntity that = (UserEntity) o;
        return getUsername().equals(that.getUsername()) && Objects.equals(getPassword(), that.getPassword()) && getFullName().equals(that.getFullName())
            && getDateOfBirth().equals(that.getDateOfBirth()) && getEmail().equals(that.getEmail()) && getGenderEnum() == that.getGenderEnum()
            && getCreateDate().equals(
            that.getCreateDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getPassword(), getFullName(), getDateOfBirth(), getEmail(), getGenderEnum(), getCreateDate());
    }
}
