package com.webapp.phystechschool.model;

/*@Data Annotation is provided by lombok library which generates getter, setter,
equals(), hashcode(), toString() methods & Constructor at compile time.
This makes our code short and clean.
 */

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "contact_msg")
public class Contact extends BaseFormEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int contactId;

    private String status;

    @NotBlank(message = "Имя не должно быть пустым")
    @Size(min = 3, message = "Длина имени должна составлять не менее 3 символов")
    private String name;

    @NotBlank(message = "Адрес электронной почты не должен быть пустым")
    @Email(message = "Пожалуйста, укажите действительный адрес электронной почты")
    private String email;

    @NotBlank(message = "Тема не должна быть пустой")
    @Size(min = 5, message = "Тема письма должна содержать не менее 5 символов")
    private String subject;

    @NotBlank(message = "Сообщение не должно быть пустым")
    @Size(min = 10, message = "Длина сообщения должна составлять не менее 10 символов")
    private String message;
}