package jval.examples;

import jval.util.EqualsHelper;
import jval.util.HashHelper;
import jval.util.ToStringHelper;

/**
 * @author Sergiy Domin
 */
public class AllFieldTypesTest {
    boolean bool;
    byte b;
    short s;
    char c;
    int i;
    long l;
    float f;
    double d;

    boolean[] abool;
    byte[] ab;
    short[] as;
    char[] ac;
    int[] ai;
    long[] al;
    float[] af;
    double[] ad;

    Object o;
    Object[] ao;

    String str;
    String[] astr;

    CharSequence cs;
    CharSequence[] acs;

    int[][] m;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null ||
                !(o.getClass() == AllFieldTypesTest.class))
            return false;

        AllFieldTypesTest that = (AllFieldTypesTest) o;
        return EqualsHelper.equals(bool, that.bool) &&
                EqualsHelper.equals(b, that.b) &&
                EqualsHelper.equals(s, that.s) &&
                EqualsHelper.equals(c, that.c) &&
                EqualsHelper.equals(i, that.i) &&
                EqualsHelper.equals(l, that.l) &&
                EqualsHelper.equals(f, that.f) &&
                EqualsHelper.equals(d, that.d) &&
                EqualsHelper.equals(abool, that.abool) &&
                EqualsHelper.equals(ab, that.ab) &&
                EqualsHelper.equals(as, that.as) &&
                EqualsHelper.equals(ac, that.ac) &&
                EqualsHelper.equals(ai, that.ai) &&
                EqualsHelper.equals(al, that.al) &&
                EqualsHelper.equals(af, that.af) &&
                EqualsHelper.equals(ad, that.ad) &&
                EqualsHelper.equals(this.o, that.o) &&
                EqualsHelper.equals(ao, that.ao) &&
                EqualsHelper.equals(str, that.str) &&
                EqualsHelper.equals(astr, that.astr) &&
                EqualsHelper.equals(cs, that.cs) &&
                EqualsHelper.equals(acs, that.acs) &&
                EqualsHelper.deepEquals(m, that.m);
    }

    @Override
    public int hashCode() {
        return new HashHelper()
                .add(bool)
                .add(b)
                .add(s)
                .add(c)
                .add(i)
                .add(l)
                .add(f)
                .add(d)
                .add(abool)
                .add(ab)
                .add(as)
                .add(ac)
                .add(ai)
                .add(al)
                .add(af)
                .add(ad)
                .add(o)
                .add(ao)
                .add(str)
                .add(astr)
                .add(cs)
                .add(acs)
                .addDeep(m)
                .hashCode();
    }

    @Override
    public String toString() {
        return new ToStringHelper("AllFieldTypesTest")
                .add("bool", bool)
                .add("b", b)
                .add("s", s)
                .add("c", c)
                .add("i", i)
                .add("l", l)
                .add("f", f)
                .add("d", d)
                .add("abool", abool)
                .add("ab", ab)
                .add("as", as)
                .add("ac", ac)
                .add("ai", ai)
                .add("al", al)
                .add("af", af)
                .add("ad", ad)
                .add("o", o)
                .add("ao", ao)
                .add("str", str)
                .add("astr", astr)
                .add("cs", cs)
                .add("acs", acs)
                .addDeep("m", m)
                .build();
    }
}
