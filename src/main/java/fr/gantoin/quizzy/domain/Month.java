package fr.gantoin.quizzy.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Month extends Word {

    public Month(String english, String ukrainian, String audioPath) {
        super(english, ukrainian, audioPath);
    }
}
