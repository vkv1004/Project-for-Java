package com.webapp.phystechschool.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Address extends BaseFormEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int addressId;

    @NotBlank(message = "Адрес не должен быть пустым")
    @Size(min = 5, message = "Длина адреса должна составлять не менее 5 символов")
    private String address;

    @NotBlank(message = "Номер мобильного телефона не должен быть пустым")
    @Pattern(regexp = "^(^$|\\+7 \\(\\d{3}\\) \\d{3}-\\d{2}-\\d{2}|8 \\(\\d{3}\\) \\d{3}-\\d{2}-\\d{2})", message = "Номер телефона должен соответствовать формату +7 (123) 123-12-12 или 8 (123) 123-12-12")
    private String mobileNumber;

    @NotBlank(message = "Город не должен быть пустым")
    private String city;

    @NotBlank(message = "Страна не должна быть пустой")
    private String state;

    @NotBlank(message = "Почтовый индекс не должен быть пустым")
    @Pattern(regexp = "(^$|[0-9]{6})", message = "Почтовый индекс должен состоять из 6 цифр")
    private String zipCode;
}
