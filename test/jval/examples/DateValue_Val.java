package jval.examples;

import jval.BaseValue;
import jval.util.EqualsHelper;
import jval.util.HashHelper;
import jval.util.ToStringHelper;

import java.time.LocalDateTime;

/**
 * @author Sergiy Domin
 */
public class DateValue_Val implements DateValue, BaseValue {
    private LocalDateTime date;
    private double value;

    public DateValue_Val() {
    }

    public DateValue_Val(LocalDateTime date, double value) {
        this.date = date;
        this.value = value;
    }

    @Override
    public LocalDateTime getDate() {
        return date;
    }

    @Override
    public DateValue setDate(LocalDateTime date) {
        this.date = date;
        return this;
    }

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public DateValue setValue(double value) {
        this.value = value;
        return this;
    }

    @Override
    public DateValue immutableCopy() {
        return new DateValue_IVal(date, value);
    }

    @Override
    public DateValue mutableCopy() {
        return new DateValue_Val(date, value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null ||
                !(o.getClass() == DateValue_IVal.class ||
                        o.getClass() == DateValue_Val.class))
            return false;

        DateValue that = (DateValue) o;
        return EqualsHelper.equals(date, that.getDate()) &&
                EqualsHelper.equals(value, that.getValue());
    }

    @Override
    public int hashCode() {
        return new HashHelper().add(date).add(value).hashCode();
    }

    @Override
    public String toString() {
        return new ToStringHelper("DateValue").add("date", date).add("value", value).toString();
    }
}
