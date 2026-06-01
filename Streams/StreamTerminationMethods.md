# Java Stream Termination Methods

Java Stream terminal methods produce a final result or a side effect and close the stream pipeline.
Use them after the intermediate operations in a stream chain.

```java
long count = names.stream()
    .filter(name -> name.startsWith("A"))
    .count();
```

## 1. Looping and Side Effects

These methods consume the stream and perform actions for each element.

### `forEach()`

Executes an action for each element in the stream.

```java
names.stream()
    .forEach(System.out::println);
```

### `forEachOrdered()`

Like `forEach()`, but preserves encounter order for ordered streams.

```java
names.stream()
    .parallel()
    .forEachOrdered(System.out::println);
```

### `iterator()`

Returns an iterator over the stream elements.

```java
Iterator<String> iterator = names.stream().iterator();
while (iterator.hasNext()) {
    System.out.println(iterator.next());
}
```

### `spliterator()`

Returns a `Spliterator` for the stream, useful for custom traversal or splitting.

```java
Spliterator<String> spliterator = names.stream().spliterator();
spliterator.forEachRemaining(System.out::println);
```

## 2. Reduction and Aggregation

These methods combine stream elements into a single result.

### `count()`

Counts the elements in the stream.

```java
long count = names.stream().count();
```

### `collect()`

Collects stream elements into a collection or other container.

```java
List<String> list = names.stream()
    .collect(Collectors.toList());
```

### `toList()`

A convenience terminal method that collects elements into an immutable list.

```java
List<String> list = names.stream().toList();
```

### `min()`

Finds the minimum element according to a comparator.

```java
Optional<String> minName = names.stream()
    .min(String::compareTo);
```

### `max()`

Finds the maximum element according to a comparator.

```java
Optional<String> maxName = names.stream()
    .max(String::compareTo);
```

### `reduce()`

Aggregates stream elements into a single value.

```java
Optional<Integer> sum = numbers.stream()
    .reduce(Integer::sum);
```

## 3. Matching and Finding

These methods search the stream and return a boolean or optional result.

### `anyMatch()`

Returns `true` if any element matches the predicate.

```java
boolean hasShortName = names.stream()
    .anyMatch(name -> name.length() < 5);
```

### `allMatch()`

Returns `true` if all elements match the predicate.

```java
boolean allUppercase = names.stream()
    .allMatch(name -> name.equals(name.toUpperCase()));
```

### `noneMatch()`

Returns `true` if no elements match the predicate.

```java
boolean noneEmpty = names.stream()
    .noneMatch(String::isEmpty);
```

### `findFirst()`

Returns the first element in the stream, if present.

```java
Optional<String> first = names.stream().findFirst();
```

### `findAny()`

Returns any element from the stream; useful with parallel streams.

```java
Optional<String> any = names.stream().findAny();
```

## Quick Summary

| Method | Purpose |
| --- | --- |
| `forEach()` | Execute an action for each element |
| `forEachOrdered()` | Execute an action in encounter order |
| `iterator()` | Iterate over stream elements |
| `spliterator()` | Traverse or split stream elements |
| `count()` | Count elements |
| `collect()` | Collect elements into a container |
| `toList()` | Collect elements into a list |
| `min()` | Find minimum element |
| `max()` | Find maximum element |
| `reduce()` | Aggregate elements into one value |
| `anyMatch()` | Check if any element matches |
| `allMatch()` | Check if all elements match |
| `noneMatch()` | Check if no elements match |
| `findFirst()` | Return the first element |
| `findAny()` | Return any element |