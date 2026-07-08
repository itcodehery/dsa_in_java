# Priority Queue in Java
Useful for when you have to store elements in a particular order.

## Initialization
```java
PriorityQueue<Map.Entry<Integer,Integer>> pq = new PriorityQueue<>((a,b) -> b.getValue() - a.getValue());

// Initialization defines a condition for calculating priority
// Stores max of previous value and current value
```

## Insert
```java
for (Map.Entry entry : map.entrySet()) {
    pq.add(entry);
}
```

## Get Highest Priority Element
```java
pq.poll()
```
