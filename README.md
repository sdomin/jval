# jval
jval is a library to create immutable value objects on the fly for java 8 

(The classes are generated internally using [asm](http://asm.ow2.org/))

## Usage example
First, an interface with getter and setter methods must be defined.
The setter methods should return the interface type instead of void.
The interface can override return type on *immutableCopy* and *mutableCopy* methods from [BaseValue](https://github.com/sdomin/jval/blob/master/src/jval/BaseValue.java) interface to make it easier to create copies of the object
```java
public interface DateValue extends BaseValue {

    LocalDateTime getDate();

    DateValue setDate(LocalDateTime date);

    double getValue();

    DateValue setValue(double value);

    @Override
    DateValue immutableCopy();

    @Override
    DateValue mutableCopy();
}
```

To create an object, use *create* method from [ValueFactory](https://github.com/sdomin/jval/blob/master/src/jval/ValueFactory.java):
```java
DateValue v = ValueFactory.create(DateValue.class); // immutable instance
DateValue v1 = v.setValue(1.0); // new copy
assertNotSame(v, v1);
assertEquals(0, v.getValue(), 1e-15);
assertEquals(1, v1.getValue(), 1e-15);

DateValue m = ValueFactory.create(DateValue.class, true); // mutable instance
assertNotSame(v, m);
assertEquals(v, m);
m = v.mutableCopy(); // new mutable instance - another way to create a new value
m = m.mutableCopy(); // new copy - yet another way to create a new value

DateValue m1 = m.setValue(1.0);
assertSame(m, m1);
assertNotEquals(m, v);
assertEquals(m, v1);
```

## TODO
- [ ] Describe how mutable values can effectively work as a *Builder* where immutable instance can be built using *immutableCopy* method.
- [ ] Describe how value interfaces can extend each other. (Is this really a useful feature?)
- [ ] Add *asMap* method to BaseValue. The map should support *put* methods on mutable values.
- [ ] What should *immutableCopy*, *mutableCopy* and setter methods do when working with other *jval* objects or with java collections?

## Links
- https://immutables.github.io/
- https://github.com/google/auto/tree/master/value ([presentation](https://docs.google.com/presentation/d/14u_h-lMn7f1rXE1nDiLX0azS3IkgjGl5uxp5jGJ75RE))
