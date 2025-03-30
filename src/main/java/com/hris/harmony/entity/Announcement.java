package com.hris.harmony.entity;

import com.hris.harmony.constant.Constant;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = Constant.ANNOUNCEMENT_TABLE)
public class Announcement extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    @NotBlank
    @Size(max = 100, message = "Announcement title must be at most 100 characters")
    @Column(name = "title", nullable = false, length = 100)
    private String title;
    
    @Lob
    @Column(columnDefinition = "TEXT", name = "content")
    private String content;
}
