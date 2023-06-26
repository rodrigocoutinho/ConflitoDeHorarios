package br.ufg.inf.model;

import lombok.Data;

import java.time.LocalTime;
import java.util.List;

@Data
public class Class {
    private Integer idClass;
    private String className;
    private Integer idDay;
    private Integer idBeginSlot;
    private String beginTimeName;
    private Integer idEndSlot;
    private String endTimeName;
    private Integer idYear;
    private Integer idTerm;
    private Teacher teacher;
    private Room room;
}
