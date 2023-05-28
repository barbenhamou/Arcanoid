package Utils;

/**
 * Name: Bar Ben Hamou.<br>
 * id number: 330591207.<br>
 * A Counter.
 */
public class Counter {
    private int value;

    /**
     * Constructor.<br>
     *
     * @param value the initial value that the counter counts.
     * */
    public Counter(int value) {
        this.value = value;
    }

    /**
     * Increases value by the number given.<br>
     *
     * @param number the wanted change.
     * */
    public void increase(int number) {
        this.value += number;
    }

    /**
     * Decreases value by the number given.<br>
     *
     * @param number the wanted change.
     * */
    public void decrease(int number) {
        this.value -= number;
    }

    /**
     * The current value.<br>
     *
     * @return the number.
     * */
    public int getValue() {
        return this.value;
    }
}