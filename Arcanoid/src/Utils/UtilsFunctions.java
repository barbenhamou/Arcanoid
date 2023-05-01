package Utils;

/**
 * Utils functions class.
 * */
public class UtilsFunctions {

    public static double THERSHOLD = 0.00000001;
    /**
     * Does a comparison between doubles. <br>
     * @param a
     * @param b
     * @return wither a<=b approximately.
     * */
    public static boolean approxiEquals(double a, double b) {
        return Math.abs(a - b) <= THERSHOLD;
    }
}