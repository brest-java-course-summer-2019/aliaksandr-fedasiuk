package com.epam.brest2019.courses;

import com.epam.brest2019.courses.files.CSVFileReader;
import com.epam.brest2019.courses.files.FileReader;
import com.epam.brest2019.courses.menu.CorrectValue;
import com.epam.brest2019.courses.menu.EnteredValue;
import com.epam.brest2019.courses.menu.ExitValue;
import com.epam.brest2019.courses.menu.IncorrectValue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private static final String QUIT_SYMBOL = "q";

    public static void main(String[] args) throws IOException {

        Main main = new Main();
        Scanner scanner = new Scanner(System.in);

        FileReader fileReader = new CSVFileReader();
        Map<Integer, BigDecimal> distancePrices = fileReader.readData("price_per_km.csv");
        if (distancePrices == null || distancePrices.isEmpty()) {
            throw new FileNotFoundException("File with prices per kg not found.");
        }

        EnteredValue weightValue =
                main.receiveValueFromConsole("Enter distance in km or 'q' for quit", scanner);
        if (weightValue.getType() != EnteredValue.Types.EXIT) {
            CorrectValue correctValue = (CorrectValue) weightValue;
            System.out.println("Value: " + correctValue.getValue());
        }

        System.out.println("Bye!");
    }

    private EnteredValue receiveValueFromConsole(String message, Scanner scanner) {
        EnteredValue enteredValue = new IncorrectValue();
        while (enteredValue.getType() == EnteredValue.Types.INCORRECT) {
            System.out.println(message);
            enteredValue = parseInputValue(scanner.nextLine());
        }
        return enteredValue;
    }

    private EnteredValue parseInputValue(String inputValue) {
        EnteredValue result = new ExitValue();
        if (!inputValue.trim().toLowerCase().equals(QUIT_SYMBOL)) {
            try {
                BigDecimal value = new BigDecimal(inputValue);
                if (value.compareTo(BigDecimal.ZERO) > 0) {
                    result = new CorrectValue(new BigDecimal(inputValue));
                } else {
                    throw new IllegalArgumentException();
                }
            } catch (IllegalArgumentException e) {
                System.out.format("Incorrect value: %s%n", inputValue);
                result = new IncorrectValue();
            }
        }
        return result;
    }

}
