package com.example.moneymanagementbe.entity;

import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
@Table(name = "stores")
public class StoreEntity extends BaseEntity {

    @Column(name = "name", length = 100, nullable = false)
    String name;

    @OneToOne(
        targetEntity = AddressEntity.class,
        fetch = FetchType.LAZY,
        optional = false,
        cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH})
    AddressEntity address;

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
        if (!(o instanceof StoreEntity)) {
            return false;
        }
        StoreEntity that = (StoreEntity) o;
        return getName().equals(that.getName()) && getAddress().equals(that.getAddress()) && getUser().equals(that.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getAddress(), getUser());
    }
}
