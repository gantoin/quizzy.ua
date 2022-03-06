package fr.gantoin.quizzy.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Word {

    private String english;
    private String ukrainian;
    private String audioPath;

}
