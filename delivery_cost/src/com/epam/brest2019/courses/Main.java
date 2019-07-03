package com.epam.brest2019.courses;

import com.epam.brest2019.courses.calc.Calculator;
import com.epam.brest2019.courses.calc.CalculatorImpl;
import com.epam.brest2019.courses.files.CSVFileReader;
import com.epam.brest2019.courses.files.FileReader;
import com.epam.brest2019.courses.menu.CorrectValue;
import com.epam.brest2019.courses.menu.EnteredValue;
import com.epam.brest2019.courses.menu.ExitValue;
import com.epam.brest2019.courses.menu.IncorrectValue;
import com.epam.brest2019.courses.selector.SelectorFromMap;
import com.epam.brest2019.courses.selector.ValueSelector;

import javax.xml.bind.ValidationEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private static final String QUIT_SYMBOL = "q";

    private static final String FILE_KG_PRICES = "price_per_kg.csv";
    private static final String FILE_KM_PRICES = "price_per_km.csv";

    public static void main(String[] args) throws IOException {

        Main main = new Main();
        Scanner scanner = new Scanner(System.in);

        FileReader fileReader = new CSVFileReader();
        Map<Integer, BigDecimal> weightPrices = fileReader.readData(FILE_KG_PRICES);
        if (weightPrices == null || weightPrices.isEmpty()) {
            throw new FileNotFoundException("File with prices per kg not found.");
        }

        Map<Integer, BigDecimal> distancePrices = fileReader.readData(FILE_KM_PRICES);
        if (distancePrices == null || distancePrices.isEmpty()) {
            throw new FileNotFoundException("File with prices per km not found.");
        }

        Calculator calculator = new CalculatorImpl();
        ValueSelector selector = new SelectorFromMap();

        BigDecimal weight;
        BigDecimal distance;
        EnteredValue enteredValue;
        do {
            System.out.println("==========================================================");
            enteredValue = main.receiveValueFromConsole("Enter weight in kg or 'q' for quit", scanner);
            if (enteredValue.getType() != EnteredValue.Types.EXIT) {
                weight = ((CorrectValue) enteredValue).getValue();
                enteredValue = main.receiveValueFromConsole("Enter distance in km or 'q' for quit", scanner);
                if (enteredValue.getType() != EnteredValue.Types.EXIT) {
                    distance = ((CorrectValue) enteredValue).getValue();

                    BigDecimal deliveryCost = calculator.calc(weight, distance,
                            selector.selectValue(weightPrices, weight),
                            selector.selectValue(distancePrices, distance));

                    System.out.println("Delivery cost: " + deliveryCost);
                }
            }
        } while (enteredValue.getType() != EnteredValue.Types.EXIT);
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
