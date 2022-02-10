package fr.gantoin.quizzy.service;

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

import fr.gantoin.quizzy.domain.Day;
import fr.gantoin.quizzy.domain.Month;
import fr.gantoin.quizzy.domain.Number;

@Service
public class CsvReader {

    @Getter
    private final List<Number> numbers = new ArrayList<>();

    @Getter
    private final List<Day> days = new ArrayList<>();

    @Getter
    private final List<Month> months = new ArrayList<>();

    public CsvReader() throws URISyntaxException, IOException {
        initNumbers();
        initDays();
        initMonths();
    }

    private void initMonths() throws IOException {
        InputStream monthStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("static/months.csv");
        CsvReader.readFile(monthStream).forEach(line -> {
            String[] strings = line.split(",");
            Month month = new Month(strings[0], strings[1]);
            months.add(month);
        });
        if (monthStream != null) {
            monthStream.close();
        }
    }

    private void initDays() throws IOException {
        InputStream dayStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("static/days.csv");
        CsvReader.readFile(dayStream).forEach(line -> {
            String[] strings = line.split(",");
            Day day = new Day(strings[0], strings[1]);
            days.add(day);
        });
        if (dayStream != null) {
            dayStream.close();
        }
    }

    private void initNumbers() throws IOException {
        InputStream numberStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("static/numbers.csv");
        CsvReader.readFile(numberStream).forEach(line -> {
            String[] strings = line.split(",");
            Number number = new Number(Integer.parseInt(strings[0]), strings[1]);
            numbers.add(number);
        });
        if (numberStream != null) {
            numberStream.close();
        }
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

    public Day getRandomDay() {
        Random random = new Random();
        return days.get(random.nextInt(days.size()));
    }

    public Month getRandomMonth() {
        Random random = new Random();
        return months.get(random.nextInt(months.size()));
    }
}
