package com.example.mbrs.primjer.services;

import com.example.mbrs.primjer.models.Student;
import com.example.mbrs.primjer.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public abstract class BaseStudentService{

    @Autowired
    StudentRepository studentRepository;

    public Student getStudentById(long id) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        return studentOptional.orElse(null);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public List<Student> getStudentsByYear(int year) {
        return studentRepository.findByYear(year);
    }

    public void deleteStudent(long id) throws IllegalArgumentException {
        studentRepository.deleteById(id);
    }

    public Student createOrUpdateStudent(Student student) {
        return studentRepository.save(student);
    }
}
