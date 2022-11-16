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
@NoArgsConstructor
@AllArgsConstructor
@ToString
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "products_categories")
public class ProductCategoryEntity extends BaseEntity {

    @Column(name = "name", nullable = false, length = 50)
    String name;

    @ManyToOne(
        targetEntity = UserEntity.class,
        optional = false,
        fetch = FetchType.LAZY,
        cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH}
    )
    UserEntity user;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProductCategoryEntity)) {
            return false;
        }
        ProductCategoryEntity that = (ProductCategoryEntity) o;
        return getName().equals(that.getName())
            && getUser().getId().equals(that.getUser().getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getUser().getId());
    }
}
