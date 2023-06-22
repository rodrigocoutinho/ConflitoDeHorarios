package br.ufg.inf.model;

import lombok.Data;

@Data
public class Horario {
        private int id;
        private int idsolution;
        private String solutionname;
        private String solutioninitials;
        private int idteacher;
        private String teachername;
        private int idDay;
        private int idInsitution;
        private int idUnit;
        private String unitName;
        private int idUnitCourse;
        private int idCourse;
        private String courseName;
        private int idClass;
        private String className;
        private int idDiscipline;
        private String disciplineName;
        private int idRoom;
        private String roomName;
        private int studentsNumber;
        private int sequence;
        private int idBeginSlot;
        private String beginTimeName;
        private int idEndSlot;
        private String endTimeName;
        private int idYear;
        private int idTerm;
        private int idCollisionType;
        private int collisionLevel;
        private int collisionSize;
    }
