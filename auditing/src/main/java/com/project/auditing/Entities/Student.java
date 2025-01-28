package com.project.auditing.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student extends Audit{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Short sem;

    private Short cpi;

    private Short rollNo;

//    @PrePersist
//    void beforeSave() {
//
//    }
//
//    @PreUpdate
//    void beforeUpdate() {
//
//    }
//
//    @PreRemove
//    void beforeDelete() {
//
//    }

}
