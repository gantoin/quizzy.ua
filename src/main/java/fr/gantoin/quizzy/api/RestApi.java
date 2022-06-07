package fr.gantoin.quizzy.api;

import fr.gantoin.quizzy.domain.Number;
import fr.gantoin.quizzy.dto.DayGame;
import fr.gantoin.quizzy.dto.MonthGame;
import fr.gantoin.quizzy.dto.NumberGame;
import fr.gantoin.quizzy.service.CsvReader;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "api")
public class RestApi {

    private final CsvReader csvReader;

    @GetMapping(value = "/days", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DayGame> daysListen() {
        DayGame dayGame = new DayGame(csvReader.getRandomDay(), csvReader.getDayList());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(dayGame);
    }


    @GetMapping(value = "/months", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MonthGame> monthsListen() {
        MonthGame monthGame = new MonthGame(csvReader.getRandomMonth(), csvReader.getMonthList());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(monthGame);
    }


    @GetMapping(value = "/numbers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NumberGame> numbersListen() {
        NumberGame numberGame = new NumberGame(csvReader.getRandomNumber(), csvReader.getNumberList());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(numberGame);
    }
}
