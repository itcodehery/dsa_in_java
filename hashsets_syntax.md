## HashSet Solution: 
```java
class Solution {
    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> seen = new HashSet<>();
        for (int num : nums) {
            if (seen.contains(num))         // This is for some reason an O(1) operation, somehow that is possible
                return true;
            seen.add(num);
        }
        return false;
    }
}
```
