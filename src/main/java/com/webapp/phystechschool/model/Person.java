package com.webapp.phystechschool.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.webapp.phystechschool.annotation.FieldsValueMatch;
import com.webapp.phystechschool.annotation.PasswordValidator;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@FieldsValueMatch(
        field = "pwd",
        fieldMatch = "confirmpwd",
        message = "Пароли не совпадают"
)
public class Person extends BaseFormEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int personId;

    @NotBlank(message = "Имя не должно быть пустым")
    @Size(min = 3, message = "Длина имени должна составлять не менее 3 символов")
    private String name;

    @NotBlank(message = "Адрес электронной почты не должен быть пустым")
    @Email(message = "Пожалуйста, укажите действительный адрес электронной почты")
    private String email;

    @NotBlank(message = "Пароль не должен быть пустым")
    @Size(min = 5, message = "Длина пароля должна составлять не менее 4 символов")
    @PasswordValidator
    @JsonIgnore
    private String pwd;

    @Column(name = "reset_password_token")
    @JsonIgnore
    private String resetPasswordToken;

    @NotBlank(message = "Пароль не должен быть пустым")
    @Transient
    @JsonIgnore
    private String confirmpwd;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, targetEntity = Roles.class)
    @JoinColumn(name = "role_id", referencedColumnName = "roleId", nullable = false)
    private Roles roles;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = Address.class)
    @JoinColumn(name = "address_id", referencedColumnName = "addressId")
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id", referencedColumnName = "classId")
    private PhystechClass phystechClass;
}
