package jval.util;

import java.util.Arrays;

/**
 * @author Sergiy Domin
 */
public class ToStringHelper {
    private final StringBuilder sb;
    private boolean first = true;

    public ToStringHelper(String name) {
        sb = new StringBuilder(name).append('{');
    }

    public ToStringHelper add(String field, boolean value) {
        appendName(field).append(value);
        return this;
    }

    public ToStringHelper add(String field, byte value) {
        return add(field, (int) value);
    }

    public ToStringHelper add(String field, short value) {
        return add(field, (int) value);
    }

    public ToStringHelper add(String field, int value) {
        appendName(field).append(value);
        return this;
    }

    public ToStringHelper add(String field, char value) {
        appendName(field).append(value);
        return this;
    }

    public ToStringHelper add(String field, long value) {
        appendName(field).append(value);
        return this;
    }

    public ToStringHelper add(String field, float value) {
        appendName(field).append(value);
        return this;
    }

    public ToStringHelper add(String field, double value) {
        appendName(field).append(value);
        return this;
    }

    public ToStringHelper add(String field, CharSequence value) {
        appendName(field).append("'").append(value).append("'");
        return this;
    }

    public ToStringHelper add(String field, String value) {
        appendName(field).append("'").append(value).append("'");
        return this;
    }

    public ToStringHelper add(String field, Object value) {
        appendName(field).append(value);
        return this;
    }

    public ToStringHelper add(String field, boolean[] value) {
        appendName(field).append(Arrays.toString(value));
        return this;
    }

    public ToStringHelper add(String field, byte[] value) {
        appendName(field).append(Arrays.toString(value));
        return this;
    }

    public ToStringHelper add(String field, short[] value) {
        appendName(field).append(Arrays.toString(value));
        return this;
    }

    public ToStringHelper add(String field, int[] value) {
        appendName(field).append(Arrays.toString(value));
        return this;
    }

    public ToStringHelper add(String field, char[] value) {
        appendName(field);
        if (value == null) {
            sb.append("null");
        } else {
            sb.append("'").append(value).append("'");
        }
        return this;
    }

    public ToStringHelper add(String field, long[] value) {
        appendName(field).append(Arrays.toString(value));
        return this;
    }

    public ToStringHelper add(String field, float[] value) {
        appendName(field).append(Arrays.toString(value));
        return this;
    }

    public ToStringHelper add(String field, double[] value) {
        appendName(field).append(Arrays.toString(value));
        return this;
    }

    public ToStringHelper add(String field, Object[] value) {
        appendName(field).append(Arrays.toString(value));
        return this;
    }

    public ToStringHelper addDeep(String field, Object[] value) {
        appendName(field).append(Arrays.deepToString(value));
        return this;
    }

    private StringBuilder appendName(String field) {
        if (first) {
            first = false;
        } else {
            sb.append(", ");
        }
        return sb.append(field).append("=");
    }

    public String build() {
        return sb.append("}").toString();
    }

    @Override
    public String toString() {
        return sb.toString();
    }
}
