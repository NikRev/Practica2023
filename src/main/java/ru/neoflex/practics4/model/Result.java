package ru.neoflex.practics4.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity(name = "results")
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String firstNumber;
    String secondNumber;
    int result;
    String action;

    public Result(String firstNumber, String secondNumber, String action, int result) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
        this.action = action;
        this.result = result;
    }
}