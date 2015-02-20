package jval;

import org.junit.Test;

import static jval.SingleFieldTest.assertHashCodeAndEquals;
import static jval.ValueFactory.create;
import static org.junit.Assert.*;

/**
 * @author Sergiy Domin
 */
public class PrimitiveSingleFieldTest {
    @Test
    public void booleanFieldMutable() {
        Bool value = create(Bool.class, true);
        assertFalse(value.getValue());
        assertEquals("Bool{value=false}", value.toString());

        Bool value2 = value.setValue(true);
        assertSame(value, value2);
        assertTrue(value.getValue());
        assertEquals("Bool{value=true}", value.toString());
    }

    @Test
    public void booleanFieldImmutable() {
        Bool value = create(Bool.class);
        assertFalse(value.getValue());
        assertEquals("Bool{value=false}", value.toString());

        Bool value2 = value.setValue(true);
        assertNotSame(value, value2);
        assertFalse(value.getValue());
        assertTrue(value2.getValue());
        assertEquals("Bool{value=true}", value2.toString());
    }

    @Test
    public void booleanEquals() {
        Bool b = create(Bool.class);
        assertHashCodeAndEquals(b, b.setValue(true));
    }

    @Test
    public void byteFieldMutable() {
        B value = create(B.class, true);
        assertEquals(0, value.getValue());
        assertEquals("B{value=0}", value.toString());

        B value2 = value.setValue((byte) 1);
        assertSame(value, value2);
        assertEquals(1, value.getValue());
        assertEquals("B{value=1}", value.toString());
    }

    @Test
    public void byteFieldImmutable() {
        B value = create(B.class);
        assertEquals(0, value.getValue());
        assertEquals("B{value=0}", value.toString());

        B value2 = value.setValue((byte) 1);
        assertNotSame(value, value2);
        assertEquals(0, value.getValue());
        assertEquals(1, value2.getValue());
        assertEquals("B{value=1}", value2.toString());
    }

    @Test
    public void byteFieldEquals() {
        B b = create(B.class);
        assertHashCodeAndEquals(b, b.setValue((byte) 1), b.setValue((byte) 2));
    }

    @Test
    public void shortFieldMutable() {
        S value = create(S.class, true);
        assertEquals(0, value.getValue());
        assertEquals("S{value=0}", value.toString());

        S value2 = value.setValue((short) 1);
        assertSame(value, value2);
        assertEquals(1, value.getValue());
        assertEquals("S{value=1}", value.toString());
    }

    @Test
    public void shortFieldImmutable() {
        S value = create(S.class);
        assertEquals(0, value.getValue());
        assertEquals("S{value=0}", value.toString());

        S value2 = value.setValue((short) 1);
        assertNotSame(value, value2);
        assertEquals(0, value.getValue());
        assertEquals(1, value2.getValue());
        assertEquals("S{value=1}", value2.toString());
    }

    @Test
    public void shortFieldEquals() {
        S s = create(S.class);
        assertHashCodeAndEquals(s, s.setValue((short) 1), s.setValue((short) 2));
    }

    @Test
    public void charFieldMutable() {
        C value = create(C.class, true);
        assertEquals('\0', value.getValue());
        assertEquals("C{value=\0}", value.toString());

        C value2 = value.setValue('a');
        assertSame(value, value2);
        assertEquals('a', value.getValue());
        assertEquals("C{value=a}", value.toString());
    }

    @Test
    public void charFieldImmutable() {
        C value = create(C.class);
        assertEquals('\0', value.getValue());
        assertEquals("C{value=\0}", value.toString());

        C value2 = value.setValue('a');
        assertNotSame(value, value2);
        assertEquals('\0', value.getValue());
        assertEquals('a', value2.getValue());
        assertEquals("C{value=a}", value2.toString());
    }

    @Test
    public void charFieldEquals() {
        C c = create(C.class);
        assertHashCodeAndEquals(c, c.setValue('a'), c.setValue('b'));
    }

    @Test
    public void intFieldMutable() {
        I value = create(I.class, true);
        assertEquals(0, value.getValue());
        assertEquals("I{value=0}", value.toString());

        I value2 = value.setValue(1);
        assertSame(value, value2);
        assertEquals(1, value.getValue());
        assertEquals("I{value=1}", value.toString());
    }

    @Test
    public void intFieldImmutable() {
        I value = create(I.class);
        assertEquals(0, value.getValue());
        assertEquals("I{value=0}", value.toString());

        I value2 = value.setValue(1);
        assertNotSame(value, value2);
        assertEquals(0, value.getValue());
        assertEquals(1, value2.getValue());
        assertEquals("I{value=1}", value2.toString());
    }

