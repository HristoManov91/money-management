package com.example.money_management_be.entity;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "addresses")
public class AddressEntity extends BaseEntity {

    @Column(name = "country", length = 50)
    String country;

    @Column(name = "city", length = 50)
    String city;

    @Column(name = "street", length = 100)
    String street;


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AddressEntity)) {
            return false;
        }
        AddressEntity that = (AddressEntity) o;
        return Objects.equals(getCountry(), that.getCountry())
            && Objects.equals(getCity(), that.getCity())
            && Objects.equals(getStreet(), that.getStreet())
            && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCountry(), getCity(), getStreet(), getId());
    }
}
