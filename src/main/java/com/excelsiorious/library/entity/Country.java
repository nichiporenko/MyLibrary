package com.excelsiorious.library.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@NamedQueries({
        @NamedQuery(name = "selectAllCountries", query = "SELECT c FROM Country c"),
        @NamedQuery(name = "selectCountryByName", query = "SELECT c FROM Country c WHERE c.name = :name")
})
@Entity
@Table(name = "countries")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Country extends BasicEntity implements Serializable {
    @NotNull
    @Size(min = 3, max = 50)
    @Column(name = "name")
    private String name;

    @Override
    public String toString() {
        return name;
    }
}
