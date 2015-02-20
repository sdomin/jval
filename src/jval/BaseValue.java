package jval;

/**
 * @author Sergiy Domin
 */
public interface BaseValue {
    Object immutableCopy();

    Object mutableCopy();
}
