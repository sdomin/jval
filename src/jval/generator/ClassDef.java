package jval.generator;

import jdk.nashorn.internal.codegen.types.Type;
import org.objectweb.asm.Opcodes;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.stream.Collectors;

import static jval.generator.FieldExtractor.extractFields;
import static jval.generator.FieldExtractor.verifyMethodTypes;

/**
 * @author Sergiy Domin
 */
class ClassDef {
    private final Class<?> iface;
    private final boolean immutable;

    private final String immutableInternalName;
    private final String mutableInternalName;

    private final List<FieldDef> fields;

    public ClassDef(Class<?> iface, boolean immutable,
                    String immutableInternalName, String mutableInternalName,
                    List<FieldDef> fields) {
        this.iface = iface;
        this.immutable = immutable;
        this.immutableInternalName = immutableInternalName;
        this.mutableInternalName = mutableInternalName;
        this.fields = Collections.unmodifiableList(fields);
    }

    static ClassDef fromInterface(Class<?> iface, boolean immutable, String immutableName, String mutableName) {
        Collection<FieldDef> fields = verifyType(iface).values();
        verifyFields(iface, fields);
        return new ClassDef(iface, immutable,
                internalName(immutableName), internalName(mutableName),
                new ArrayList<>(fields));
    }

    private static String internalName(String className) {
        return className.replace('.', '/');
    }

    private static SortedMap<String, FieldDef> verifyType(Class<?> type) {
        if (!type.isInterface()) {
            throw new IllegalArgumentException(type.getName() + " must be an interface");
        }
        if (!Modifier.isPublic(type.getModifiers())) {
            throw new IllegalArgumentException(type.getName() + " must be public");
        }

        SortedMap<String, FieldDef> fields = extractFields(type);
        verifyFields(type, fields.values());
        return fields;
    }

    private static void verifyFields(Class<?> type, Collection<FieldDef> fields) {
        fields.forEach(f -> verifyMethodTypes(type, f));

        Set<Method> allFieldMethods = fields.stream().flatMap(f -> f.getMethods().stream()).collect(Collectors.toSet());
        Set<Method> allMethods = new HashSet<>(Arrays.asList(type.getMethods()));
        allMethods.removeAll(allFieldMethods);
        if (!allMethods.isEmpty()) {
            throw new IllegalArgumentException("Default implementation required for methods: " + allMethods);
        }
    }

    public Class<?> getIface() {
        return iface;
    }

    public String getIfaceInternalName() {
        return Type.getInternalName(iface);
    }

    public boolean isImmutable() {
        return immutable;
    }

    public boolean isMutable() {
        return !isImmutable();
    }

    public String getImmutableInternalName() {
        return immutableInternalName;
    }

    public String getMutableInternalName() {
        return mutableInternalName;
    }

    public String getInternalClassName() {
        return immutable ? getImmutableInternalName() : getMutableInternalName();
    }

    public List<FieldDef> getFields() {
        return fields;
    }

    public int getFieldModifiers() {
        int modifiers = Opcodes.ACC_PRIVATE;
        if (immutable) {
            modifiers += Opcodes.ACC_FINAL;
        }
        return modifiers;
    }
}
