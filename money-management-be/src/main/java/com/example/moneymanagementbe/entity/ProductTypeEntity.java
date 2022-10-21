package com.example.moneymanagementbe.entity;

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
@ToString
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "products_names")
public class ProductTypeEntity extends BaseEntity {

    @Column(name = "name", nullable = false, length = 50)
    String name;

    @Column(name = "brand", length = 50)
    String brand;

    @ManyToOne(
        targetEntity = CategoryEntity.class,
        optional = false,
        fetch = FetchType.EAGER,
        cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH})
    CategoryEntity category;

    @ManyToOne(
        targetEntity = UserEntity.class,
        fetch = FetchType.LAZY,
        optional = false,
        cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH})
    UserEntity user;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProductTypeEntity)) {
            return false;
        }
        ProductTypeEntity that = (ProductTypeEntity) o;
        return Objects.equals(getName(), that.getName()) && Objects.equals(getBrand(), that.getBrand()) && Objects.equals(
            getCategory(), that.getCategory()) && Objects.equals(getUser(), that.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getBrand(), getCategory(), getUser());
    }
}
