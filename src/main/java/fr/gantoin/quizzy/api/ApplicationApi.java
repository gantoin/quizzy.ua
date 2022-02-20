package fr.gantoin.quizzy.api;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

import fr.gantoin.quizzy.service.CsvReader;

@RequiredArgsConstructor
@Controller
public class ApplicationApi {

    private final CsvReader csvReader;

    @GetMapping(path = {"/", "/index"})
    public String index(Model model) {
        return "index";
    }
}
