# Inserting into a hashmap

## Hashmap my Solution:
```java
public static void main(String[] args) {
    HashMap<Integer, Integer> hmap = new HashMap<>();
    hmap.put(key, map.getOrDefault(key, 0) + 1);        // frequency counter
}
```

## Hashmap Optimized Solution:
```java
class Solution {
    public boolean containsDuplicate(int[] nums) {
        HashMap<Integer, Integer> seen = new HashMap<>();
        for (int num : nums) {
            if (seen.containsKey(num) && seen.get(num) >= 1)
                return true;
            seen.put(num, seen.getOrDefault(num, 0) + 1);
        }
        return false;
    }
}
```

## Comparing two Hashmaps:
```java
return map1.equals(map2);
```


## Getting all Keys
```java
map.keySet()
map.keySet().toArray() // converts to array for indexing access
```

## Getting all Values
```java
map.values()
```


## Check if contains Key
```java
map.containsKey("key");
```
