package com.hris.harmony.constant;

public class Constant {
    // api base endpoint
    public static final String AUTH_API = "api/v1/auth";
    public static final String EMPLOYEE_API = "api/v1/employee";
    
    // table
    public static final String USER_TABLE = "m_user";
    public static final String EMPLOYEE_TABLE = "m_employee";
    
    // message
    public static final String SUCCESS_LOGIN = "Login Successfully!";
    public static final String SUCCESS_REGISTER = "Registered Successfully!";
    public static final String ERROR_USERNAME_EXISTS = "Username already exists!";
    public static final String ERROR_CREATING_JWT = "Error creating JWT!";
    public static final String ERROR_INVALID_CREDENTIALS = "Invalid credentials!";
    public static final String SUCCESS_GET_ALL_EMPLOYEE = "Get All Employees Successfully!";
    public static final String SUCCESS_GET_EMPLOYEE_BY_ID = "Get Employee By Id Successfully!";
    public static final String SUCCESS_UPDATE_EMPLOYEE = "Update Employee Successfully!";
    public static final String SUCCESS_DELETE_EMPLOYEE = "Delete Employee Successfully!";
}
