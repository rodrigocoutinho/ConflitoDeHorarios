package br.ufg.inf;

import br.ufg.inf.file.CsvReader;
import br.ufg.inf.model.Horario;

import javax.inject.Inject;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {


        List<Horario> obj = CsvReader.read();



        //System.out.println(obj);
    }
}