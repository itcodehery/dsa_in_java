# Stack
Push and Pop are the only valid operations on it, so tread carefully.

## Initializing, Push, and Pop
```java
import java.util.Stack;

public class HelloWorld {
    public static void main(String[] args)
    {
        // Create a new stack
        Stack<Integer> s = new Stack<>();

        // Push elements onto the stack
        s.push(1);
        s.push(2);
        s.push(3);
        s.push(4);

        // Pop elements from the stack
        while (!s.isEmpty()) {
            System.out.println(s.pop());
        }
    }
}
```


## Valid Parenthesis
```java
class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        var charArray = s.toCharArray();

        if (s.length() < 2) {
            return false;
        }
        
        for (Character c: charArray) {
            switch (c) {
                case '(': 
                    stack.push(c);
                    break;
                case '{':
                    stack.push(c);
                    break;
                case '[':
                    stack.push(c);
                    break;
                case ')':
                    if (stack.isEmpty() || stack.peek() != '(') {
                        return false;
                    }
                    stack.pop();
                    break;
                case '}':
                    if (stack.isEmpty() || stack.peek() != '{') {
                        return false;
                    }
                    stack.pop();
                    break;
                case ']':
                    if (stack.isEmpty() || stack.peek() != '[') {
                        return false;
                    }
                    stack.pop();
                    break;
            }
        }

        return stack.isEmpty();
    }
}
```
