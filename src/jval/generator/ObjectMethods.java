package jval.generator;

import jval.util.EqualsHelper;
import jval.util.HashHelper;
import jval.util.ToStringHelper;
import org.objectweb.asm.*;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 * @author Sergiy Domin
 */
class ObjectMethods implements Opcodes {

    static void equals(ClassWriter cw, ClassDef classDef) {
        MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC, "equals", "(Ljava/lang/Object;)Z", null, null);
        mv.visitCode();

        // this == other
        Label classCheck = new Label();
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ALOAD, 1);
        mv.visitJumpInsn(Opcodes.IF_ACMPNE, classCheck);
        mv.visitInsn(Opcodes.ICONST_1);
        mv.visitInsn(Opcodes.IRETURN);

        // class check
        Label cast = new Label();
        mv.visitLabel(classCheck);
        mv.visitVarInsn(Opcodes.ALOAD, 1);
        Label failedClassCheck = new Label();
        mv.visitJumpInsn(Opcodes.IFNULL, failedClassCheck);
        mv.visitLdcInsn(Type.getObjectType(classDef.getMutableInternalName()));
        mv.visitVarInsn(Opcodes.ALOAD, 1);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/Object", "getClass", "()Ljava/lang/Class;", false);
        mv.visitJumpInsn(Opcodes.IF_ACMPEQ, cast);
        mv.visitLdcInsn(Type.getObjectType(classDef.getImmutableInternalName()));
        mv.visitVarInsn(Opcodes.ALOAD, 1);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/Object", "getClass", "()Ljava/lang/Class;", false);
        mv.visitJumpInsn(Opcodes.IF_ACMPEQ, cast);
        mv.visitLabel(failedClassCheck);
        mv.visitInsn(Opcodes.ICONST_0);
        mv.visitInsn(Opcodes.IRETURN);

        mv.visitLabel(cast);
        mv.visitVarInsn(Opcodes.ALOAD, 1);
        mv.visitTypeInsn(Opcodes.CHECKCAST, classDef.getIfaceInternalName());
        mv.visitVarInsn(Opcodes.ASTORE, 2);

        Label returnFalse = new Label();
        for (FieldDef field : classDef.getFields()) {
            mv.visitVarInsn(Opcodes.ALOAD, 0);
            Method getter = field.getGetter();
            mv.visitMethodInsn(Opcodes.INVOKEINTERFACE, classDef.getIfaceInternalName(), getter.getName(), Type.getMethodDescriptor(getter), true);
            mv.visitVarInsn(Opcodes.ALOAD, 2);
            mv.visitMethodInsn(Opcodes.INVOKEINTERFACE, classDef.getIfaceInternalName(), getter.getName(), Type.getMethodDescriptor(getter), true);
            if (field.getType().isPrimitive()) {
                if (field.getType() == double.class || field.getType() == float.class) {
                    mv.visitMethodInsn(Opcodes.INVOKESTATIC, Type.getInternalName(EqualsHelper.class), "equals", Type.getMethodDescriptor(Type.BOOLEAN_TYPE, field.getAsmType(), field.getAsmType()), false);
                    mv.visitJumpInsn(Opcodes.IFEQ, returnFalse);
                } else if (field.getType() == long.class) {
                    mv.visitInsn(Opcodes.LCMP);
                    mv.visitJumpInsn(Opcodes.IFNE, returnFalse);
                } else {
                    mv.visitJumpInsn(Opcodes.IF_ICMPNE, returnFalse);
                }
            } else if (field.getType().isArray()) {
                Class<?> componentType = field.getType().getComponentType();
                if (componentType.isPrimitive()) {
                    mv.visitMethodInsn(Opcodes.INVOKESTATIC, Type.getInternalName(EqualsHelper.class), "equals", Type.getMethodDescriptor(Type.BOOLEAN_TYPE, field.getAsmType(), field.getAsmType()), false);
                } else if (componentType.isArray()) {
                    mv.visitMethodInsn(Opcodes.INVOKESTATIC, Type.getInternalName(EqualsHelper.class), "deepEquals", Type.getMethodDescriptor(Type.BOOLEAN_TYPE, Type.getType(Object[].class), Type.getType(Object[].class)), false);
                } else {
                    mv.visitMethodInsn(Opcodes.INVOKESTATIC, Type.getInternalName(EqualsHelper.class), "equals", Type.getMethodDescriptor(Type.BOOLEAN_TYPE, Type.getType(Object[].class), Type.getType(Object[].class)), false);
                }
                mv.visitJumpInsn(Opcodes.IFEQ, returnFalse);
            } else {
                mv.visitMethodInsn(Opcodes.INVOKESTATIC, Type.getInternalName(EqualsHelper.class), "equals", Type.getMethodDescriptor(Type.BOOLEAN_TYPE, Type.getType(Object.class), Type.getType(Object.class)), false);
                mv.visitJumpInsn(Opcodes.IFEQ, returnFalse);
            }
        }

        mv.visitInsn(Opcodes.ICONST_1);
        Label returnLabel = new Label();
        mv.visitJumpInsn(Opcodes.GOTO, returnLabel);
        mv.visitLabel(returnFalse);
        mv.visitInsn(Opcodes.ICONST_0);
        mv.visitLabel(returnLabel);
        mv.visitInsn(Opcodes.IRETURN);

        mv.visitMaxs(-1, -1);
        mv.visitEnd();
    }

    static void hashCode(ClassWriter cw, ClassDef classDef) {
        String className = classDef.getInternalClassName();
        List<FieldDef> fields = classDef.getFields();

        MethodVisitor mv = cw.visitMethod(ACC_PUBLIC, "hashCode", "()I", null, null);
        mv.visitCode();
        mv.visitTypeInsn(NEW, "jval/util/HashHelper");
        mv.visitInsn(DUP);
        mv.visitMethodInsn(INVOKESPECIAL, "jval/util/HashHelper", "<init>", "()V", false);
        for (FieldDef field : fields) {
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, className, field.getName(), field.getTypeDescriptor());
            mv.visitMethodInsn(INVOKEVIRTUAL,
                    "jval/util/HashHelper",
                    field.isNestedArray() ? "addDeep" : "add",
                    Type.getMethodDescriptor(findHelperMethod(HashHelper.class, field.getType())), false);
        }
        mv.visitMethodInsn(INVOKEVIRTUAL, "jval/util/HashHelper", "hashCode", "()I", false);
        mv.visitInsn(IRETURN);

        mv.visitMaxs(-1, -1);
        mv.visitEnd();
    }

    static void toString(ClassWriter cw, ClassDef classDef) {
        String className = classDef.getInternalClassName();

        MethodVisitor mv = cw.visitMethod(ACC_PUBLIC, "toString", "()Ljava/lang/String;", null, null);
        mv.visitCode();

        String helperClass = Type.getInternalName(ToStringHelper.class);
        mv.visitTypeInsn(NEW, helperClass);
        mv.visitInsn(DUP);
        mv.visitLdcInsn(classDef.getIface().getSimpleName());
        mv.visitMethodInsn(INVOKESPECIAL, helperClass, "<init>", "(Ljava/lang/String;)V", false);

        for (FieldDef field : classDef.getFields()) {
            mv.visitLdcInsn(field.getName());
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, className, field.getName(), field.getTypeDescriptor());
            mv.visitMethodInsn(INVOKEVIRTUAL,
                    helperClass,
                    field.isNestedArray() ? "addDeep" : "add",
                    Type.getMethodDescriptor(findHelperMethod(ToStringHelper.class, field.getType(), String.class)),
                    false);
        }

        mv.visitMethodInsn(INVOKEVIRTUAL, helperClass, "build", "()Ljava/lang/String;", false);

        mv.visitInsn(ARETURN);
        mv.visitMaxs(-1, -1);
        mv.visitEnd();
    }

    private static Method findHelperMethod(Class<?> helperClass, Class<?> type, Class<?>... args) {
        boolean nonPrimitiveArray = type.isArray() && !type.getComponentType().isPrimitive();
        Class[] tmp = Arrays.copyOf(args, args.length + 1);
        tmp[args.length] = nonPrimitiveArray ? Object[].class : type;
        try {
            return helperClass.getMethod("add", tmp);
        } catch (NoSuchMethodException e) {
            if (!type.isPrimitive() && !type.isArray() && type != Object.class) {
                return findHelperMethod(helperClass, type.getSuperclass(), args);
            }
            throw new RuntimeException("Can't find " + helperClass.getSimpleName() + " method for " + type.getName() + ", args: " + Arrays.toString(args));
        }
    }
}
