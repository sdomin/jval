package jval;

import jval.generator.ValueClassLoader;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author Sergiy Domin
 */
public enum ValueFactory {
    INSTANCE;

    private final ValueClassLoader classLoader = new ValueClassLoader();
    private final ConcurrentMap<Class<?>, Object> values = new ConcurrentHashMap<>();

    public static <T> T create(Class<T> iface) {
        return iface.cast(INSTANCE.values.computeIfAbsent(iface, c -> ((BaseValue) INSTANCE.classLoader.mutableInstance(c)).immutableCopy()));
    }

    public static <T> T create(Class<T> iface, boolean mutable) {
        T t = create(iface);
        return mutable ? iface.cast(((BaseValue) create(iface)).mutableCopy()) : t;
    }
}
