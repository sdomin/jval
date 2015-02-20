package jval.generator;

import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Stream;

/**
 * @author Sergiy Domin
 */
class FieldExtractor {

    static SortedMap<String, FieldDef> extractFields(Class<?> type) {
        Map<String, List<Method>> methodsByField = extractFieldMethods(type);
        SortedMap<String, FieldDef> result = new TreeMap<>();
        for (Map.Entry<String, List<Method>> e : methodsByField.entrySet()) {
            String name = e.getKey();
            List<Method> methods = e.getValue();
            result.put(name, new FieldDef(name, getFieldType(methods), methods));
        }
        return result;
    }

    static Map<String, List<Method>> extractFieldMethods(Class<?> type) {
        SortedMap<String, List<Method>> byName = new TreeMap<>();
        for (Method method : type.getMethods()) {
            if (method.isDefault()) {
                continue;
            }
            getFieldName(method).ifPresent(name -> addToListMap(byName, name, method));
        }
        return byName;
    }

    static boolean isGetter(Method method) {
        return method.getParameterCount() == 0;
    }

    static void verifyMethodTypes(Class<?> type, FieldDef field) {
        List<Method> methods = field.getMethods();
        Class<?> fieldType = field.getType();

        getters(methods)
                .map(Method::getReturnType)
                .filter(t -> t != fieldType)
                .findFirst()
                .ifPresent(t -> {
                    throw new IllegalArgumentException("All getter return types must be same, but got "
                            + type.getName() + " and " + t.getName()
                            + ": " + methods);
                });

        setters(methods)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No setter methods: " + methods));

        setters(methods)
                .map(m -> m.getParameterTypes()[0])
                .filter(type::isAssignableFrom)
                .findFirst()
                .ifPresent(t -> {
                    throw new IllegalArgumentException("Setter type " + t.getName() + " incompatible with getter type " + type.getName()
                            + ": " + methods);
                });

        setters(methods)
                .map(Method::getReturnType)
                .filter(t -> t != Void.class && !t.isAssignableFrom(type))
                .findFirst()
                .ifPresent(t -> {
                    throw new IllegalArgumentException("Setter return type " + t + " isn't compatible with interface type " + type
                            + ": " + methods);
                });
    }

    private static Class getFieldType(List<Method> methods) {
        return getters(methods)
                .map(Method::getReturnType)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No getter methods: " + methods));
    }

    private static Optional<String> getFieldName(Method method) {
        String name = method.getName();
        Class<?> returnType = method.getReturnType();
        if (isGetter(method)) {
            if (name.startsWith("get")) {
                return decapitalize(name.substring(3));
            } else if (returnType == boolean.class || returnType == Boolean.class) {
                if (name.startsWith("is")) {
                    return decapitalize(name.substring(2));
                } else if (name.startsWith("has")) {
                    return decapitalize(name.substring(3));
                }
            }
        } else if (isSetter(method)) {
            if (name.startsWith("set")) {
                return decapitalize(name.substring(3));
            } else if (name.startsWith("with") && method.getReturnType() != Void.class) {
                return decapitalize(name.substring(4));
            }
        }
        return Optional.empty();
    }

    private static Optional<String> decapitalize(String s) {
        if (s.length() == 0) {
            return Optional.empty();
        }
        return Optional.of(Character.toLowerCase(s.charAt(0)) + s.substring(1));
    }

    private static Stream<Method> getters(List<Method> methods) {
        return methods.stream()
                .filter(FieldExtractor::isGetter);
    }

    private static Stream<Method> setters(List<Method> methods) {
        return methods.stream()
                .filter(FieldExtractor::isSetter);
    }

    private static boolean isSetter(Method method) {
        return method.getParameterCount() == 1;
    }

    private static <K, V> boolean addToListMap(Map<K, List<V>> byName, K key, V value) {
        List<V> list = byName.get(key);
        if (list == null) {
            list = new ArrayList<>();
            byName.put(key, list);
        }
        return list.add(value);
    }

}
