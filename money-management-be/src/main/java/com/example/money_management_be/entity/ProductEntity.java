package com.example.money_management_be.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedSubgraph;
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
@NamedEntityGraph(
    name = "join-productType",
    attributeNodes = {
        @NamedAttributeNode(
            value = "productType",
            subgraph = "productType.productCategory"
        )
    },
    subgraphs = {
        @NamedSubgraph(
            name = "productType.productCategory",
            attributeNodes = {
                @NamedAttributeNode("productCategory")
            }
        )
    }
)
public class ProductEntity extends BaseEntity {

    @ManyToOne(
        targetEntity = ProductTypeEntity.class,
        fetch = FetchType.EAGER,
        optional = false,
        cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH}
    )
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

    @Column(name = "date", nullable = false)
    LocalDate date;

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
