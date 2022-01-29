package fr.gantoin.quizzukrainiannumber.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import lombok.Getter;

import fr.gantoin.quizzukrainiannumber.domain.Number;

@Service
public class CsvReader {

    @Getter
    private final List<Number> numbers = new ArrayList<>();

    public CsvReader() throws URISyntaxException, IOException {
        InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("static/numbers.csv");
        CsvReader.readFile(resourceAsStream).forEach(line -> {
            String[] strings = line.split(",");
            Number number = new Number(Integer.parseInt(strings[0]), strings[1]);
            numbers.add(number);
        });
    }

    public static List<String> readFile(InputStream resourceAsStream) throws IOException {
        List<String> result = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(resourceAsStream, StandardCharsets.UTF_8));

        for (String line = br.readLine(); line != null; line = br.readLine()) {
            result.add(line);
        }

        br.close();
        resourceAsStream.close();

        return result;
    }

    public Number getRandomNumber() {
        Random random = new Random();
        return numbers.get(random.nextInt(numbers.size()));
    }
}
