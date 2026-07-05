# ArrayList (implements List abstract class)

## New Array List
```java
ArrayList<Type> a_name = new ArrayList<>();
```

## Adding new Elements
```java
a_name.add("element");
a_name.add(0,"element"); // indexed add
```

## Modifying Existing Elements
```java
a_name.set(0,"new_element");
```

## Accessing Elements
```java
a_name.get(0); // gets first element
```

## Removing Elements
```java
a_name.remove(0);       // removes at index
```

## Clearing
```java
a_name.clear();
```

## Size
```java
a_name.size();
```

## Return Collection<> as ArrayList<>
```java
return new ArrayList<>(collection_var);

// ex: return new ArrayList<>(maps.values());
```

## Converting Set/Anything to ArrayList
```java
// Get the array of the elements
// of the ArrayList
// using toArray(T[]) method
Integer arr[] = new Integer[ArrLis.size()];
arr = ArrLis.toArray(arr);
System.out.println(" Elements of ArrayList " + Arrays.toString(arr));
```
