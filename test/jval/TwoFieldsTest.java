package jval;

import org.junit.Test;

import static jval.SingleFieldTest.assertHashCodeAndEquals;
import static jval.ValueFactory.create;
import static org.junit.Assert.*;

/**
 * @author Sergiy Domin
 */
public class TwoFieldsTest {

    @Test
    public void objectIntMutable() {
        OI v = create(OI.class, true);
        assertNull(v.getValue1());
        assertEquals(0, v.getValue2());
        assertEquals("OI{value1=null, value2=0}", v.toString());

        Object o = "a";
        assertSame(v, v.setValue1(o));
        assertSame(v, v.setValue2(1));

        assertEquals(o, v.getValue1());
        assertEquals(1, v.getValue2());
        assertEquals("OI{value1=a, value2=1}", v.toString());
    }

    @Test
    public void objectIntImmutable() {
        OI v = create(OI.class);
        assertNull(v.getValue1());
        assertEquals(0, v.getValue2());
        assertEquals("OI{value1=null, value2=0}", v.toString());

        Object o = "a";
        OI v2 = v.setValue1(o).setValue2(1);
        assertNotSame(v, v2);

        assertNull(v.getValue1());
        assertEquals(0, v.getValue2());
        assertEquals(o, v2.getValue1());
        assertEquals(1, v2.getValue2());
        assertEquals("OI{value1=a, value2=1}", v2.toString());
    }

    @Test
    public void objectIntEquals() {
        OI v = create(OI.class);
        assertHashCodeAndEquals(v, v.setValue1("a"), v.setValue2(1), v.setValue1("a").setValue2(1));
    }

    @Test
    public void intLongMutable() {
        IL v = create(IL.class, true);
        assertEquals(0, v.getValue1());
        assertEquals(0, v.getValue2());
        assertEquals("IL{value1=0, value2=0}", v.toString());

        assertSame(v, v.setValue1(1));
        assertSame(v, v.setValue2(2));

        assertEquals(1, v.getValue1());
        assertEquals(2, v.getValue2());
        assertEquals("IL{value1=1, value2=2}", v.toString());
    }

    @Test
    public void intLongImmutable() {
        IL v = create(IL.class);
        assertEquals(0, v.getValue1());
        assertEquals(0, v.getValue2());
        assertEquals("IL{value1=0, value2=0}", v.toString());

        IL v2 = v.setValue1(1).setValue2(2);
        assertNotSame(v, v2);

        assertEquals(0, v.getValue1());
        assertEquals(0, v.getValue2());
        assertEquals(1, v2.getValue1());
        assertEquals(2, v2.getValue2());
        assertEquals("IL{value1=1, value2=2}", v2.toString());
    }

    @Test
    public void intLongEquals() {
        IL v = create(IL.class);
        assertHashCodeAndEquals(v, v.setValue1(1), v.setValue2(2), v.setValue1(1).setValue2(2));
    }

    @Test
    public void longDoubleMutable() {
        LD v = create(LD.class, true);
        assertEquals(0, v.getValue1());
        assertEquals(0, v.getValue2(), 1e-15);
        assertEquals("LD{value1=0, value2=0.0}", v.toString());

        assertSame(v, v.setValue1(1));
        assertSame(v, v.setValue2(2.1));

        assertEquals(1, v.getValue1());
        assertEquals(2.1, v.getValue2(), 1e-15);
        assertEquals("LD{value1=1, value2=2.1}", v.toString());
    }

    @Test
    public void longDoubleImmutable() {
        LD v = create(LD.class);
        assertEquals(0, v.getValue1());
        assertEquals(0, v.getValue2(), 1e-15);
        assertEquals("LD{value1=0, value2=0.0}", v.toString());

        LD v2 = v.setValue1(1).setValue2(2.1);
        assertNotSame(v, v2);

        assertEquals(0, v.getValue1());
        assertEquals(0, v.getValue2(), 1e-15);
        assertEquals(1, v2.getValue1());
        assertEquals(2.1, v2.getValue2(), 1e-15);
        assertEquals("LD{value1=1, value2=2.1}", v2.toString());
    }

    @Test
    public void longDoubleEquals() {
        LD v = create(LD.class);
        assertHashCodeAndEquals(v, v.setValue1(1), v.setValue2(2.1), v.setValue1(1).setValue2(2.1));
    }

    @Test
    public void doubleIntMutable() {
        DI v = create(DI.class, true);
        assertEquals(0, v.getValue1(), 1e-15);
        assertEquals(0, v.getValue2());
        assertEquals("DI{value1=0.0, value2=0}", v.toString());

        assertSame(v, v.setValue1(1.1));
        assertSame(v, v.setValue2(2));

        assertEquals(1.1, v.getValue1(), 1e-15);
        assertEquals(2, v.getValue2());
        assertEquals("DI{value1=1.1, value2=2}", v.toString());
    }

    @Test
    public void doubleIntImmutable() {
        DI v = create(DI.class);
        assertEquals(0, v.getValue1(), 1e-15);
        assertEquals(0, v.getValue2());
        assertEquals("DI{value1=0.0, value2=0}", v.toString());

        DI v2 = v.setValue1(1.1).setValue2(2);
        assertNotSame(v, v2);

        assertEquals(0, v.getValue1(), 1e-15);
        assertEquals(0, v.getValue2());
        assertEquals(1.1, v2.getValue1(), 1e-15);
        assertEquals(2, v2.getValue2());
        assertEquals("DI{value1=1.1, value2=2}", v2.toString());
    }

    @Test
    public void doubleIntEquals() {
        DI v = create(DI.class);
        assertHashCodeAndEquals(v, v.setValue1(1.1), v.setValue2(2), v.setValue1(1.1).setValue2(2));
    }

    public interface OI {
        Object getValue1();

        OI setValue1(Object value);

        int getValue2();

        OI setValue2(int value);
    }

    public interface IL {
        int getValue1();

        IL setValue1(int value);

        long getValue2();

        IL setValue2(long value);
    }

    public interface LD {
        long getValue1();

        LD setValue1(long value);

        double getValue2();

        LD setValue2(double value);
    }

    public interface DI {
        double getValue1();

        DI setValue1(double value);

        int getValue2();

        DI setValue2(int value);
    }

}
