# My Solution which got accepted
```java
class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String token: tokens) {
            try {
                int num = Integer.parseInt(token);
                stack.push(num);
                // System.out.println("PStack: " + stack);
            } catch (Exception e) {
                int a = stack.pop();
                int b = stack.pop();
                // System.out.println("Token: " + token);
                stack.push(eval(a,b,token));
                // System.out.println("PEvalStack: " + stack);
            }
        }

        if (stack.isEmpty()) {
            return 0;
        } else {
            return stack.peek();
        }
    }

    public int eval(int a, int b, String op) {
        return switch (op) {
            case "+" -> a + b;
            case "-" -> b - a;
            case "/" -> b / a;
            case "*" -> a * b;
            default -> a + b;
        };
    }
}
```

Brooo apparently division and subtraction is always b / a and b - a everytime respectively! Keep that in mind the next time we do infix and postfix.
