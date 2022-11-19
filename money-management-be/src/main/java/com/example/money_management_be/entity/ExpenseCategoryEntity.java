package com.example.money_management_be.entity;

import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"user"})
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "expenses_categories")
public class ExpenseCategoryEntity extends BaseEntity {

    @Column(name = "name", length = 50, nullable = false)
    String name;

    @ManyToOne(
        targetEntity = UserEntity.class,
        optional = false,
        fetch = FetchType.LAZY,
        cascade = {CascadeType.MERGE, CascadeType.DETACH}
    )
    UserEntity user;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ExpenseCategoryEntity)) {
            return false;
        }
        ExpenseCategoryEntity that = (ExpenseCategoryEntity) o;
        return Objects.equals(getId(), that.getId())
            && Objects.equals(getName(), that.getName())
            && Objects.equals(getUser().getId(), that.getUser().getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId() , getName(), getUser().getId());
    }
}
