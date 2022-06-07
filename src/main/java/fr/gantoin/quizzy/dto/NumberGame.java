package fr.gantoin.quizzy.dto;

import fr.gantoin.quizzy.domain.Number;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class NumberGame {

    private Number randomNumber;
    private List<Number> numbers;

}
