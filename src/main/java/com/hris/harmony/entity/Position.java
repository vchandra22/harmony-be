package com.hris.harmony.entity;

import com.hris.harmony.constant.Constant;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = Constant.POSITION_TABLE)
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotBlank(message = "Position name is required")
    @Size(max = 100, message = "Position name must be at most 100 characters")
    @Column(name = "name", nullable = false, unique = true, length = 100)
    private String name;
}
