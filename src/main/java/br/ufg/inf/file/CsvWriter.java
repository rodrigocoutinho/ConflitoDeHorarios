package br.ufg.inf.file;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import javax.enterprise.context.ApplicationScoped;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@ApplicationScoped
public class CsvWriter {

    public static void write(List<Object> objects) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        // List<MyBean> beans comes from somewhere earlier in your code.
        Writer writer = new FileWriter("objectsOutPut.csv");
        StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(writer).build();
        beanToCsv.write(objects);
        writer.close();
    }


}
