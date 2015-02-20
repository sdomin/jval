package jval.examples;

import java.time.LocalDateTime;

/**
 * @author Sergiy Domin
 */
public interface DateValue {

    LocalDateTime getDate();

    DateValue setDate(LocalDateTime date);

    double getValue();

    DateValue setValue(double value);
}
