package com.example.mbrs.primjer.converters;

import com.example.mbrs.primjer.dtos.StudentDto;
import com.example.mbrs.primjer.models.Student;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class StudentConverter{

    @Autowired
    ModelMapper modelMapper;

    public StudentDto convertToDto(Student student) {
        return modelMapper.map(student, StudentDto.class);
    }

    public Student convertToEntity(StudentDto studentDto) {
        return modelMapper.map(studentDto, Student.class);
    }

    public Student updateEntityFromDto(Student student, StudentDto studentDto) {
        studentDto.setId(student.getId());
        modelMapper.map(studentDto, student);
        return student;
    }

    public List<StudentDto> convertListToDto(List<Student> students){
        List<StudentDto> studentDtos = new LinkedList<>();
        for(Student student: students) {
            studentDtos.add(convertToDto(student));
        }
        return studentDtos;
    }

    public List<Student> convertListToEntity(List<StudentDto> studentDtos){
        List<Student> students = new LinkedList<>();
        for(StudentDto studentDto: studentDtos) {
            students.add(convertToEntity(studentDto));
        }
        return students;
    }
}
