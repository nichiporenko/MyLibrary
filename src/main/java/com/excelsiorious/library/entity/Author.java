package com.excelsiorious.library.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

@NamedQueries({
        @NamedQuery(name = "selectAllAuthors", query = "SELECT a FROM Author a"),
        @NamedQuery(name = "selectAuthorByName", query = "SELECT a FROM Author a WHERE a.firstName = :firstName AND " +
                "a.lastName = :lastName")
})
@Entity
@Table(name = "authors")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Author extends BasicEntity implements Serializable {
    @NotNull
    @Size(min = 3, max = 50)
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @Size(min = 3, max = 50)
    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
