package racingcar.domain;

public class RandomNumberGenerator implements NumberGenerator {
    private final int minimumNumber;
    private final int maximumNumber;

    public RandomNumberGenerator(int minimumNumber, int maximumNumber) {
        this.minimumNumber = minimumNumber;
        this.maximumNumber = maximumNumber;
    }

    @Override
    public int generate() {
        return (int) (Math.random() * maximumNumber - minimumNumber + 1) + minimumNumber;
    }
}