package fr.gantoin.quizzy.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

import fr.gantoin.quizzy.domain.Food;
import fr.gantoin.quizzy.domain.Word;
import fr.gantoin.quizzy.service.CsvReader;

@RequiredArgsConstructor
@Controller
public class ApplicationApi {

    private final CsvReader csvReader;

    @GetMapping(path = {"/", "/index"})
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/days-listen")
    public String daysListen(Model model) {
        return getTemplate(model, csvReader.getRandomDay(), csvReader.getDayList(), "listen");
    }

    @GetMapping("/months-listen")
    public String monthsListen(Model model) {
        return getTemplate(model, csvReader.getRandomMonth(), csvReader.getMonthList(), "listen");
    }

    @GetMapping("/food")
    public String foodForm(Model model) {
        Food answer = csvReader.getRandomFood();
        return getTemplate(model, answer, new ArrayList<>(csvReader.getFiveRandomFoodAndAnswer(answer)), "read");
    }

    @GetMapping("/months")
    public String monthsForm(Model model) {
        return getTemplate(model, csvReader.getRandomMonth(), csvReader.getMonthList(), "read");
    }

    @GetMapping("/days")
    public String daysForm(Model model) {
        return getTemplate(model, csvReader.getRandomDay(), csvReader.getDayList(), "read");
    }

    private String getTemplate(Model model, Word guess, List<?> list, String templateName) {
        model.addAttribute("guess", guess);
        model.addAttribute("list", list);
        return templateName;
    }

    @PostMapping("/answer")
    public String postAnswer(Model model, @RequestParam("answer") String answer, @RequestParam("solution") String solution) {
        if (answer.equals(solution)) {
            model.addAttribute("result", "Correct!");
        } else {
            model.addAttribute("result", "Wrong!");
        }
        return "result";
    }
}
