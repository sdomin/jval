package jval.generator;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

/**
 * @author Sergiy Domin
 */
class Generator implements Opcodes {

    static byte[] define(ClassDef classDef) {
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS + ClassWriter.COMPUTE_FRAMES);

        ClassStructure.definition(cw, classDef);
        ClassStructure.fields(cw, classDef);
        if (classDef.isMutable()) {
            ClassStructure.constructorDefault(cw);
        }
        ClassStructure.constructorAllFields(cw, classDef);
        ClassStructure.gettersAndSetter(cw, classDef);

        BaseValueMethods.immutableCopy(cw, classDef);
        BaseValueMethods.mutableCopy(cw, classDef);

        ObjectMethods.equals(cw, classDef);
        ObjectMethods.hashCode(cw, classDef);
        ObjectMethods.toString(cw, classDef);

        cw.visitEnd();
        return cw.toByteArray();
    }
}
