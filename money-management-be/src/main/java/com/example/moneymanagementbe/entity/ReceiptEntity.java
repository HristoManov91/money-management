package com.example.moneymanagementbe.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name = "receipts")
public class ReceiptEntity extends BaseEntity {

    @Column(name = "date" , nullable = false)
    LocalDate date;

    @ManyToOne(targetEntity = UserEntity.class, fetch = FetchType.LAZY, optional = false)//TODO cascade
    UserEntity user;

    @OneToMany(targetEntity = ProductEntity.class, fetch = FetchType.EAGER)//TODO cascade
    List<ProductEntity> products;

    @Column(name = "total_price", scale = 2)
    BigDecimal totalPrice;

    @Column(name = "total_discount", scale = 2)
    BigDecimal totalDiscount;

    @Column(name = "description")
    String description;

    @ManyToOne(targetEntity = StoreEntity.class, fetch = FetchType.EAGER, optional = false)//TODO cascade
    StoreEntity store;


}
