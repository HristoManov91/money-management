package com.example.moneymanagementbe.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
        optional = false,
        cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH})
    ProductTypeEntity productType;

    @Column(name = "standard_price", nullable = false, scale = 2)
    BigDecimal standardPrice;

    @Column(name = "price_discount", scale = 2)
    BigDecimal priceDiscount;

    @Column(name = "percent_discount", scale = 2)
    BigDecimal percentDiscount;

    @Column(name = "price", nullable = false, scale = 2)
    BigDecimal price;

    @Column(name = "quantity", nullable = false, scale = 2)
    BigDecimal quantity;

    @Column(name = "total_price", nullable = false, scale = 2)
    BigDecimal totalPrice;

    @Column(name = "expiration_date")
    LocalDate expirationDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProductEntity)) {
            return false;
        }
        ProductEntity that = (ProductEntity) o;
        return Objects.equals(getProductType(), that.getProductType()) && Objects.equals(getStandardPrice(), that.getStandardPrice())
            && Objects.equals(getPriceDiscount(), that.getPriceDiscount()) && Objects.equals(getPercentDiscount(), that.getPercentDiscount())
            && Objects.equals(getPrice(), that.getPrice()) && Objects.equals(getQuantity(), that.getQuantity()) && Objects.equals(
            getTotalPrice(), that.getTotalPrice()) && Objects.equals(getExpirationDate(), that.getExpirationDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProductType(), getStandardPrice(), getPriceDiscount(), getPercentDiscount(), getPrice(), getQuantity(), getTotalPrice(),
            getExpirationDate());
    }
}
