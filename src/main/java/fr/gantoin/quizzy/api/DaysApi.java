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
public class DaysApi {

    private final CsvReader csvReader;

    @GetMapping("/days-listen")
    public String daysListen(Model model) {
        model.addAttribute("guess", csvReader.getRandomDay());
        model.addAttribute("days", csvReader.getDayList());
        return "days/listen";
    }

    @GetMapping("/days")
    public String daysForm(Model model) {
        model.addAttribute("guess", csvReader.getRandomDay());
        model.addAttribute("days", csvReader.getDayList());
        return "days/read";
    }

    @PostMapping("/days")
    public String postDaysAnswer(Model model, @RequestParam("answer") String answer, @RequestParam("solution") String solution) {
        if (answer.equals(solution)) {
            model.addAttribute("result", "Correct!");
        } else {
            model.addAttribute("result", "Wrong!");
        }
        return "result";
    }
}
