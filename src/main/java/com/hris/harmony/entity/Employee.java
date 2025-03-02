package com.hris.harmony.entity;

import com.hris.harmony.constant.Constant;
import jakarta.persistence.*;
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
    
    @Column(name = "phone", nullable = false, unique = true)
    private String phone;
    
    @Column(name = "birth_place")
    private String birth_place;
    
    @Column(name = "birth_date")
    private Date birth_date;
    
    @Column(name = "address")
    private String address;
    
    @Column(name = "hire_date")
    private Date hire_date;
    
    @Column(name = "salary", precision = 10, scale = 2)
    private BigDecimal salary;
    
    @OneToOne
    @JoinColumn(name = "user_account_id")
    private UserAccount userAccount;
}
