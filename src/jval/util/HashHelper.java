package jval.util;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author Sergiy Domin
 */
public class HashHelper {
    private int result;

    public HashHelper add(boolean value) {
        return add(Boolean.hashCode(value));
    }

    public HashHelper add(byte value) {
        return add(Byte.hashCode(value));
    }

    public HashHelper add(short value) {
        return add(Short.hashCode(value));
    }

    public HashHelper add(char value) {
        return add(Character.hashCode(value));
    }

    public HashHelper add(int value) {
        result = 31 * result + value;
        return this;
    }

    public HashHelper add(long value) {
        return add(Long.hashCode(value));
    }

    public HashHelper add(float value) {
        return add(Float.hashCode(value));
    }

    public HashHelper add(double value) {
        return add(Double.hashCode(value));
    }

    public HashHelper add(Object value) {
        return add(Objects.hashCode(value));
    }

    public HashHelper add(boolean[] value) {
        return add(Arrays.hashCode(value));
    }

    public HashHelper add(byte[] value) {
        return add(Arrays.hashCode(value));
    }

    public HashHelper add(short[] value) {
        return add(Arrays.hashCode(value));
    }

    public HashHelper add(char[] value) {
        return add(Arrays.hashCode(value));
    }

    public HashHelper add(int[] value) {
        return add(Arrays.hashCode(value));
    }

    public HashHelper add(long[] value) {
        return add(Arrays.hashCode(value));
    }

    public HashHelper add(float[] value) {
        return add(Arrays.hashCode(value));
    }

    public HashHelper add(double[] value) {
        return add(Arrays.hashCode(value));
    }

    public HashHelper add(Object[] value) {
        return add(Arrays.hashCode(value));
    }

    public HashHelper addDeep(Object[] value) {
        return add(Arrays.deepHashCode(value));
    }

    @Override
    public int hashCode() {
        return result;
    }
}
