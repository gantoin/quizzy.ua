package fr.gantoin.quizzy.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Food extends Word {

    public Food(String english, String ukrainian) {
        super(english, ukrainian, null);
    }
}
