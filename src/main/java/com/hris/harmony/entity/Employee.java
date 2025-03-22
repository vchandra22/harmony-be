package com.hris.harmony.entity;

import com.hris.harmony.constant.Constant;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = Constant.EMPLOYEE_TABLE)
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotBlank(message = "First name is required")
    @Size(max = 50, message = "First name must be at most 50 characters")
    @Column(name = "first_name", nullable = false, length = 50)
    private String first_name;

    @NotBlank(message = "Last name is required")
    @Size(max = 50, message = "Last name must be at most 50 characters")
    @Column(name = "last_name", nullable = false, length = 50)
    private String last_name;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[0-9]{10,15}$", message = "Phone number must be 10-15 digits")
    @Column(name = "phone", nullable = false, unique = true)
    private String phone;

    @Size(max = 100, message = "Birth place must be at most 100 characters")
    @Column(name = "birth_place", nullable = true)
    private String birth_place;

    @Column(name = "birth_date", nullable = true)
    private Date birth_date;

    @Size(max = 255, message = "Address must be at most 255 characters")
    @Column(name = "address", nullable = true)
    private String address;

    @NotNull(message = "Hire date is required")
    @PastOrPresent(message = "Hire date cannot be in the future")
    @Column(name = "hire_date", nullable = false)
    private Date hire_date;

    @NotNull(message = "Salary is required")
    @DecimalMin(value = "0.00", message = "Salary must be positive")
    @Digits(integer = 10, fraction = 2, message = "Invalid salary format")
    @Column(name = "salary", precision = 10, scale = 2, nullable = false)
    private BigDecimal salary;
    
    @OneToOne
    @JoinColumn(name = "user_account_id")
    private UserAccount userAccount;
    
    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position position;
}
