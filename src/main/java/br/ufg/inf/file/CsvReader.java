package br.ufg.inf.file;

import br.ufg.inf.model.Horario;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import javax.enterprise.context.ApplicationScoped;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@ApplicationScoped
public class CsvReader {

    public static List<Horario> read() throws IOException {
        Reader reader = Files.newBufferedReader(Paths.get("ColisaoHorarios-DadosEntrada.csv"));

        CsvToBean<Horario> csvToBean = new CsvToBeanBuilder(reader)
                .withType(Horario.class)
                .build();

        List<Horario> testes = csvToBean.parse();

        for (Horario teste : testes){
            System.out.println(teste);
        }
        return testes;
    }
}
