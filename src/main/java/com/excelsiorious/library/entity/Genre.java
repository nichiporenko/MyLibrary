package com.excelsiorious.library.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@NamedQueries({
        @NamedQuery(name = "selectAllGenres", query = "SELECT g FROM Genre g"),
        @NamedQuery(name = "selectGenreByName", query = "SELECT g FROM Genre g WHERE g.name = :name")
})
@Entity
@Table(name = "genres")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Genre extends BasicEntity implements Serializable {
    @NotNull
    @Size(min = 3, max = 50)
    @Column(name = "name")
    private String name;

    @Override
    public String toString() {
        return name;
    }
}
