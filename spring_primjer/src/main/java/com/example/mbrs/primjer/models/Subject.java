package com.example.mbrs.primjer.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "subject")
@Data
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "subjects")
    private Set<Student> students;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Professor professor;
}
