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
public class NumbersApi {

    private final CsvReader csvReader;

    @GetMapping("/numbers-listen")
    public String numbersListen(Model model) {
        return "building";
    }

    @GetMapping("/numbers")
    public String readForm(Model model) {
        model.addAttribute("number", csvReader.getRandomNumber());
        return "numbers/read";
    }

    @PostMapping("/numbers")
    public String postAnswer(Model model, @RequestParam("answer") String answer, @RequestParam("number") int number) {
        if (answer == null || answer.isEmpty()) {
            model.addAttribute("result", "Wrong!");
        } else {
            if (Integer.parseInt(answer) == number) {
                model.addAttribute("result", "Correct!");
            } else {
                model.addAttribute("result", "Wrong!");
            }
        }
        return "result";
    }
}
