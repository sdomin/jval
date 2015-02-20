package jval.examples;

import org.junit.Ignore;
import org.junit.Test;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.util.ASMifier;
import org.objectweb.asm.util.TraceClassVisitor;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Sergiy Domin
 */
@Ignore
public class GeneratorCodeForExamples {
    @Test
    public void immutableValue() throws IOException {
        ClassReader cr = new ClassReader(DateValue_IVal.class.getName());
        cr.accept(new TraceClassVisitor(null, new ASMifier(), new PrintWriter(System.out)), ClassReader.SKIP_DEBUG);
    }

    @Test
    public void value() throws IOException {
        ClassReader cr = new ClassReader(DateValue_Val.class.getName());
        cr.accept(new TraceClassVisitor(null, new ASMifier(), new PrintWriter(System.out)), ClassReader.SKIP_DEBUG);
    }

    @Test
    public void allFieldTypes() throws IOException {
        ClassReader cr = new ClassReader(AllFieldTypesTest.class.getName());
        cr.accept(new TraceClassVisitor(null, new ASMifier(), new PrintWriter(System.out)), ClassReader.SKIP_DEBUG);
    }
}
