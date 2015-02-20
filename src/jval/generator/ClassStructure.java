package jval.generator;

import jval.BaseValue;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;

/**
 * @author Sergiy Domin
 */
class ClassStructure {
    static void definition(ClassWriter cw, ClassDef classDef) {
        cw.visit(Opcodes.V1_8,
                Opcodes.ACC_PUBLIC + Opcodes.ACC_SUPER,
                classDef.getInternalClassName(),
                null,
                "java/lang/Object",
                new String[]{classDef.getIfaceInternalName(), Type.getInternalName(BaseValue.class)});
    }

    static void constructorDefault(ClassWriter cw) {
        //default
        MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
        mv.visitCode();
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(1, 1);
        mv.visitEnd();
    }

    static void constructorAllFields(ClassWriter cw, ClassDef classDef) {
        String className = classDef.getInternalClassName();
        List<FieldDef> fields = classDef.getFields();

        MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", getConstructorDescriptor(fields), null, null);
        mv.visitCode();
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
        int i = 1;
        for (FieldDef def : fields) {
            mv.visitVarInsn(Opcodes.ALOAD, 0);
            Type asmType = def.getAsmType();
            mv.visitVarInsn(asmType.getOpcode(Opcodes.ILOAD), i);
            i += asmType.getSize();
            mv.visitFieldInsn(Opcodes.PUTFIELD, className, def.getName(), asmType.getDescriptor());
        }
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(-1, -1);
        mv.visitEnd();
    }

    static void fields(ClassWriter cw, ClassDef classDef) {
        int modifiers = classDef.getFieldModifiers();
        for (FieldDef def : classDef.getFields()) {
            cw.visitField(modifiers, def.getName(), def.getTypeDescriptor(), null, null).visitEnd();
        }
    }

    static void gettersAndSetter(ClassWriter cw, ClassDef classDef) {
        String className = classDef.getInternalClassName();
        List<FieldDef> fields = classDef.getFields();

        String constructorDescriptor = getConstructorDescriptor(fields);
        for (FieldDef def : fields) {
            for (Method method : def.getMethods()) {
                MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC, method.getName(), Type.getMethodDescriptor(method), null, null);
                mv.visitCode();
                if (FieldExtractor.isGetter(method)) {
                    mv.visitVarInsn(Opcodes.ALOAD, 0);
                    mv.visitFieldInsn(Opcodes.GETFIELD, className, def.getName(), def.getTypeDescriptor());
                    mv.visitInsn(def.getAsmType().getOpcode(Opcodes.IRETURN));
                } else if (classDef.isImmutable()) {
                    mv.visitTypeInsn(Opcodes.NEW, className);
                    mv.visitInsn(Opcodes.DUP);
                    for (FieldDef f : fields) {
                        if (f.getName().equals(def.getName())) {
                            mv.visitVarInsn(f.getAsmType().getOpcode(Opcodes.ILOAD), 1);
                        } else {
                            mv.visitVarInsn(Opcodes.ALOAD, 0);
                            mv.visitFieldInsn(Opcodes.GETFIELD, className, f.getName(), f.getTypeDescriptor());
                        }
                    }
                    mv.visitMethodInsn(Opcodes.INVOKESPECIAL, className, "<init>", constructorDescriptor, false);
                    mv.visitInsn(Opcodes.ARETURN);
                } else {
                    mv.visitVarInsn(Opcodes.ALOAD, 0);
                    mv.visitVarInsn(def.getAsmType().getOpcode(Opcodes.ILOAD), 1);
                    mv.visitFieldInsn(Opcodes.PUTFIELD, className, def.getName(), def.getTypeDescriptor());
                    if (method.getReturnType() != Void.class) {
                        mv.visitVarInsn(Opcodes.ALOAD, 0);
                        mv.visitInsn(Opcodes.ARETURN);
                    } else {
                        mv.visitInsn(Opcodes.RETURN);
                    }
                }
                mv.visitMaxs(-1, -1);
                mv.visitEnd();
            }
        }
    }

    static String getConstructorDescriptor(Collection<FieldDef> fields) {
        return Type.getMethodDescriptor(Type.VOID_TYPE, fields.stream().map(FieldDef::getAsmType).toArray(Type[]::new));
    }
}
