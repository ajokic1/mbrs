package com.example.mbrs.primjer.repositories;

import com.example.mbrs.primjer.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface BaseStudentRepository extends JpaRepository<Student, Long> {
    public List<Student> findByYear(int year);
}
