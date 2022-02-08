package fr.gantoin.quizzukrainiannumber.api;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

import fr.gantoin.quizzukrainiannumber.service.CsvReader;

@RequiredArgsConstructor
@Controller
public class ApplicationApi {

    private final CsvReader csvReader;

    @GetMapping(path = {"/", "/index"})
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/listen")
    public String listenForm(Model model) {
        return "listen";
    }

    @GetMapping("/read")
    public String readForm(Model model) {
        model.addAttribute("number", csvReader.getRandomNumber());
        return "read";
    }

    @PostMapping("/read")
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

    @GetMapping("/days")
    public String daysForm(Model model) {
        model.addAttribute("guess", csvReader.getRandomDay());
        model.addAttribute("days", csvReader.getDays());
        return "days";
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

    @GetMapping("/months")
    public String monthsForm(Model model) {
        model.addAttribute("guess", csvReader.getRandomMonth());
        model.addAttribute("months", csvReader.getMonths());
        return "months";
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
