A **monotonic stack** is a specialized stack data structure where the elements are kept in a specific sorted order—either strictly increasing or decreasing—from bottom to top. It is one of the most powerful patterns for solving array problems efficiently in **$O(N)$ time** instead of the naive $O(N^2)$ nested-loop approach.

```java
import java.util.Arrays;
import java.util.Stack;

public class MonotonicStackExample {

    public static int[] findNextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        // Default all results to -1 (meaning no greater element exists)
        Arrays.fill(result, -1);
        
        // We store indices in the stack rather than the values themselves
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            // Maintain a monotonically decreasing stack.
            // While stack is not empty and current element is greater than 
            // the element at the index stored at the top of the stack:
            while (!stack.isEmpty() && nums[i] > nums.get(stack.peek())) { // wait, Stack uses peek()
                int index = stack.pop();
                result[index] = nums[i]; // nums[i] is the next greater element
            }
            // Push the current index onto the stack
            stack.push(i);
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {2, 1, 2, 4, 3};
        int[] result = findNextGreaterElements(nums);
        
        System.out.println("Original: " + Arrays.toString(nums));
        System.out.println("Next Greater: " + Arrays.toString(result));
    }
}

```

### Why is the Time Complexity $O(N)$?

Even though there is a `while` loop inside a `for` loop, every element is pushed onto the stack exactly once and popped from the stack at most once. Thus, the inner loop operations do not run $N$ times for each outer iteration, resulting in a linear **$O(N)$ time complexity** and **$O(N)$ space complexity** for the stack and result array.
