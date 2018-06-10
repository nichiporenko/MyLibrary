package com.excelsiorious.library.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@NamedQueries({
        @NamedQuery(name = "selectAllLanguages", query = "SELECT l FROM Language l"),
        @NamedQuery(name = "selectLanguageByName", query = "SELECT l FROM Language l WHERE l.name = :name")
})
@Entity
@Table(name = "languages")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Language extends BasicEntity implements Serializable {
    @NotNull
    @Size(min = 3, max = 50)
    @Column(name = "name")
    private String name;

    @Override
    public String toString() {
        return name;
    }
}
