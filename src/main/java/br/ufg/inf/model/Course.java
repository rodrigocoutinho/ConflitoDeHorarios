package br.ufg.inf.model;

import lombok.Data;

import java.util.List;

@Data
public class Course {
    private Integer idCourse;
    private String courseName;
    private Discipline discipline;
}
