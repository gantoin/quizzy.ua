package fr.gantoin.quizzy.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Day extends Word {

    public Day(String english, String ukrainian, String audioPath) {
        super(english, ukrainian, audioPath);
    }
}
