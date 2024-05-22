package com.webapp.phystechschool.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RolesTest {
    @Test
    public void testRolesCreation() {
        Roles roles = new Roles();
        roles.setRoleId(1);
        roles.setRoleName("Admin");

        assertEquals(1, roles.getRoleId());
        assertEquals("Admin", roles.getRoleName());
    }
}
