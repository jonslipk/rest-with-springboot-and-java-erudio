package com.example.erudio.services;

import org.springframework.stereotype.Service;

import com.example.erudio.api.exceptions.UnsuportableMathException;

@Service
public class MathServiceImpl implements MathService {

    @Override
    public Double sum(String numberOne, String numberTwo) {
        exceptionIsNotANumber(numberOne, numberTwo);

        return convertDouble(numberOne) + convertDouble(numberTwo);

    }

    @Override
    public Double substract(String numberOne, String numberTwo) {
        exceptionIsNotANumber(numberOne, numberTwo);

        return convertDouble(numberOne) - convertDouble(numberTwo);
    }

    @Override
    public Double multiplication(String numberOne, String numberTwo) {
        exceptionIsNotANumber(numberOne, numberTwo);

        return convertDouble(numberOne) * convertDouble(numberTwo);
    }

    @Override
    public Double division(String numberOne, String numberTwo) {
        exceptionIsNotANumber(numberOne, numberTwo);

        return convertDouble(numberOne) / convertDouble(numberTwo);
    }

    @Override
    public Double media(String numberOne, String numberTwo) {
        exceptionIsNotANumber(numberOne, numberTwo);

        return (convertDouble(numberOne) + convertDouble(numberTwo)) / 2;
    }

    @Override
    public Double raizQuadrada(String number) {
        exceptionIsNotANumber(number);

        return Math.sqrt(convertDouble(number));
    }

    private Double convertDouble(String number) {

        exceptionIsNotANumber(number);
        return Double.parseDouble(replaceNumber(number));
    }

    private boolean isNumber(String number) {
        return replaceNumber(number) != null &&
                replaceNumber(number).matches("-?\\d+(\\.\\d+)?");
    }

    private String replaceNumber(String number) {
        return number.replace(",", ".");
    }

    private void exceptionIsNotANumber(String number) {
        if (!isNumber(number)) {
            throw new UnsuportableMathException("value isn't a number");
        }
    }

    private void exceptionIsNotANumber(String numberOne, String numberTwo) {
        if (!isNumber(numberOne) || !isNumber(numberTwo)) {
            throw new UnsuportableMathException("value isn't a number");
        }
    }

}
