package fr.gantoin.quizzy.api;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

import fr.gantoin.quizzy.service.CsvReader;

@RequiredArgsConstructor
@Controller
public class MonthsApi {

    private final CsvReader csvReader;

    @GetMapping("/months-listen")
    public String monthsListen(Model model) {
        model.addAttribute("guess", csvReader.getRandomMonth());
        model.addAttribute("months", csvReader.getMonthList());
        return "months/listen";
    }

    @GetMapping("/months")
    public String monthsForm(Model model) {
        model.addAttribute("guess", csvReader.getRandomMonth());
        model.addAttribute("months", csvReader.getMonthList());
        return "months/read";
    }

    @PostMapping("/months")
    public String postMonthsAnswer(Model model, @RequestParam("answer") String answer, @RequestParam("solution") String solution) {
        if (answer.equals(solution)) {
            model.addAttribute("result", "Correct!");
        } else {
            model.addAttribute("result", "Wrong!");
        }
        return "result";
    }
}