    @Test
    public void intFieldEquals() {
        I i = create(I.class);
        assertHashCodeAndEquals(i, i.setValue(1), i.setValue(2));
    }

    @Test
    public void longFieldMutable() {
        L value = create(L.class, true);
        assertEquals(0, value.getValue());
        assertEquals("L{value=0}", value.toString());

        L value2 = value.setValue(12345678900L);
        assertSame(value, value2);
        assertEquals(12345678900L, value.getValue());
        assertEquals("L{value=12345678900}", value.toString());
    }

    @Test
    public void longFieldImmutable() {
        L value = create(L.class);
        assertEquals(0, value.getValue());
        assertEquals("L{value=0}", value.toString());

        L value2 = value.setValue(1);
        assertNotSame(value, value2);
        assertEquals(0, value.getValue());
        assertEquals(1, value2.getValue());
        assertEquals("L{value=1}", value2.toString());
    }

    @Test
    public void longFieldEquals() {
        L l = create(L.class);
        assertHashCodeAndEquals(l, l.setValue(1), l.setValue(2), l.setValue(12345678900L));
    }

    @Test
    public void floatFieldMutable() {
        F value = create(F.class, true);
        assertEquals(0, value.getValue(), 1e-15);
        assertEquals("F{value=0.0}", value.toString());

        F value2 = value.setValue(1f);
        assertSame(value, value2);
        assertEquals(1f, value.getValue(), 1e-15);
        assertEquals("F{value=1.0}", value.toString());
    }

    @Test
    public void floatFieldImmutable() {
        F value = create(F.class);
        assertEquals(0, value.getValue(), 1e-15);
        assertEquals("F{value=0.0}", value.toString());

        F value2 = value.setValue(1f);
        assertNotSame(value, value2);
        assertEquals(0, value.getValue(), 1e-15);
        assertEquals(1f, value2.getValue(), 1e-15);
        assertEquals("F{value=1.0}", value2.toString());
    }

    @Test
    public void floatFieldEquals() {
        F f = create(F.class);
        assertHashCodeAndEquals(f, f.setValue(1), f.setValue(Float.NaN), f.setValue(Float.NEGATIVE_INFINITY), f.setValue(Float.POSITIVE_INFINITY), f.setValue(-0.0f));
        assertNotEquals(f.setValue(-0.0f), f.setValue(+0.0f));
        assertEquals(f.setValue(0.0f), f.setValue(+0.0f));
        assertTrue(Float.valueOf(0.0f).equals(Float.valueOf(+0.0f)));
    }

    @Test
    public void doubleFieldMutable() {
        D value = create(D.class, true);
        assertEquals(0, value.getValue(), 1e-15);
        assertEquals("D{value=0.0}", value.toString());

        D value2 = value.setValue(1.0);
        assertSame(value, value2);
        assertEquals(1.0, value.getValue(), 1e-15);
        assertEquals("D{value=1.0}", value.toString());
    }

    @Test
    public void doubleFieldImmutable() {
        D value = create(D.class);
        assertEquals(0, value.getValue(), 1e-15);
        assertEquals("D{value=0.0}", value.toString());

        D value2 = value.setValue(1.0);
        assertNotSame(value, value2);
        assertEquals(0, value.getValue(), 1e-15);
        assertEquals(1.0, value2.getValue(), 1e-15);
        assertEquals("D{value=1.0}", value2.toString());
    }

    @Test
    public void doubleFieldEquals() {
        D d = create(D.class);
        assertHashCodeAndEquals(d, d.setValue(1), d.setValue(Double.NaN), d.setValue(Double.NEGATIVE_INFINITY), d.setValue(Double.POSITIVE_INFINITY), d.setValue(-0.0));
        assertNotEquals(d.setValue(-0.0), d.setValue(+0.0));
        assertEquals(d.setValue(0.0), d.setValue(+0.0));
        assertTrue(Double.valueOf(0.0).equals(Double.valueOf(+0.0)));
    }

    public interface Bool {
        boolean getValue();

        Bool setValue(boolean value);
    }

    public interface C {
        char getValue();

        C setValue(char value);
    }

    public interface B {
        byte getValue();

        B setValue(byte value);
    }

    public interface S {
        short getValue();

        S setValue(short value);
    }

    public interface I {
        int getValue();

        I setValue(int value);
    }

    public interface L {
        long getValue();

        L setValue(long value);
    }

    public interface F {
        float getValue();

        F setValue(float value);
    }

    public interface D {
        double getValue();

        D setValue(double value);
    }
}
