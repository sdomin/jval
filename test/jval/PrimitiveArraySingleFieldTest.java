package jval;

import org.junit.Test;

import static jval.SingleFieldTest.assertHashCodeAndEquals;
import static jval.ValueFactory.create;
import static org.junit.Assert.*;

/**
 * @author Sergiy Domin
 */
public class PrimitiveArraySingleFieldTest {
    @Test
    public void booleanArrayFieldMutable() {
        ABool value = create(ABool.class, true);
        assertNull(value.getValue());
        assertEquals("ABool{value=null}", value.toString());

        ABool value2 = value.setValue(new boolean[]{true});
        assertSame(value, value2);
        assertArrayEquals(new boolean[]{true}, value.getValue());
        assertEquals("ABool{value=[true]}", value.toString());
    }

    @Test
    public void booleanArrayFieldImmutable() {
        ABool value = create(ABool.class);
        assertNull(value.getValue());
        assertEquals("ABool{value=null}", value.toString());

        ABool value2 = value.setValue(new boolean[]{true});
        assertNotSame(value, value2);
        assertNull(value.getValue());
        assertArrayEquals(new boolean[]{true}, value2.getValue());
        assertEquals("ABool{value=[true]}", value2.toString());
    }

    @Test
    public void booleanArrayFieldEquals() {
        ABool v = create(ABool.class);
        assertHashCodeAndEquals(v, v.setValue(new boolean[0]), v.setValue(new boolean[]{false}), v.setValue(new boolean[]{true}));
    }

    @Test
    public void byteArrayFieldMutable() {
        AB value = create(AB.class, true);
        assertEquals(null, value.getValue());
        assertEquals("AB{value=null}", value.toString());

        AB value2 = value.setValue(new byte[]{1});
        assertSame(value, value2);
        assertArrayEquals(new byte[]{1}, value.getValue());
        assertEquals("AB{value=[1]}", value.toString());
    }

    @Test
    public void byteArrayFieldImmutable() {
        AB value = create(AB.class);
        assertNull(value.getValue());
        assertEquals("AB{value=null}", value.toString());

        AB value2 = value.setValue(new byte[]{1});
        assertNotSame(value, value2);
        assertNull(value.getValue());
        assertArrayEquals(new byte[]{1}, value2.getValue());
        assertEquals("AB{value=[1]}", value2.toString());
    }

    @Test
    public void byteArrayFieldEquals() {
        AB v = create(AB.class);
        assertHashCodeAndEquals(v, v.setValue(new byte[0]), v.setValue(new byte[]{1}));
    }

    @Test
    public void shortArrayFieldMutable() {
        AS value = create(AS.class, true);
        assertEquals(null, value.getValue());
        assertEquals("AS{value=null}", value.toString());

        AS value2 = value.setValue(new short[]{1});
        assertSame(value, value2);
        assertArrayEquals(new short[]{1}, value.getValue());
        assertEquals("AS{value=[1]}", value.toString());
    }

    @Test
    public void shortArrayFieldImmutable() {
        AS value = create(AS.class);
        assertNull(value.getValue());
        assertEquals("AS{value=null}", value.toString());

        AS value2 = value.setValue(new short[]{1});
        assertNotSame(value, value2);
        assertNull(value.getValue());
        assertArrayEquals(new short[]{1}, value2.getValue());
        assertEquals("AS{value=[1]}", value2.toString());
    }

    @Test
    public void shortArrayFieldEquals() {
        AS v = create(AS.class);
        assertHashCodeAndEquals(v, v.setValue(new short[0]), v.setValue(new short[]{1}));
    }

    @Test
    public void charArrayFieldMutable() {
        AC value = create(AC.class, true);
        assertEquals(null, value.getValue());
        assertEquals("AC{value=null}", value.toString());

        AC value2 = value.setValue(new char[]{'a'});
        assertSame(value, value2);
        assertArrayEquals(new char[]{'a'}, value.getValue());
        assertEquals("AC{value='a'}", value.toString());
    }

    @Test
    public void charArrayFieldImmutable() {
        AC value = create(AC.class);
        assertNull(value.getValue());
        assertEquals("AC{value=null}", value.toString());

        AC value2 = value.setValue(new char[]{'a'});
        assertNotSame(value, value2);
        assertNull(value.getValue());
        assertArrayEquals(new char[]{'a'}, value2.getValue());
        assertEquals("AC{value='a'}", value2.toString());
    }

    @Test
    public void charArrayFieldEquals() {
        AC v = create(AC.class);
        assertHashCodeAndEquals(v, v.setValue(new char[0]), v.setValue(new char[]{'a'}));
    }

