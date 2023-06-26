package br.ufg.inf.model;

import lombok.Data;

import java.util.List;

@Data
public class Unit {
    private Integer idUnit;
    private String unitName;
    private Course course;
}
