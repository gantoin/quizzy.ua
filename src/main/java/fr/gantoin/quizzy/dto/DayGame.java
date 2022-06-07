package fr.gantoin.quizzy.dto;

import fr.gantoin.quizzy.domain.Day;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class DayGame {

    private Day randomDay;
    private List<Day> days;

}
