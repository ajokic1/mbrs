package com.example.mbrs.primjer.repositories;

import com.example.mbrs.primjer.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends BaseStudentRepository {

}
