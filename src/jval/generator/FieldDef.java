package jval.generator;

import org.objectweb.asm.Type;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @author Sergiy Domin
 */
class FieldDef {
    private final String name;
    private final Class<?> type;
    private final List<Method> methods;

    public FieldDef(String name, Class<?> type, List<Method> methods) {
        this.name = name;
        this.type = type;
        this.methods = methods;
    }

    public String getName() {
        return name;
    }

    public Class<?> getType() {
        return type;
    }

    public boolean isNestedArray() {
        return getType().isArray() && getType().getComponentType().isArray();
    }

    public Type getAsmType() {
        return Type.getType(type);
    }

    public String getTypeDescriptor() {
        return getAsmType().getDescriptor();
    }

    public List<Method> getMethods() {
        return methods;
    }

    public Method getGetter() {
        return methods.stream().filter(FieldExtractor::isGetter).findFirst().orElseThrow(() -> new IllegalArgumentException("Missing gettor on " + this));
    }

    @Override
    public String toString() {
        return "FieldDef{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", methods=" + methods +
                '}';
    }
}
