package com.example.money_management_be.entity;

import static java.util.Objects.nonNull;

import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.PrePersist;
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
@Table(name = "products_types")
@NamedEntityGraph(
    name = "join-category",
    attributeNodes = {
        @NamedAttributeNode("productCategory")
    }
)
public class ProductTypeEntity extends BaseEntity {

    @Column(name = "name", nullable = false, length = 100)
    String name;

    @Column(name = "brand", length = 100)
    String brand;

    @ManyToOne(
        targetEntity = ProductCategoryEntity.class,
        fetch = FetchType.EAGER,
        optional = false,
        cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH}
    )
    ProductCategoryEntity productCategory;

    @ManyToOne(
        targetEntity = UserEntity.class,
        fetch = FetchType.LAZY,
        optional = false,
        cascade = {CascadeType.MERGE, CascadeType.DETACH}
    )
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
        return Objects.equals(getName(), that.getName())
            && Objects.equals(getBrand(), that.getBrand())
            && Objects.equals(getProductCategory().getId(), that.getProductCategory().getId())
            && Objects.equals(getUser().getId(), that.getUser().getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getBrand(), getProductCategory().getId(), getUser().getId());
    }

    @PrePersist
    private void prePersist() {
        if (nonNull(this.getProductCategory()) && this.getProductCategory().getUser() == null) {
            this.getProductCategory().setUser(this.getUser());
        }
    }
}
