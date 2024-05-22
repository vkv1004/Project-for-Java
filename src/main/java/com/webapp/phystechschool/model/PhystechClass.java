package com.webapp.phystechschool.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "class")
public class PhystechClass extends BaseFormEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int classId;

    @NotBlank(message = "Имя не должно быть пустым")
    @Size(min = 3, message = "Длина имени должна составлять не менее 3 символов")
    private String name;

    @OneToMany(mappedBy = "phystechClass", fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST, targetEntity = Person.class)
    private Set<Person> persons = new HashSet<>();
}
