package fr.gantoin.quizzy.api;

import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

import fr.gantoin.quizzy.domain.Food;
import fr.gantoin.quizzy.service.CsvReader;

@RequiredArgsConstructor
@Controller
public class FoodApi {

    private final CsvReader csvReader;

    @GetMapping("/food")
    public String foodForm(Model model) {
        Food guessFood = csvReader.getRandomFood();
        Set<Food> randomFood = csvReader.getFiveRandomFood();
        randomFood.add(guessFood);
        model.addAttribute("guess", guessFood);
        model.addAttribute("food", randomFood);
        return "food/read";
    }

    @PostMapping("/food")
    public String postFoodAnswer(Model model, @RequestParam("answer") String answer, @RequestParam("solution") String solution) {
        if (answer.equals(solution)) {
            model.addAttribute("result", "Correct!");
        } else {
            model.addAttribute("result", "Wrong!");
        }
        return "result";
    }
}
