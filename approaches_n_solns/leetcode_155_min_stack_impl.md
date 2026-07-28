# My Min Stack Implementation
When pushing, for every value check if it is smaller than the top value of the mini stack, if yes, push that into the mini, or just push the top value of the mini stack again into it. This helps us track the stack length for both which is to be equal at all times apparently.

```java
class MinStack {
    ArrayList<Integer> arr;
    ArrayList<Integer> mini;
    
    public MinStack() {
        arr = new ArrayList<>();
        mini = new ArrayList<>();
    }
    
    public void push(int value) {
        arr.add(value);

        if (mini.isEmpty()) {
            mini.add(value);
        } else {
            mini.add(Math.min(mini.get(mini.size() - 1), value));
        }
    }

    public void pop() {
        if (!arr.isEmpty()) {
            arr.remove(arr.size() - 1);
            mini.remove(mini.size() - 1);
        }
    }

    public int top() {
        if (arr.isEmpty()) {
            return -1;   
        }
        return arr.get(arr.size() - 1);
    }

    public int getMin() {
        if (mini.isEmpty()) {
            return -1;   
        }
        return mini.get(mini.size() - 1);
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(value);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
```

# Clean 6ms approach
This creates a Node data structure to do the same, but technically for the interviewer it is more interesting.

```java
class MinStack {
	private Node head;
        
    public void push(int x) {
        if (head == null) 
            head = new Node(x, x, null);
        else 
            head = new Node(x, Math.min(x, head.min), head);
    }
    
    public void pop() {
        head = head.next;
    }
    
    public int top() {
        return head.val;
    }
    
    public int getMin() {
        return head.min;
    }
        
    private class Node {
        int val;
        int min;
        Node next;
            
        private Node(int val, int min, Node next) {
            this.val = val;
            this.min = min;
            this.next = next;
        }
    }
}
```
