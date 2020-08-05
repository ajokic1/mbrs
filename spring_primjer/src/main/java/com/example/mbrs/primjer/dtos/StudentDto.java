package com.example.mbrs.primjer.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {
    private long id;
    private String firstName;
    private String lastName;
    private int year;
    private Date enrollDate;
}
