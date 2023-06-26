package br.ufg.inf.model;

import lombok.Data;

import java.util.List;

@Data
public class Discipline {
    public Integer idDiscipline;
    private String disciplineName;
    private Integer studentsNumber;
    private Integer sequence;
    private Class classe;
}
