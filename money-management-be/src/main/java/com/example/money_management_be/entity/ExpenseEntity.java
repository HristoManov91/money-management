package com.example.money_management_be.entity;

import static java.util.Objects.nonNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToMany;
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
@ToString(exclude = {"user", "store"})
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "expenses")
@NamedEntityGraphs({
    @NamedEntityGraph(
        name = "join-all",
        attributeNodes = {
            @NamedAttributeNode("category"),
            @NamedAttributeNode("subCategory"),
            @NamedAttributeNode("user"),
            @NamedAttributeNode(value = "store", subgraph = "store.address"),
            @NamedAttributeNode(value = "products", subgraph = "products.productType")
        },
        subgraphs = {
            @NamedSubgraph(
                name = "store.address",
                attributeNodes = @NamedAttributeNode("address")
            ),
            @NamedSubgraph(
                name = "products.productType",
                attributeNodes = @NamedAttributeNode(value = "productType", subgraph = "productType.productCategory")
            ),
            @NamedSubgraph(
                name = "productType.productCategory",
                attributeNodes = @NamedAttributeNode(value = "productCategory")
            )
        }
    ),
    @NamedEntityGraph(
        name = "join-products",
        attributeNodes = @NamedAttributeNode(value = "products", subgraph = "subgraph.product"),
        subgraphs = {
            @NamedSubgraph(
                name = "subgraph.product",
                attributeNodes = @NamedAttributeNode(value = "productType", subgraph = "subgraph.productType")
            ),
            @NamedSubgraph(
                name = "subgraph.productType",
                attributeNodes = @NamedAttributeNode(value = "productCategory")
            )
        }
    )}
)
public class ExpenseEntity extends BaseEntity {

    @ManyToOne(
        targetEntity = ExpenseCategoryEntity.class,
        fetch = FetchType.EAGER,
        optional = false,
        cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH}
    )
    ExpenseCategoryEntity category;

    @ManyToOne(
        targetEntity = ExpenseSubCategoryEntity.class,
        fetch = FetchType.EAGER,
        optional = false,
        cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH}
    )
    ExpenseSubCategoryEntity subCategory;

    @Column(name = "date", nullable = false)
    LocalDate date;

    @ManyToOne(
        targetEntity = UserEntity.class,
        fetch = FetchType.EAGER,
        optional = false,
        cascade = {CascadeType.MERGE, CascadeType.DETACH}
    )
    UserEntity user;

    @OneToMany(
        targetEntity = ProductEntity.class,
        fetch = FetchType.LAZY,
        cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH}
    )
    List<ProductEntity> products;

    @Column(name = "price", scale = 2, nullable = false)
    BigDecimal price;

    @Column(name = "discount", scale = 2)
    BigDecimal discount;

    @Column(name = "description", length = 500)
    String description;

    @ManyToOne(
        targetEntity = StoreEntity.class,
        fetch = FetchType.EAGER,
        optional = false,
        cascade = {CascadeType.MERGE, CascadeType.DETACH}
    )
    StoreEntity store;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ExpenseEntity)) {
            return false;
        }
        ExpenseEntity that = (ExpenseEntity) o;
        return Objects.equals(getId(), that.getId())
            && Objects.equals(getCategory().getId(), that.getCategory().getId())
            && Objects.equals(getSubCategory().getId(), that.getSubCategory().getId())
            && Objects.equals(getDate(), that.getDate())
            && Objects.equals(getUser().getId(), that.getUser().getId())
            && Objects.equals(getPrice(), that.getPrice())
            && Objects.equals(getDiscount(), that.getDiscount())
            && Objects.equals(getDescription(), that.getDescription())
            && Objects.equals(getStore().getId(), that.getStore().getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCategory().getId(), getSubCategory().getId(), getDate(), getUser().getId(), getPrice(),
            getDiscount(), getDescription(), getStore().getId());
    }

    @PrePersist
    private void prePersist() {

        if (nonNull(this.products)) {
            this.products.forEach(p -> {
                p.getProductType().setUser(this.user);
                p.setDate(this.date);
            });
        }

        if (nonNull(this.category)) {
            this.subCategory.setExpenseCategory(this.category);
        }
    }
}
