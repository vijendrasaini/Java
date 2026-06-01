# Java Stream Methods

Java Streams help process collections in a clean, readable, and functional style.
Most stream methods return a new stream, so they are commonly chained together.

```java
List<String> result = names.stream()
    .filter(name -> name.startsWith("A"))
    .map(String::toUpperCase)
    .toList();
```

## Method Types

| Type | Meaning | Examples |
| --- | --- | --- |
| Intermediate methods | Return another stream and can be chained | `filter`, `map`, `sorted` |
| Terminal methods | Produce a final result or side effect | `collect`, `toList`, `forEach`, `count` |

> The methods in this file are intermediate methods.

## 1. Filtering And Slicing

These methods reduce or select elements from a stream.

### `filter()`

Keeps only the elements that match a condition.

```java
List<Integer> evenNumbers = numbers.stream()
    .filter(number -> number % 2 == 0)
    .toList();
```

### `distinct()`

Removes duplicate elements.

```java
List<Integer> uniqueNumbers = numbers.stream()
    .distinct()
    .toList();
```

### `limit()`

Keeps only the first `n` elements.

```java
List<String> firstThreeNames = names.stream()
    .limit(3)
    .toList();
```

### `skip()`

Skips the first `n` elements.

```java
List<String> remainingNames = names.stream()
    .skip(2)
    .toList();
```

### `takeWhile()`

Takes elements while the condition is true. It stops as soon as the condition
becomes false.

```java
List<Integer> smallNumbers = numbers.stream()
    .takeWhile(number -> number < 10)
    .toList();
```

### `dropWhile()`

Drops elements while the condition is true. Once the condition becomes false,
it keeps the rest of the stream.

```java
List<Integer> remainingNumbers = numbers.stream()
    .dropWhile(number -> number < 10)
    .toList();
```

## 2. Mapping And Shaping

These methods transform stream elements from one form to another.

### `map()`

Transforms each element into another value.

```java
List<Integer> nameLengths = names.stream()
    .map(String::length)
    .toList();
```

### `flatMap()`

Converts each element into a stream, then flattens all streams into one stream.

```java
List<String> words = sentences.stream()
    .flatMap(sentence -> Arrays.stream(sentence.split(" ")))
    .toList();
```

### `mapToInt()`

Converts a stream into an `IntStream`.

```java
int totalLength = names.stream()
    .mapToInt(String::length)
    .sum();
```

### `mapToLong()`

Converts a stream into a `LongStream`.

```java
long totalSalary = employees.stream()
    .mapToLong(Employee::getSalary)
    .sum();
```

### `mapToDouble()`

Converts a stream into a `DoubleStream`.

```java
double averagePrice = products.stream()
    .mapToDouble(Product::getPrice)
    .average()
    .orElse(0.0);
```

### `mapToObj()`

Converts a primitive stream back into an object stream.

```java
List<String> labels = IntStream.rangeClosed(1, 5)
    .mapToObj(number -> "Item " + number)
    .toList();
```

## 3. Sorting And Debugging

These methods help order elements or inspect a stream pipeline.

### `sorted()`

Sorts stream elements in natural order or by a custom comparator.

```java
List<String> sortedNames = names.stream()
    .sorted()
    .toList();
```

```java
List<Employee> sortedEmployees = employees.stream()
    .sorted(Comparator.comparing(Employee::getSalary))
    .toList();
```

### `peek()`

Performs an action on each element while the stream continues. It is mainly
used for debugging.

```java
List<String> upperCaseNames = names.stream()
    .peek(name -> System.out.println("Before: " + name))
    .map(String::toUpperCase)
    .peek(name -> System.out.println("After: " + name))
    .toList();
```

## Quick Summary

| Method | Purpose |
| --- | --- |
| `filter()` | Keeps elements that match a condition |
| `distinct()` | Removes duplicates |
| `limit()` | Keeps only the first `n` elements |
| `skip()` | Skips the first `n` elements |
| `takeWhile()` | Takes elements until the condition becomes false |
| `dropWhile()` | Drops elements until the condition becomes false |
| `map()` | Transforms each element |
| `flatMap()` | Flattens nested streams into one stream |
| `mapToInt()` | Converts to `IntStream` |
| `mapToLong()` | Converts to `LongStream` |
| `mapToDouble()` | Converts to `DoubleStream` |
| `mapToObj()` | Converts a primitive stream to an object stream |
| `sorted()` | Sorts stream elements |
| `peek()` | Inspects stream elements during processing |
