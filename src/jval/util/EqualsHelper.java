package jval.util;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author Sergiy Domin
 */
public class EqualsHelper {
    public static boolean equals(double d1, double d2) {
        return Double.doubleToLongBits(d1) == Double.doubleToLongBits(d2);
    }

    public static boolean equals(float f1, float f2) {
        return Float.floatToIntBits(f1) == Float.floatToIntBits(f2);
    }

    public static boolean equals(Object o1, Object o2) {
        return Objects.equals(o1, o2);
    }

    public static boolean equals(boolean[] o1, boolean[] o2) {
        return Arrays.equals(o1, o2);
    }

    public static boolean equals(byte[] o1, byte[] o2) {
        return Arrays.equals(o1, o2);
    }

    public static boolean equals(short[] o1, short[] o2) {
        return Arrays.equals(o1, o2);
    }

    public static boolean equals(char[] o1, char[] o2) {
        return Arrays.equals(o1, o2);
    }

    public static boolean equals(int[] o1, int[] o2) {
        return Arrays.equals(o1, o2);
    }

    public static boolean equals(long[] o1, long[] o2) {
        return Arrays.equals(o1, o2);
    }

    public static boolean equals(float[] o1, float[] o2) {
        return Arrays.equals(o1, o2);
    }

    public static boolean equals(double[] o1, double[] o2) {
        return Arrays.equals(o1, o2);
    }

    public static boolean equals(Object[] o1, Object[] o2) {
        return Arrays.equals(o1, o2);
    }

    public static boolean deepEquals(Object[] o1, Object[] o2) {
        return Arrays.deepEquals(o1, o2);
    }
}
