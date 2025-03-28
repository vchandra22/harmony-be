package com.hris.harmony.constant;

public class Constant {
    // api base endpoint
    public static final String AUTH_API = "api/v1/auth";
    public static final String EMPLOYEE_API = "api/v1/employee";
    public static final String POSITION_API = "api/v1/positions";
    public static final String DEPARTMENT_API = "api/v1/departments";
    
    // table
    public static final String USER_TABLE = "m_user";
    public static final String EMPLOYEE_TABLE = "m_employee";
    public static final String POSITION_TABLE = "m_position";
    public static final String DEPARTMENT_TABLE = "m_department";
    
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
    public static final String SUCCESS_CREATE_POSITION = "Create Position Successfully!";
    public static final String SUCCESS_GET_ALL_POSITION = "Get All Positions Successfully!";
    public static final String SUCCESS_UPDATE_POSITION = "Update Position Successfully!";
    public static final String SUCCESS_GET_POSITION_BY_ID = "Get Position By Id Successfully!";
    public static final String SUCCESS_DELETE_POSITION = "Delete Position Successfully!";

    public static final String SUCCESS_GET_ALL_DEPARTMENT = "Get All Departments Successfully!";
    public static final String SUCCESS_GET_DEPARTMENT_BY_ID = "Get Department By Id Successfully!";
    public static final String SUCCESS_CREATE_DEPARTMENT = "Create Department Successfully!";
    public static final String SUCCESS_UPDATE_DEPARTMENT = "Update Department Successfully!";
    public static final String SUCCESS_DELETE_DEPARTMENT = "Delete Department Successfully!";
}
