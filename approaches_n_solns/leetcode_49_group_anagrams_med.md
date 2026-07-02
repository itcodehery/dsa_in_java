# MY PROGRESS

```java
class Solution {
    boolean isAnagram(String str1, String str2) {
        if (str1.length() != str2.length()) {
            return false;
        }

        HashMap<Character, Integer> map1 = new HashMap<>();
        HashMap<Character, Integer> map2 = new HashMap<>();
        
        for (int i = 0 ; i < str1.length() ; i++) {
            map1.put(str1.charAt(i), map1.getOrDefault(str1.charAt(i), 0) + 1);
        }

        for (int j = 0 ; j < str2.length() ; j++) {
            map2.put(str2.charAt(j), map2.getOrDefault(str2.charAt(j),0) + 1);
        }

        return map1.equals(map2);
    }


    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        if (strs.length < 1) {
            return res;
        }

        for (int i = 0; i < strs.length ; i++) {
            ArrayList<String> newEntry = new ArrayList<String>();
            newEntry.add(strs[i]);
            for (int j = 1; j < strs.length - 1; j++) {
                if (!newEntry.contains(strs[j]) && isAnagram(strs[i], strs[j])) {
                    newEntry.add(strs[j]);
                }
            }
            res.add(newEntry);
        }

        return res;
    }
}
```

## SOLUTION I FOUND
- Sort the string as Character Array and make that the key, and for each, if the sorted value is the same as the key, just add it to the map under the same key
