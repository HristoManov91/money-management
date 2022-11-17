package com.example.money_management_be.entity;

import java.math.BigDecimal;
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
@Table(name = "products")
public class ProductEntity extends BaseEntity {

    @ManyToOne(
        targetEntity = ProductTypeEntity.class,
        fetch = FetchType.EAGER,
        optional = false,
        cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH}
    )
    @Fetch(FetchMode.JOIN)
    ProductTypeEntity productType;

    @Column(name = "standard_price", nullable = false, scale = 2)
    BigDecimal standardPrice;

    @Column(name = "price_discount", scale = 2)
    BigDecimal priceDiscount;

    @Column(name = "percent_discount", scale = 2)
    BigDecimal percentDiscount;

    @Column(name = "price_after_discount", nullable = false, scale = 2)
    BigDecimal priceAfterDiscount;

    @Column(name = "quantity", nullable = false, scale = 2)
    BigDecimal quantity;

    @Column(name = "total_price", nullable = false, scale = 2)
    BigDecimal totalPrice;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProductEntity)) {
            return false;
        }
        ProductEntity that = (ProductEntity) o;
        return Objects.equals(getProductType().getId(), that.getProductType().getId())
            && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProductType().getId(), getId());
    }


}
