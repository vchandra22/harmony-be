package com.hris.harmony.constant;

public enum UserRole {
    ROLE_KARYAWAN("KARYAWAN"),
    ROLE_SUPERVISON("SUPERVISOR"),
    ROLE_FINANCE("FINANCE"),
    ROLE_ADMIN("ADMIN");
    private String value;
    
    UserRole(String value) {
        this.value = value;
    }
    
    public static UserRole fromValue(String value) {
        for (UserRole userRole : UserRole.values()) {
            if (userRole.value.equalsIgnoreCase(value)) {
                return userRole;
            }
        }
        return null;
    }
}
