package com.example.moneymanagementbe.entity;

import java.util.Objects;
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
public class ProductNameEntity extends BaseEntity {

    @Column(name = "name", nullable = false, length = 50)
    String name;

    @Column(name = "brand", length = 50)
    String brand;

    @ManyToOne(targetEntity = UserEntity.class, fetch = FetchType.LAZY, optional = false)//TODO cascade
    UserEntity user;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProductNameEntity)) {
            return false;
        }
        ProductNameEntity that = (ProductNameEntity) o;
        return getName().equals(that.getName()) && Objects.equals(getBrand(), that.getBrand()) && getUser().equals(that.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getBrand(), getUser());
    }
}