    @Test
    public void intArrayFieldMutable() {
        AI value = create(AI.class, true);
        assertEquals(null, value.getValue());
        assertEquals("AI{value=null}", value.toString());

        AI value2 = value.setValue(new int[]{1});
        assertSame(value, value2);
        assertArrayEquals(new int[]{1}, value.getValue());
        assertEquals("AI{value=[1]}", value.toString());
    }

    @Test
    public void intArrayFieldImmutable() {
        AI value = create(AI.class);
        assertNull(value.getValue());
        assertEquals("AI{value=null}", value.toString());

        AI value2 = value.setValue(new int[]{1});
        assertNotSame(value, value2);
        assertNull(value.getValue());
        assertArrayEquals(new int[]{1}, value2.getValue());
        assertEquals("AI{value=[1]}", value2.toString());
    }

    @Test
    public void intArrayFieldEquals() {
        AI v = create(AI.class);
        assertHashCodeAndEquals(v, v.setValue(new int[0]), v.setValue(new int[]{1}));
    }

    @Test
    public void longArrayFieldMutable() {
        AL value = create(AL.class, true);
        assertEquals(null, value.getValue());
        assertEquals("AL{value=null}", value.toString());

        AL value2 = value.setValue(new long[]{1});
        assertSame(value, value2);
        assertArrayEquals(new long[]{1}, value.getValue());
        assertEquals("AL{value=[1]}", value.toString());
    }

    @Test
    public void longArrayFieldImmutable() {
        AL value = create(AL.class);
        assertNull(value.getValue());
        assertEquals("AL{value=null}", value.toString());

        AL value2 = value.setValue(new long[]{1});
        assertNotSame(value, value2);
        assertNull(value.getValue());
        assertArrayEquals(new long[]{1}, value2.getValue());
        assertEquals("AL{value=[1]}", value2.toString());
    }

    @Test
    public void longArrayFieldEquals() {
        AL v = create(AL.class);
        assertHashCodeAndEquals(v, v.setValue(new long[0]), v.setValue(new long[]{1}));
    }

    @Test
    public void floatArrayFieldMutable() {
        AF value = create(AF.class, true);
        assertEquals(null, value.getValue());
        assertEquals("AF{value=null}", value.toString());

        AF value2 = value.setValue(new float[]{1});
        assertSame(value, value2);
        assertArrayEquals(new float[]{1}, value.getValue(), 1e-15f);
        assertEquals("AF{value=[1.0]}", value.toString());
    }

    @Test
    public void floatArrayFieldImmutable() {
        AF value = create(AF.class);
        assertNull(value.getValue());
        assertEquals("AF{value=null}", value.toString());

        AF value2 = value.setValue(new float[]{1});
        assertNotSame(value, value2);
        assertNull(value.getValue());
        assertArrayEquals(new float[]{1}, value2.getValue(), 1e-15f);
        assertEquals("AF{value=[1.0]}", value2.toString());
    }

    @Test
    public void floatArrayFieldEquals() {
        AF v = create(AF.class);
        assertHashCodeAndEquals(v, v.setValue(new float[0]), v.setValue(new float[]{1}), v.setValue(new float[]{Float.NaN}));
    }

    @Test
    public void doubleArrayFieldMutable() {
        AD value = create(AD.class, true);
        assertEquals(null, value.getValue());
        assertEquals("AD{value=null}", value.toString());

        AD value2 = value.setValue(new double[]{1});
        assertSame(value, value2);
        assertArrayEquals(new double[]{1}, value.getValue(), 1e-15);
        assertEquals("AD{value=[1.0]}", value.toString());
    }

    @Test
    public void doubleArrayFieldImmutable() {
        AD value = create(AD.class);
        assertNull(value.getValue());
        assertEquals("AD{value=null}", value.toString());

        AD value2 = value.setValue(new double[]{1});
        assertNotSame(value, value2);
        assertNull(value.getValue());
        assertArrayEquals(new double[]{1}, value2.getValue(), 1e-15);
        assertEquals("AD{value=[1.0]}", value2.toString());
    }

    @Test
    public void doubleArrayFieldEquals() {
        AD v = create(AD.class);
        assertHashCodeAndEquals(v, v.setValue(new double[0]), v.setValue(new double[]{1}), v.setValue(new double[]{Double.NaN}));
    }

    public interface ABool {
        boolean[] getValue();

        ABool setValue(boolean[] value);
    }

    public interface AB {
        byte[] getValue();

        AB setValue(byte[] value);
    }

    public interface AS {
        short[] getValue();

        AS setValue(short[] value);
    }

    public interface AC {
        char[] getValue();

        AC setValue(char[] value);
    }

    public interface AI {
        int[] getValue();

        AI setValue(int[] value);
    }

    public interface AL {
        long[] getValue();

        AL setValue(long[] value);
    }

    public interface AF {
        float[] getValue();

        AF setValue(float[] value);
    }

    public interface AD {
        double[] getValue();

        AD setValue(double[] value);
    }

}
