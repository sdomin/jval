package jval.generator;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import java.util.Collection;

/**
 * @author Sergiy Domin
 */
class BaseValueMethods {
    static void immutableCopy(ClassWriter cw, ClassDef classDef) {
        if (classDef.isImmutable()) {
            immutableCopy(classDef.getIface(), cw);
        } else {
            immutableCopy(classDef.getIface(), classDef.getMutableInternalName(), classDef.getImmutableInternalName(), classDef.getFields(), cw);
        }

        bridge(classDef.getIface(), classDef.getInternalClassName(), "immutableCopy", cw);
    }

    static void mutableCopy(ClassWriter cw, ClassDef classDef) {
        mutableCopy(classDef.getIface(), classDef.getMutableInternalName(), classDef.getInternalClassName(), classDef.getFields(), cw);
        bridge(classDef.getIface(), classDef.getInternalClassName(), "mutableCopy", cw);
    }

    private static void immutableCopy(Class<?> iface, ClassWriter cw) {
        MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC, "immutableCopy", "()" + Type.getDescriptor(iface), null, null);
        mv.visitCode();
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitInsn(Opcodes.ARETURN);
        mv.visitMaxs(-1, -1);
        mv.visitEnd();

    }

    private static void immutableCopy(Class<?> iface, String mutableName, String immutableName, Collection<FieldDef> fields, ClassWriter cw) {
        MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC, "immutableCopy", "()" + Type.getDescriptor(iface), null, null);
        mv.visitCode();
        mv.visitTypeInsn(Opcodes.NEW, immutableName);
        mv.visitInsn(Opcodes.DUP);
        fieldsToOpStack(mv, mutableName, fields);
        mv.visitMethodInsn(Opcodes.INVOKESPECIAL, immutableName, "<init>", ClassStructure.getConstructorDescriptor(fields), false);
        mv.visitInsn(Opcodes.ARETURN);
        mv.visitMaxs(-1, -1);
        mv.visitEnd();
    }

    private static void mutableCopy(Class<?> iface, String mutableClassName, String sourceClassName, Collection<FieldDef> fields, ClassWriter cw) {
        MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC, "mutableCopy", "()" + Type.getDescriptor(iface), null, null);
        mv.visitCode();
        mv.visitTypeInsn(Opcodes.NEW, mutableClassName);
        mv.visitInsn(Opcodes.DUP);
        fieldsToOpStack(mv, sourceClassName, fields);
        mv.visitMethodInsn(Opcodes.INVOKESPECIAL, mutableClassName, "<init>", ClassStructure.getConstructorDescriptor(fields), false);
        mv.visitInsn(Opcodes.ARETURN);
        mv.visitMaxs(-1, -1);
        mv.visitEnd();
    }

    private static void bridge(Class<?> iface, String className, String methodName, ClassWriter cw) {
        MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_BRIDGE + Opcodes.ACC_SYNTHETIC, methodName, "()Ljava/lang/Object;", null, null);
        mv.visitCode();
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, className, methodName, "()" + Type.getDescriptor(iface), false);
        mv.visitInsn(Opcodes.ARETURN);
        mv.visitMaxs(-1, -1);
        mv.visitEnd();
    }

    private static void fieldsToOpStack(MethodVisitor mv, String name, Collection<FieldDef> fields) {
        for (FieldDef f : fields) {
            mv.visitVarInsn(Opcodes.ALOAD, 0);
            mv.visitFieldInsn(Opcodes.GETFIELD, name, f.getName(), f.getTypeDescriptor());
        }
    }

}
