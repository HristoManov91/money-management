package com.example.money_management_be.entity;

import com.example.money_management_be.entity.enums.GenderEnum;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

    @Column(name = "password", nullable = false)
    String password;

    @Column(name = "full_name", nullable = false, length = 200)
    String fullName;

    @Column(name = "date_of_birth", nullable = false)
    LocalDate dateOfBirth;

    @Column(name = "email", nullable = false, length = 50, unique = true)
    String email;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "gender", nullable = false, length = 10)
    GenderEnum gender;

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
        return Objects.equals(getId(), that.getId())
            && Objects.equals(getPassword(), that.getPassword())
            && Objects.equals(getFullName(), that.getFullName())
            && Objects.equals(getDateOfBirth(), that.getDateOfBirth())
            && Objects.equals(getEmail(), that.getEmail())
            && getGender() == that.getGender()
            && Objects.equals(getCreateDate(), that.getCreateDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPassword(), getFullName(), getDateOfBirth(), getEmail(), getGender(), getCreateDate());
    }
}
