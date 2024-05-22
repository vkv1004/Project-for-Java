package com.webapp.phystechschool.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Roles extends BaseFormEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int roleId;

    private String roleName;
}
