package jval.examples;

import jval.ValueFactory;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Sergiy Domin
 */
public class DateValueTest {
    @Test
    public void runExample() {
        DateValue v = ValueFactory.create(DateValue.class); // immutable instance
        DateValue v1 = v.setValue(1.0); // new copy
        assertNotSame(v, v1);
        assertEquals(0, v.getValue(), 1e-15);
        assertEquals(1, v1.getValue(), 1e-15);

        DateValue m = ValueFactory.create(DateValue.class, true); // mutable instance
        assertNotSame(v, m);
        assertEquals(v, m);
        m = v.mutableCopy(); // new mutable instance - another way to create a new value
        m = m.mutableCopy(); // new copy - yet another way to create a new value

        DateValue m1 = m.setValue(1.0);
        assertSame(m, m1);
        assertNotEquals(m, v);
        assertEquals(m, v1);
    }
}
