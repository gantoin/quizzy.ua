package fr.gantoin.quizzy.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Set;

import org.springframework.stereotype.Service;

import lombok.Getter;

import fr.gantoin.quizzy.domain.Day;
import fr.gantoin.quizzy.domain.Food;
import fr.gantoin.quizzy.domain.Month;
import fr.gantoin.quizzy.domain.Number;

@Service
public class CsvReader {

    @Getter
    private final List<Food> foodList = new ArrayList<>();

    @Getter
    private final List<Number> numberList = new ArrayList<>();

    @Getter
    private final List<Day> dayList = new ArrayList<>();

    @Getter
    private final List<Month> monthList = new ArrayList<>();

    public CsvReader() throws IOException {
        initNumbers();
        initDays();
        initMonths();
        initFood();
    }

    private void initMonths() throws IOException {
        InputStream monthStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("static/months.csv");
        CsvReader.readFile(monthStream).forEach(line -> {
            String[] strings = line.split(",");
            Month month = new Month(strings[0], strings[1], "audio/months/" + strings[0].toLowerCase(Locale.ROOT) + ".mp3");
            monthList.add(month);
        });
        if (monthStream != null) {
            monthStream.close();
        }
    }

    private void initDays() throws IOException {
        InputStream dayStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("static/days.csv");
        CsvReader.readFile(dayStream).forEach(line -> {
            String[] strings = line.split(",");
            Day day = new Day(strings[0], strings[1], "audio/days/" + strings[0].toLowerCase(Locale.ROOT) + ".mp3");
            dayList.add(day);
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
            numberList.add(number);
        });
        if (numberStream != null) {
            numberStream.close();
        }
    }

    private void initFood() throws IOException {
        InputStream foodStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("static/food.csv");
        CsvReader.readFile(foodStream).forEach(line -> {
            String[] strings = line.split(",");
            Food food = new Food(strings[0], strings[1]);
            foodList.add(food);
        });
        if (foodStream != null) {
            foodStream.close();
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
        return numberList.get(random.nextInt(numberList.size()));
    }

    public Day getRandomDay() {
        Random random = new Random();
        return dayList.get(random.nextInt(dayList.size()));
    }

    public Month getRandomMonth() {
        Random random = new Random();
        return monthList.get(random.nextInt(monthList.size()));
    }

    public Food getRandomFood() {
        Random random = new Random();
        return foodList.get(random.nextInt(foodList.size()));
    }

    public Set<Food> getFiveRandomFoodAndAnswer(Food answer) {
        Set<Food> food = new HashSet<>();
        while (food.size() < 5) {
            Random random = new Random();
            food.add(foodList.get(random.nextInt(foodList.size())));
        }
        food.add(answer);
        return food;
    }
}
