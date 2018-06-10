package com.excelsiorious.library.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@NamedQueries({
        @NamedQuery(name = "selectAllPublishingHouses", query = "SELECT p FROM PublishingHouse p"),
        @NamedQuery(name = "selectPublishingHouseByName", query = "SELECT p FROM PublishingHouse p WHERE p.name = :name")
})
@Entity
@Table(name = "publishing_houses")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PublishingHouse extends BasicEntity implements Serializable {
    @NotNull
    @Size(min = 3, max = 50)
    @Column(name = "name")
    private String name;

    @Override
    public String toString() {
        return name;
    }
}
