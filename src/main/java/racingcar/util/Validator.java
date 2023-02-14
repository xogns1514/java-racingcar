package racingcar.util;

import racingcar.constant.ErrorMessage;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Validator {

    public static final String NATURAL_NUMBER_REGEX = "^[1-9]+[0-9]*$";
    private static final int MAX_NAME_LENGTH = 5;

    public static void validateCarName(String carName) {
        validateNotEmptyInput(carName);
        validateNameLength(carName);
    }

    public static void validateNotEmptyInput(String str) {
        if (str.trim().isBlank()) {
            throw new IllegalArgumentException(ErrorMessage.EMPTY_STRING.getMessage());
        }
    }

    private static void validateNameLength(String name) {
        if (name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_NAME_LENGTH.getMessage());
        }
    }

    public static void validateNaturalNumber(String str) {
        if (!str.matches(NATURAL_NUMBER_REGEX)) {
            throw new IllegalArgumentException(ErrorMessage.NOT_NATURAL_NUMBER.getMessage());
        }
    }

    public static void validateDuplication(List<String> inputs) {
        Set<String> inputSet = new HashSet<>(inputs);
        if (inputs.size() != inputSet.size()) {
            throw new IllegalArgumentException(ErrorMessage.DUPLICATED_INPUT.getMessage());
        }
    }

}