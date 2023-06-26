package br.ufg.inf;

import br.ufg.inf.file.CsvReader;
import br.ufg.inf.model.*;
import br.ufg.inf.model.Class;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.IOException;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {


        List<InputFile> obj = CsvReader.read();
        List<String> hashs = new ArrayList<>();
        List<String> hashsA = new ArrayList<>();
        List<Institution> institutions = new ArrayList<>();
        Collision collision = new Collision();
        Map<String, Institution> institutionMap = new HashMap<>();

        String pk = String.format("%s.%s.%s.%s.%s.%s.%s.%s","idInstitution","idUnit", "idClass", "idRoom", "idDay", "idTeacher", "idBeginSlot", "idEndSlot");

        for(InputFile input : obj){
            String hashCollision = String.format("%d.%d.%d.%d.%d.%d.%d.%d",input.getIdInstitution(), input.getIdUnit(), input.getIdClass()
                    , input.getIdRoom(), input.getIdDay(), input.getIdTeacher(), input.getIdBeginSlot(), input.getIdEndSlot() );
            hashs.add(hashCollision);

            String hashCollisionTotal = String.format("%d.%d.%d.%d.%d", input.getIdRoom(), input.getIdDay(), input.getIdTeacher(), input.getIdBeginSlot(), input.getIdEndSlot() );
            hashsA.add(hashCollisionTotal);

            Teacher teacher = new Teacher();
            teacher.setIdTeacher(input.getIdTeacher());
            teacher.setTeacherName(input.getTeacherName());

            Discipline discipline = new Discipline();
            discipline.setIdDiscipline(input.getIdDiscipline());
            discipline.setDisciplineName(input.getDisciplineName());
            discipline.setStudentsNumber(input.getStudentsNumber());
            discipline.setSequence(input.getSequence());

            Room room = new Room();
            room.setIdRoom(input.getIdRoom());
            room.setRoomName(input.getRoomName());

            Class classe = new Class();
            classe.setIdClass(input.getIdClass());
            classe.setClassName(input.getClassName());
            classe.setIdDay(input.getIdDay());
            classe.setIdBeginSlot(input.getIdBeginSlot());
            classe.setBeginTimeName(input.getBeginTimeName());
            classe.setIdEndSlot(input.getIdEndSlot());
            classe.setEndTimeName(input.getEndTimeName());
            classe.setIdYear(input.getIdYear());
            classe.setIdTerm(input.getIdTerm());
            classe.setTeacher(teacher);
            classe.setRoom(room);

            discipline.setClasse(classe);

            Course course = new Course();
            course.setIdCourse(input.getIdCourse());
            course.setDiscipline(discipline);
            course.setCourseName(input.getCourseName());

            Unit unit = new Unit();
            unit.setIdUnit(input.getIdUnit());
            unit.setUnitName(input.getUnitName());
            unit.setCourse(course);

            Institution institution = new Institution();
            institution.setIdInstitution(input.getIdInstitution());
            institution.setUnit(unit);
            institutions.add(institution);

        }

//        List<String>collisionTotalList = hashs.stream().filter(str -> hashs.indexOf(str) != hashs.lastIndexOf(str)).collect(Collectors.toList());
//        List<String>collisionTotalA = hashsA.stream().filter(str -> hashsA.indexOf(str) != hashsA.lastIndexOf(str)).collect(Collectors.toList());
//        System.out.println("Hash: " + pk);
//        for(String string: hashs){
//            System.out.println("line: " + string);
//        }
//        //System.out.println("CompleteList: " + hashs);
//        System.out.println("CollisionTotal: " + collisionTotalList);
//
//            List<InputFile> conflitos = new ArrayList<>();
//            for (int i=0; i<conflitos.size(); i++){
//                InputFile classeA = conflitos.get(i);
//                for(int j = i+1; j<conflitos.size(); j++){
//                    InputFile classeB = conflitos.get(j);
//                    if (classeA.getIdRoom() == classeB.getIdRoom()
//                            && classeA.getIdTeacher() == classeB.getIdTeacher()
//                            //&& classeA.getIdBeginSlot() == classeB.getIdBeginSlot()
//                            //&& classeA.getIdEndSlot() == classeB.getIdEndSlot()
//                    ){
//                        conflitos.add(classeA);
//                        conflitos.add(classeB);
//                    }
//                }
//            }
        //System.out.println("CollisionTotal: " + conflitos);
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter();
        for(Institution institution : institutions){
            String json = writer.writeValueAsString(institution);
            //System.out.println(json);
            //System.out.println(institution);
        }
        List<InputFile> teste= checkConflicts(obj);
        for(InputFile a : teste){
            //String json = writer.writeValueAsString(institution);
            //System.out.println(json);
            //System.out.println(institution);
            System.out.println(formatMessage(a));
        }

        //System.out.println(collisionTotalA);
    }

    public static List<InputFile> checkConflicts(List<InputFile> institutions) {
        List<InputFile> conflitos = new ArrayList<>();

        for (int i = 0; i < institutions.size(); i++) {
            InputFile institutionA = institutions.get(i);

            // Verifica conflito com objetos anteriores na lista
            for (int j = 0; j < i; j++) {
                InputFile institutionB = institutions.get(j);

                if (isTotalConflict(institutionA, institutionB)) {
                    institutionA.setTotalConflict(true);
                    institutionB.setTotalConflict(true);
                    institutionA.setTypeConflict(typeConflict(institutionA, institutionB));
                    institutionB.setTypeConflict(typeConflict(institutionA, institutionB));
                    conflitos.add(institutionA);
                    conflitos.add(institutionB);
                }else if (isPartialConflict(institutionA, institutionB)) {
                    institutionA.setTypeConflict(typeConflict(institutionA, institutionB));
                    institutionB.setTypeConflict(typeConflict(institutionA, institutionB));
                    conflitos.add(institutionA);
                    conflitos.add(institutionB);
                }
            }
        }

        return conflitos;
    }

    public static boolean isTotalConflict(InputFile institutionA, InputFile institutionB) {
        // Verifica conflito de horário total

        return (institutionA.getIdDay() == institutionB.getIdDay()
                && institutionA.getBeginTimeName().equals(institutionB.getBeginTimeName())
                && institutionA.getEndTimeName().equals(institutionB.getEndTimeName()))
                && (institutionA.getIdRoom() == institutionB.getIdRoom()
                || institutionA.getIdTeacher() == institutionB.getIdTeacher());
    }

    public static String typeConflict(InputFile institutionA, InputFile institutionB){
        String typeConflict = "";

        if(institutionA.getIdRoom() == institutionB.getIdRoom() && institutionA.getIdTeacher() == institutionB.getIdTeacher()){
            typeConflict = "Professor, Sala";
        }else if(institutionA.getIdTeacher() == institutionB.getIdTeacher()){
            typeConflict = "Professor";
        }else if(institutionA.getIdRoom() == institutionB.getIdRoom()){
            typeConflict = "Sala";
        }


        return typeConflict;
    }

    public static boolean isPartialConflict(InputFile institutionA, InputFile institutionB) {
        // Verifica conflito de horário parcial

        return institutionA.getIdDay() == institutionB.getIdDay()
                && ((institutionA.getBeginTimeName().compareTo(institutionB.getBeginTimeName()) >= 0
                && institutionA.getBeginTimeName().compareTo(institutionB.getEndTimeName()) < 0)
                || (institutionA.getEndTimeName().compareTo(institutionB.getBeginTimeName()) > 0
                && institutionA.getEndTimeName().compareTo(institutionB.getEndTimeName()) <= 0))
                && institutionA.getIdRoom() == institutionB.getIdRoom();
    }

    public static String formatMessage(InputFile institution) {

        String conflict = "%s %s – %s, %s -> [Instituição: %d, Unidade: %d, Curso: %d, Turma %s, Prof: %s, Sala: %s]";
        return String.format(conflict,
                getWeekDay(institution.getIdDay()),
                institution.getBeginTimeName() + "-" + institution.getEndTimeName(),
                institution.getTypeConflict(),
                institution.isTotalConflict() ? "total" : "parcial",
                institution.getIdInstitution(),
                institution.getIdUnit(),
                institution.getIdCourse(),
                institution.getClassName(),
                institution.getIdTeacher(),
                institution.getIdRoom());
    }

    public static String getWeekDay(int idDay) {
        switch (idDay) {
            case 1:
                return "Seg";
            case 2:
                return "Ter";
            case 3:
                return "Qua";
            case 4:
                return "Qui";
            case 5:
                return "Sex";
            case 6:
                return "Sáb";
            case 7:
                return "Dom";
            default:
                return "";
        }
    }
}