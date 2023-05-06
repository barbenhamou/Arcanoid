package Utils;

/**
 * Name: Bar Ben Hamou.<br>
 * id number: 330591207.<br>
 * Utils functions class.
 * */
public class UtilsFunctions {

    private static final double THRESHOLD = 0.00000001;
    /**
     * Does a comparison between doubles. <br>
     * @param a
     * @param b
     * @return wither a<=b approximately.
     * */
    public static boolean approxiEquals(double a, double b) {
        return Math.abs(a - b) <= THRESHOLD;
    }
}