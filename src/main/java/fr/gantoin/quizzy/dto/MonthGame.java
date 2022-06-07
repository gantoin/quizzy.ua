package fr.gantoin.quizzy.dto;

import fr.gantoin.quizzy.domain.Day;
import fr.gantoin.quizzy.domain.Month;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class MonthGame {

    private Month randomMonth;
    private List<Month> months;

}
