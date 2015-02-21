package jval.examples;

import jval.BaseValue;

import java.time.LocalDateTime;

/**
 * @author Sergiy Domin
 */
public interface DateValue extends BaseValue {

    LocalDateTime getDate();

    DateValue setDate(LocalDateTime date);

    double getValue();

    DateValue setValue(double value);

    @Override
    DateValue immutableCopy();

    @Override
    DateValue mutableCopy();
}
