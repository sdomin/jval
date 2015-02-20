package jval;

import org.junit.Test;

import static jval.ValueFactory.create;
import static org.junit.Assert.*;

public class SingleFieldTest {
    static void assertHashCodeAndEquals(Object... values) {
        BaseValue[] bvs = new BaseValue[values.length];
        for (int i = 0; i < values.length; i++) {
            bvs[i] = (BaseValue) ((BaseValue) values[i]).immutableCopy();
        }

        for (int i = 0; i < bvs.length; i++) {
            BaseValue bv = bvs[i];
            assertEquals(bv, bv);
            assertEquals(bv.hashCode(), bv.hashCode());
            {
                Object bv2 = bv.mutableCopy();
                assertEquals(bv, bv2);
                assertEquals(bv2, bv);
                assertEquals("hashCode", bv.hashCode(), bv2.hashCode());
                assertNotSame(bv, bv2);
            }
            for (int j = 0; j < bvs.length; j++) {
                if (i == j) {
                    continue;
                }
                BaseValue bv2 = i < j ? bvs[j] : (BaseValue) bvs[j].mutableCopy();
                assertNotSame(bv, bv2);
                assertNotEquals(bv, bv2);
                assertNotEquals(bv2, bv);
                assertNotEquals("hashCode", bv.hashCode(), bv2);
            }
        }
    }

    @Test
    public void stringFieldMutable() {
        Str value = create(Str.class, true);
        assertNull(value.getValue());
        assertEquals("Str{value='null'}", value.toString());

        String v = "a";
        Str value2 = value.setValue(v);
        assertSame(value, value2);
        assertEquals(v, value.getValue());
        assertEquals("Str{value='a'}", value.toString());
    }

    @Test
    public void stringFieldImmutable() {
        Str value = create(Str.class);
        assertNull(value.getValue());
        assertEquals("Str{value='null'}", value.toString());

        String v = "a";
        Str value2 = value.setValue(v);
        assertNotSame(value, value2);
        assertNull(value.getValue());
        assertEquals(v, value2.getValue());
        assertEquals("Str{value='a'}", value2.toString());
    }

    @Test
    public void stringFieldEquals() {
        Str s = create(Str.class);
        assertHashCodeAndEquals(s, s.setValue("a"), s.setValue("aa"), s.setValue("b"));
    }

    @Test
    public void objectFieldMutable() {
        O value = create(O.class, true);
        assertNull(value.getValue());
        assertEquals("O{value=null}", value.toString());

        Object v = "a";
        O value2 = value.setValue(v);
        assertSame(value, value2);
        assertEquals(v, value.getValue());
        assertEquals("O{value=a}", value.toString());
    }

    @Test
    public void objectFieldImmutable() {
        O value = create(O.class);
        assertNull(value.getValue());
        assertEquals("O{value=null}", value.toString());

        Object v = new Object() {
            @Override
            public String toString() {
                return "o";
            }
        };
        O value2 = value.setValue(v);
        assertNotSame(value, value2);
        assertNull(value.getValue());
        assertEquals(v, value2.getValue());
        assertEquals("O{value=o}", value2.toString());
    }

    @Test
    public void objectFieldEquals() {
        O o = create(O.class);
        assertHashCodeAndEquals(o, o.setValue(new Object()), o.setValue(new Object()));
    }

    @Test
    public void stringArrayFieldMutable() {
        AStr value = create(AStr.class, true);
        assertNull(value.getValue());
        assertEquals("AStr{value=null}", value.toString());

        String[] v = {"a"};
        AStr value2 = value.setValue(v);
        assertSame(value, value2);
        assertArrayEquals(v, value.getValue());
        assertEquals("AStr{value=[a]}", value.toString());
    }

    @Test
    public void stringArrayFieldImmutable() {
        AStr value = create(AStr.class);
        assertNull(value.getValue());
        assertEquals("AStr{value=null}", value.toString());

        String[] v = {"a"};
        AStr value2 = value.setValue(v);
        assertNotSame(value, value2);
        assertNull(value.getValue());
        assertArrayEquals(v, value2.getValue());
        assertEquals("AStr{value=[a]}", value2.toString());
    }

    @Test
    public void stringArrayFieldEquals() {
        AStr s = create(AStr.class);
        assertHashCodeAndEquals(s, s.setValue(new String[0]), s.setValue(new String[]{"a"}));
    }

    @Test
    public void objectArrayFieldMutable() {
        AO value = create(AO.class, true);
        assertNull(value.getValue());
        assertEquals("AO{value=null}", value.toString());

        Object[] v = {"a"};
        AO value2 = value.setValue(v);
        assertSame(value, value2);
        assertArrayEquals(v, value.getValue());
        assertEquals("AO{value=[a]}", value.toString());
    }

    @Test
    public void objectArrayFieldImmutable() {
        AO value = create(AO.class);
        assertNull(value.getValue());
        assertEquals("AO{value=null}", value.toString());

        Object[] v = {"a"};
        AO value2 = value.setValue(v);
        assertNotSame(value, value2);
        assertNull(value.getValue());
        assertArrayEquals(v, value2.getValue());
        assertEquals("AO{value=[a]}", value2.toString());
    }

    @Test
    public void objectArrayFieldEquals() {
        AO s = create(AO.class);
        assertHashCodeAndEquals(s, s.setValue(new Object[0]), s.setValue(new Object[]{"a"}));
    }

    @Test
    public void primitiveMatrixFieldMutable() {
        AI2 value = create(AI2.class, true);
        assertNull(value.getValue());
        assertEquals("AI2{value=null}", value.toString());

        AI2 value2 = value.setValue(new int[][]{{1}, {2}});
        assertSame(value, value2);
        assertArrayEquals(new int[][]{{1}, {2}}, value.getValue());
        assertEquals("AI2{value=[[1], [2]]}", value.toString());
    }

    @Test
    public void primitiveMatrixFieldImmutable() {
        AI2 value = create(AI2.class);
        assertNull(value.getValue());
        assertEquals("AI2{value=null}", value.toString());

        AI2 value2 = value.setValue(new int[][]{{1}, {2}});
        assertNotSame(value, value2);
        assertNull(value.getValue());
        assertArrayEquals(new int[][]{{1}, {2}}, value2.getValue());
        assertEquals("AI2{value=[[1], [2]]}", value2.toString());
    }

    @Test
    public void primitiveMatrixFieldEquals() {
        AI2 m = create(AI2.class);
        assertHashCodeAndEquals(m, m.setValue(new int[0][0]), m.setValue(new int[][]{{}}), m.setValue(new int[][]{{1}}));
    }

    public interface Str {
        String getValue();

        Str setValue(String value);
    }

    public interface O {
        Object getValue();

        O setValue(Object value);
    }

    public interface AStr {
        String[] getValue();

        AStr setValue(String[] value);
    }

    public interface AO {
        Object[] getValue();

        AO setValue(Object[] value);
    }

    public interface AI2 {
        int[][] getValue();

        AI2 setValue(int[][] value);
    }
}