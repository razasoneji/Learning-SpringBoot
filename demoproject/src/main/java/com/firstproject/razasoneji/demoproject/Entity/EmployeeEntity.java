package com.firstproject.razasoneji.demoproject.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
// defines schema at table level, like multirow unique , like indexing, multiindexing ,catalog , schema , name of table etc.
@Table(name = "employees"
        //,catalog = "employee_catalog"
       // ,schema = "emp_schema"
        ,uniqueConstraints = {
        @UniqueConstraint(columnNames = {"email"}),
        @UniqueConstraint(columnNames = {"phone"})
        },
        indexes = {@Index(name = "email_idx",columnList = "email"),
                   @Index(name = "phone_no_idx",columnList = "phone")})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private Integer age;

    private Integer salary;

    private String gender;

    @Column(name = "email" , nullable = false)
    private String email;

    @Column(name = "phone", nullable = false)
    private String phone;

    private String department;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
