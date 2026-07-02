# Strings
## New String
```java
String stra = "Hello World";            // String literal (Static Memory)
String str = new String("Hello World"); // heap allocation
```

## Length
```java
str.length();
```

## Concat
```java
str.concat(stra);
```

## IndexOf 
```java
str.indexOf("substring");       // returns first index of the start of substring
```

## Character Array
### To
```java
String a = new String("Hello");
Character[] a_a = a.toCharArray();
```

### From
```java
Character[] a_a = ['a','b','c'];
String a = new String(a_a);
// or
String a = String.valueOf(a_a);
```
