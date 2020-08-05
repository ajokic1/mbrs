package com.example.mbrs.primjer.controllers;

import com.example.mbrs.primjer.converters.StudentConverter;
import com.example.mbrs.primjer.dtos.StudentDto;
import com.example.mbrs.primjer.models.Student;
import com.example.mbrs.primjer.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public abstract class BaseStudentController {

    @Autowired
    StudentService studentService;

    @Autowired
    StudentConverter studentConverter;

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudent(@PathVariable long id) {
        Student student = studentService.getStudentById(id);
        if (student == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(studentConverter.convertToDto(student));
    }

    @GetMapping
    public ResponseEntity<?> getAllStudents() {
        return ResponseEntity.ok(studentConverter.convertListToDto(studentService.getAllStudents()));
    }

    @GetMapping("/year/{year}")
    public ResponseEntity<?> getStudentsByYear(@PathVariable int year) {
        return ResponseEntity.ok(studentConverter.convertListToDto(studentService.getStudentsByYear(year)));
    }

    @PostMapping
    public ResponseEntity<?> createStudent(@RequestBody StudentDto studentDto){
        return ResponseEntity.ok(studentConverter.convertToDto(
                studentService.createOrUpdateStudent(studentConverter.convertToEntity(studentDto))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable long id) {
        try{
            studentService.deleteStudent(id);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable long id, @RequestBody StudentDto studentDto) {
        Student student = studentService.getStudentById(id);
        if (student == null)
            return ResponseEntity.notFound().build();
        student = studentService.createOrUpdateStudent(studentConverter.updateEntityFromDto(student, studentDto));
        return ResponseEntity.ok(student);
    }
}
