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
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "expenses_sub_categories")
public class ExpenseSubCategoryEntity extends BaseEntity {

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @ManyToOne(
        targetEntity = ExpenseCategoryEntity.class,
        optional = false,
        fetch = FetchType.LAZY,
        cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH}
    )
    @Fetch(FetchMode.JOIN)
    ExpenseCategoryEntity expenseCategory;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ExpenseSubCategoryEntity)) {
            return false;
        }
        ExpenseSubCategoryEntity that = (ExpenseSubCategoryEntity) o;
        return Objects.equals(getId(), that.getId())
            && Objects.equals(getName(), that.getName())
            && Objects.equals(getExpenseCategory().getId(), that.getExpenseCategory().getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId() , getName(), getExpenseCategory().getId());
    }
}
