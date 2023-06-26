package br.ufg.inf.model;

import lombok.Data;

@Data
public class InputFile {
        private int id;
        private int idSolution;
        private String solutionName;
        private String solutionInitials;
        private int idTeacher;
        private String teacherName;
        private int idDay;
        private int idInstitution;
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
        private boolean isTotalConflict;
        private String typeConflict;

        public boolean isTotalConflict() {
                return isTotalConflict;
        }

        public void setTotalConflict(boolean totalConflict) {
                isTotalConflict = totalConflict;
        }
}
