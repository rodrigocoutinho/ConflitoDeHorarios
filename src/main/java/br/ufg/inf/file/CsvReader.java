package br.ufg.inf.file;

import br.ufg.inf.model.InputFile;
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

    public static List<InputFile> read() throws IOException {
        Reader reader = Files.newBufferedReader(Paths.get("ColisaoHorarios-DadosEntrada.csv"));

        CsvToBean<InputFile> csvToBean = new CsvToBeanBuilder(reader)
                .withType(InputFile.class)
                .build();

        List<InputFile> testes = csvToBean.parse();

//        for (InputFile teste : testes){
//            System.out.println(teste);
//        }
        return testes;
    }
}
