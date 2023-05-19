package ru.neoflex.practics4.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.neoflex.practics4.model.Result;
import ru.neoflex.practics4.repository.ResultRepository;

import java.util.List;

@RestController
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CalcController {
    ResultRepository resultRepository;

    @Autowired
    public CalcController(ResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }

    @GetMapping("/plus/{a}/{b}")
    @ApiResponse(responseCode = "200", description = "Get sum of two numbers")
    @ApiResponse(responseCode = "400", description = "Incorrect numbers!")
    public ResponseEntity<String> plus(@PathVariable(name = "a") String a, @PathVariable(name = "b") String b) {
        int sum;

        try {
            sum = Integer.parseInt(a) + Integer.parseInt(b);
            resultRepository.save(new Result(a, b, "plus", sum));
            return new ResponseEntity<>(String.valueOf(sum), HttpStatus.OK);
        }catch (NumberFormatException exception) {
            return new ResponseEntity<>("Incorrect numbers!", HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/minus/{a}/{b}")
    @ApiResponse(responseCode = "200", description = "Get difference of two numbers")
    @ApiResponse(responseCode = "400", description = "Incorrect numbers!")
    public ResponseEntity<String> minus(@PathVariable(name = "a") String a, @PathVariable(name = "b") String b) {
        int dif;

        try {
            dif = Integer.parseInt(a) - Integer.parseInt(b);
            resultRepository.save(new Result(a, b, "minus", dif));
            return new ResponseEntity<>(String.valueOf(dif), HttpStatus.OK);
        }catch (NumberFormatException exception) {
            return new ResponseEntity<>("Incorrect numbers!", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getAllResults")
    @ApiResponse(responseCode = "200", description = "Get all results")
    public ResponseEntity getAllResults() {
        List<Result> allResults = resultRepository.findAll();
        return allResults.isEmpty() ? new ResponseEntity<>("There are no results yet", HttpStatus.OK) :
                new ResponseEntity<>(allResults, HttpStatus.OK);
    }
}