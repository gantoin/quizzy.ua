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
    public String postAnswer(Model model, @RequestParam("answer") int answer, @RequestParam("number") int number) {
        if(answer == number) {
            model.addAttribute("result", "Correct!");
        } else {
            model.addAttribute("result", "Wrong!");
        }
        return "result";
    }
}