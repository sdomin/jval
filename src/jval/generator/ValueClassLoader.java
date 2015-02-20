package jval.generator;

import static java.util.Objects.requireNonNull;

/**
 * @author Sergiy Domin
 */
public final class ValueClassLoader extends ClassLoader {
    static final String MUTABLE_SUFFIX = "__Val__";
    static final String IMMUTABLE_SUFFIX = "__IVal__";

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        if (name.endsWith(MUTABLE_SUFFIX)) {
            String ifaceName = name.substring(0, name.length() - MUTABLE_SUFFIX.length());
            Class<?> iface = getParent().loadClass(ifaceName);
            byte[] b = Generator.define(ClassDef.fromInterface(iface, false, ifaceName + IMMUTABLE_SUFFIX, name));
            return defineClass(name, b, 0, b.length);
        } else if (name.endsWith(IMMUTABLE_SUFFIX)) {
            String ifaceName = name.substring(0, name.length() - IMMUTABLE_SUFFIX.length());
            Class<?> iface = getParent().loadClass(ifaceName);
            byte[] b = Generator.define(ClassDef.fromInterface(iface, true, name, ifaceName + MUTABLE_SUFFIX));
            return defineClass(name, b, 0, b.length);
        }
        return super.findClass(name);
    }

    /**
     * Only mutable instance method exists because only mutable values have no-args constructor.
     * Use {@link jval.BaseValue#immutableCopy()} to create an immutable instance
     */
    public <T> T mutableInstance(Class<T> iface) {
        requireNonNull(iface, "iface");
        try {
            Class valueClass = loadClass(iface.getName() + MUTABLE_SUFFIX);
            return iface.cast(valueClass.newInstance());
        } catch (Exception e) {
            throw new RuntimeException("Can't create mutableInstance of class " + iface, e);
        }
    }

}
