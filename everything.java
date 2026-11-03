import java.util.*;

/*
 * DSA PATTERNS CHEAT SHEET (Java)
 *
 * JAVA COLLECTIONS QUICK REFERENCE
 * --------------------------------
 * Map<K,V> m = new HashMap<>();   m.put, m.get, m.getOrDefault(k, d), m.containsKey,
 *                                 m.merge(k, 1, Integer::sum), m.computeIfAbsent(k, x -> new ArrayList<>())
 * Set<T> s = new HashSet<>();     s.add (returns false if already present), s.contains, s.remove
 * Deque<T> stack = new ArrayDeque<>();  push, pop, peek, isEmpty   (use this, NOT java.util.Stack)
 * Queue<T> q = new LinkedList<>();      offer, poll, peek, size, isEmpty
 * PriorityQueue<Integer> minHeap = new PriorityQueue<>();
 * PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
 * List<Integer> list = new ArrayList<>();  add, get, set, remove(int index), size
 * Arrays.sort(arr);  Arrays.sort(arr, (a, b) -> a[0] - b[0]);  Arrays.fill(arr, v);
 * String: s.charAt(i), s.length(), s.substring(i, j), s.toCharArray(), new String(charArray)
 * StringBuilder: append, deleteCharAt(idx), reverse, toString
 *
 * JAVA GOTCHAS
 * ------------
 * - Compare Integer objects with .equals(), not == (cache only covers -128..127)
 * - list.remove(Integer.valueOf(x)) removes by value; list.remove(i) removes by index
 * - Use mid = lo + (hi - lo) / 2 to avoid integer overflow
 * - When storing a path into results, copy it: res.add(new ArrayList<>(path))
 * - Use long for sums/products that may overflow int
 */
public class DSAPatterns {

    // =====================================================================
    // 1. HASH MAP / SET
    // =====================================================================

    static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> seen = new HashMap<>();   // value -> index
        for (int i = 0; i < nums.length; i++) {
            int need = target - nums[i];
            if (seen.containsKey(need)) return new int[]{seen.get(need), i};
            seen.put(nums[i], i);
        }
        return new int[]{};
    }

    static boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int x : nums) {
            if (!set.add(x)) return true;               // add() returns false if already present
        }
        return false;
    }

    static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
            count[t.charAt(i) - 'a']--;
        }
        for (int c : count) if (c != 0) return false;
        return true;
    }

    static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
        }
        return new ArrayList<>(map.values());
    }

    static int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int x : nums) set.add(x);
        int best = 0;
        for (int x : set) {
            if (!set.contains(x - 1)) {                 // x is the start of a sequence
                int len = 1;
                while (set.contains(x + len)) len++;
                best = Math.max(best, len);
            }
        }
        return best;
    }

    // =====================================================================
    // 2. TWO POINTERS
    // =====================================================================

    static boolean isPalindrome(String s) {
        int l = 0, r = s.length() - 1;
        while (l < r) {
            while (l < r && !Character.isLetterOrDigit(s.charAt(l))) l++;
            while (l < r && !Character.isLetterOrDigit(s.charAt(r))) r--;
            if (Character.toLowerCase(s.charAt(l)) != Character.toLowerCase(s.charAt(r))) return false;
            l++;
            r--;
        }
        return true;
    }

    static int[] twoSumSorted(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int sum = nums[l] + nums[r];
            if (sum == target) return new int[]{l + 1, r + 1};   // 1-indexed in LeetCode
            else if (sum < target) l++;
            else r--;
        }
        return new int[]{};
    }

    static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;       // skip duplicate anchors
            int l = i + 1, r = nums.length - 1;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    l++;
                    r--;
                    while (l < r && nums[l] == nums[l - 1]) l++;  // skip duplicate lefts
                } else if (sum < 0) l++;
                else r--;
            }
        }
        return res;
    }

    static int maxArea(int[] height) {                            // Container With Most Water
        int l = 0, r = height.length - 1, best = 0;
        while (l < r) {
            best = Math.max(best, Math.min(height[l], height[r]) * (r - l));
            if (height[l] < height[r]) l++;
            else r--;
        }
        return best;
    }

    static void moveZeroes(int[] nums) {
        int write = 0;
        for (int x : nums) if (x != 0) nums[write++] = x;
        while (write < nums.length) nums[write++] = 0;
    }

    // =====================================================================
    // 3. SLIDING WINDOW
    // =====================================================================

    static int maxProfit(int[] prices) {                          // Best Time to Buy and Sell Stock
        int minPrice = Integer.MAX_VALUE, best = 0;
        for (int p : prices) {
            minPrice = Math.min(minPrice, p);
            best = Math.max(best, p - minPrice);
        }
        return best;
    }

    static int lengthOfLongestSubstring(String s) {
        Set<Character> window = new HashSet<>();
        int left = 0, best = 0;
        for (int right = 0; right < s.length(); right++) {
            while (window.contains(s.charAt(right))) {
                window.remove(s.charAt(left));
                left++;
            }
            window.add(s.charAt(right));
            best = Math.max(best, right - left + 1);
        }
        return best;
    }

    static int characterReplacement(String s, int k) {            // Longest Repeating Character Replacement
        int[] count = new int[26];
        int left = 0, maxFreq = 0, best = 0;
        for (int right = 0; right < s.length(); right++) {
            maxFreq = Math.max(maxFreq, ++count[s.charAt(right) - 'A']);
            while ((right - left + 1) - maxFreq > k) {
                count[s.charAt(left) - 'A']--;
                left++;
            }
            best = Math.max(best, right - left + 1);
        }
        return best;
    }

    static int minSubArrayLen(int target, int[] nums) {           // fixed condition, shrink-while-valid
        int left = 0, sum = 0, best = Integer.MAX_VALUE;
        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];
            while (sum >= target) {
                best = Math.min(best, right - left + 1);
                sum -= nums[left++];
            }
        }
        return best == Integer.MAX_VALUE ? 0 : best;
    }

    // =====================================================================
    // 4. STACK (INCLUDING MONOTONIC)
    // =====================================================================

    static boolean isValid(String s) {                            // Valid Parentheses
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (c == '(') stack.push(')');
            else if (c == '[') stack.push(']');
            else if (c == '{') stack.push('}');
            else if (stack.isEmpty() || stack.pop() != c) return false;
        }
        return stack.isEmpty();
    }

    static int[] nextGreaterElements(int[] arr) {                 // monotonic decreasing stack of indices
        int n = arr.length;
        int[] result = new int[n];
        Arrays.fill(result, -1);
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
                result[stack.pop()] = arr[i];
            }
            stack.push(i);
        }
        return result;
    }

    static int[] dailyTemperatures(int[] temps) {                 // same pattern, store distance
        int n = temps.length;
        int[] res = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && temps[stack.peek()] < temps[i]) {
                int idx = stack.pop();
                res[idx] = i - idx;
            }
            stack.push(i);
        }
        return res;
    }

    static int evalRPN(String[] tokens) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (String t : tokens) {
            switch (t) {
                case "+": stack.push(stack.pop() + stack.pop()); break;
                case "*": stack.push(stack.pop() * stack.pop()); break;
                case "-": { int b = stack.pop(), a = stack.pop(); stack.push(a - b); break; }
                case "/": { int b = stack.pop(), a = stack.pop(); stack.push(a / b); break; }
                default: stack.push(Integer.parseInt(t));
            }
        }
        return stack.pop();
    }

    static class MinStack {                                       // store (value, minSoFar)
        private final Deque<int[]> stack = new ArrayDeque<>();

        void push(int x) {
            int min = stack.isEmpty() ? x : Math.min(x, stack.peek()[1]);
            stack.push(new int[]{x, min});
        }

        void pop() { stack.pop(); }

        int top() { return stack.peek()[0]; }

        int getMin() { return stack.peek()[1]; }
    }

    // =====================================================================
    // 5. BINARY SEARCH
    // =====================================================================

    static int binarySearch(int[] a, int target) {
        int lo = 0, hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (a[mid] == target) return mid;
            else if (a[mid] < target) lo = mid + 1;
            else hi = mid - 1;
        }
        return -1;
    }

    static int searchRotated(int[] a, int target) {
        int lo = 0, hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (a[mid] == target) return mid;
            if (a[lo] <= a[mid]) {                                // left half sorted
                if (a[lo] <= target && target < a[mid]) hi = mid - 1;
                else lo = mid + 1;
            } else {                                              // right half sorted
                if (a[mid] < target && target <= a[hi]) lo = mid + 1;
                else hi = mid - 1;
            }
        }
        return -1;
    }

    static int findMinRotated(int[] a) {
        int lo = 0, hi = a.length - 1;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (a[mid] > a[hi]) lo = mid + 1;
            else hi = mid;
        }
        return a[lo];
    }

    static int minEatingSpeed(int[] piles, int h) {               // binary search on the ANSWER
        int lo = 1, hi = 0;
        for (int p : piles) hi = Math.max(hi, p);
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            long hours = 0;
            for (int p : piles) hours += (p + mid - 1) / mid;     // ceil(p / mid)
            if (hours <= h) hi = mid;                             // feasible, try smaller
            else lo = mid + 1;
        }
        return lo;
    }

    // =====================================================================
    // 6. LINKED LIST
    // =====================================================================

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    static ListNode reverseList(ListNode head) {
        ListNode prev = null, curr = head;
        while (curr != null) {
            ListNode next = curr.next;   // 1. save next
            curr.next = prev;            // 2. flip pointer
            prev = curr;                 // 3. advance prev
            curr = next;                 // 4. advance curr
        }
        return prev;
    }

    static ListNode mergeTwoLists(ListNode a, ListNode b) {
        ListNode dummy = new ListNode(0), tail = dummy;
        while (a != null && b != null) {
            if (a.val <= b.val) { tail.next = a; a = a.next; }
            else { tail.next = b; b = b.next; }
            tail = tail.next;
        }
        tail.next = (a != null) ? a : b;
        return dummy.next;
    }

    static boolean hasCycle(ListNode head) {                      // Floyd's fast/slow
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;
        }
        return false;
    }

    static ListNode middleNode(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = dummy, slow = dummy;
        for (int i = 0; i < n; i++) fast = fast.next;             // lead by n
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }

    // =====================================================================
    // 7. TREES (DFS AND BFS)
    // =====================================================================

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    static int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    static TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        TreeNode temp = root.left;
        root.left = invertTree(root.right);
        root.right = invertTree(temp);
        return root;
    }

    static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null || p.val != q.val) return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    static boolean isValidBST(TreeNode root) {
        return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private static boolean validate(TreeNode node, long lo, long hi) {
        if (node == null) return true;
        if (node.val <= lo || node.val >= hi) return false;
        return validate(node.left, lo, node.val) && validate(node.right, node.val, hi);
    }

    private static int diameter;

    static int diameterOfBinaryTree(TreeNode root) {
        diameter = 0;
        height(root);
        return diameter;
    }

    private static int height(TreeNode node) {
        if (node == null) return 0;
        int l = height(node.left), r = height(node.right);
        diameter = Math.max(diameter, l + r);                     // path through this node
        return 1 + Math.max(l, r);
    }

    static TreeNode lcaBST(TreeNode root, TreeNode p, TreeNode q) {
        while (root != null) {
            if (p.val < root.val && q.val < root.val) root = root.left;
            else if (p.val > root.val && q.val > root.val) root = root.right;
            else return root;
        }
        return null;
    }

    static TreeNode lcaBinaryTree(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left = lcaBinaryTree(root.left, p, q);
        TreeNode right = lcaBinaryTree(root.right, p, q);
        if (left != null && right != null) return root;
        return left != null ? left : right;
    }

    static List<List<Integer>> levelOrder(TreeNode root) {        // BFS template
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();                              // freeze level size
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            res.add(level);
        }
        return res;
    }

    // =====================================================================
    // 8. GRAPHS
    // =====================================================================

    static int numIslands(char[][] grid) {                        // DFS flood fill
        int count = 0;
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == '1') {
                    sink(grid, r, c);
                    count++;
                }
            }
        }
        return count;
    }

    private static void sink(char[][] g, int r, int c) {
        if (r < 0 || c < 0 || r >= g.length || c >= g[0].length || g[r][c] != '1') return;
        g[r][c] = '0';                                            // mark visited
        sink(g, r + 1, c);
        sink(g, r - 1, c);
        sink(g, r, c + 1);
        sink(g, r, c - 1);
    }

    static boolean canFinish(int numCourses, int[][] prerequisites) {   // topological sort (Kahn's BFS)
        List<List<Integer>> adj = new ArrayList<>();
        int[] indegree = new int[numCourses];
        for (int i = 0; i < numCourses; i++) adj.add(new ArrayList<>());
        for (int[] p : prerequisites) {
            adj.get(p[1]).add(p[0]);                              // p[1] -> p[0]
            indegree[p[0]]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) if (indegree[i] == 0) queue.offer(i);
        int processed = 0;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            processed++;
            for (int next : adj.get(cur)) {
                if (--indegree[next] == 0) queue.offer(next);
            }
        }
        return processed == numCourses;                           // false means a cycle exists
    }

    static int orangesRotting(int[][] grid) {                     // multi-source BFS
        int rows = grid.length, cols = grid[0].length, fresh = 0;
        Queue<int[]> queue = new LinkedList<>();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 2) queue.offer(new int[]{r, c});
                else if (grid[r][c] == 1) fresh++;
            }
        }
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int minutes = 0;
        while (!queue.isEmpty() && fresh > 0) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                for (int[] d : dirs) {
                    int nr = cur[0] + d[0], nc = cur[1] + d[1];
                    if (nr < 0 || nc < 0 || nr >= rows || nc >= cols || grid[nr][nc] != 1) continue;
                    grid[nr][nc] = 2;
                    fresh--;
                    queue.offer(new int[]{nr, nc});
                }
            }
            minutes++;
        }
        return fresh == 0 ? minutes : -1;
    }

    // Generic graph BFS/DFS skeleton using an adjacency list and visited set
    static void bfsTemplate(Map<Integer, List<Integer>> graph, int start) {
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        visited.add(start);
        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int nb : graph.getOrDefault(node, new ArrayList<>())) {
                if (visited.add(nb)) queue.offer(nb);
            }
        }
    }

    // =====================================================================
    // 9. HEAP / TOP-K
    // =====================================================================

    static int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();   // size-k MIN heap for k LARGEST
        for (int x : nums) {
            minHeap.offer(x);
            if (minHeap.size() > k) minHeap.poll();
        }
        return minHeap.peek();
    }

    static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int x : nums) count.merge(x, 1, Integer::sum);
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> count.get(a) - count.get(b));
        for (int key : count.keySet()) {
            pq.offer(key);
            if (pq.size() > k) pq.poll();
        }
        int[] res = new int[k];
        for (int i = k - 1; i >= 0; i--) res[i] = pq.poll();
        return res;
    }

    static int[][] kClosest(int[][] points, int k) {
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(
            (a, b) -> (b[0] * b[0] + b[1] * b[1]) - (a[0] * a[0] + a[1] * a[1]));
        for (int[] p : points) {
            maxHeap.offer(p);
            if (maxHeap.size() > k) maxHeap.poll();
        }
        return maxHeap.toArray(new int[0][]);
    }

    static ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> a.val - b.val);
        for (ListNode l : lists) if (l != null) pq.offer(l);
        ListNode dummy = new ListNode(0), tail = dummy;
        while (!pq.isEmpty()) {
            ListNode node = pq.poll();
            tail.next = node;
            tail = node;
            if (node.next != null) pq.offer(node.next);
        }
        return dummy.next;
    }

    // =====================================================================
    // 10. BACKTRACKING  (choose -> explore -> un-choose)
    // =====================================================================

    static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtrackSubsets(nums, 0, new ArrayList<>(), res);
        return res;
    }

    private static void backtrackSubsets(int[] nums, int start, List<Integer> path, List<List<Integer>> res) {
        res.add(new ArrayList<>(path));                           // copy the path!
        for (int i = start; i < nums.length; i++) {
            path.add(nums[i]);                                    // choose
            backtrackSubsets(nums, i + 1, path, res);             // explore
            path.remove(path.size() - 1);                         // un-choose
        }
    }

    static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtrackPermute(nums, new boolean[nums.length], new ArrayList<>(), res);
        return res;
    }

    private static void backtrackPermute(int[] nums, boolean[] used, List<Integer> path, List<List<Integer>> res) {
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;
            used[i] = true;
            path.add(nums[i]);
            backtrackPermute(nums, used, path, res);
            path.remove(path.size() - 1);
            used[i] = false;
        }
    }

    static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        backtrackCombo(candidates, target, 0, new ArrayList<>(), res);
        return res;
    }

    private static void backtrackCombo(int[] c, int remain, int start, List<Integer> path, List<List<Integer>> res) {
        if (remain == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        if (remain < 0) return;
        for (int i = start; i < c.length; i++) {
            path.add(c[i]);
            backtrackCombo(c, remain - c[i], i, path, res);       // i, not i+1: reuse allowed
            path.remove(path.size() - 1);
        }
    }

    static List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        genParens(n, 0, 0, new StringBuilder(), res);
        return res;
    }

    private static void genParens(int n, int open, int close, StringBuilder sb, List<String> res) {
        if (sb.length() == 2 * n) {
            res.add(sb.toString());
            return;
        }
        if (open < n) {
            sb.append('(');
            genParens(n, open + 1, close, sb, res);
            sb.deleteCharAt(sb.length() - 1);
        }
        if (close < open) {
            sb.append(')');
            genParens(n, open, close + 1, sb, res);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    static boolean exist(char[][] board, String word) {           // Word Search
        for (int r = 0; r < board.length; r++)
            for (int c = 0; c < board[0].length; c++)
                if (searchWord(board, word, r, c, 0)) return true;
        return false;
    }

    private static boolean searchWord(char[][] b, String w, int r, int c, int idx) {
        if (idx == w.length()) return true;
        if (r < 0 || c < 0 || r >= b.length || c >= b[0].length || b[r][c] != w.charAt(idx)) return false;
        char saved = b[r][c];
        b[r][c] = '#';                                            // mark visited
        boolean found = searchWord(b, w, r + 1, c, idx + 1) || searchWord(b, w, r - 1, c, idx + 1)
                     || searchWord(b, w, r, c + 1, idx + 1) || searchWord(b, w, r, c - 1, idx + 1);
        b[r][c] = saved;                                          // restore
        return found;
    }

    // =====================================================================
    // 11. DYNAMIC PROGRAMMING
    // =====================================================================

    // Top-down memoization template
    static Map<Integer, Long> memo = new HashMap<>();

    static long fibMemo(int n) {
        if (n <= 1) return n;
        if (memo.containsKey(n)) return memo.get(n);
        long val = fibMemo(n - 1) + fibMemo(n - 2);
        memo.put(n, val);
        return val;
    }

    static int climbStairs(int n) {
        if (n <= 2) return n;
        int a = 1, b = 2;
        for (int i = 3; i <= n; i++) {
            int c = a + b;
            a = b;
            b = c;
        }
        return b;
    }

    static int rob(int[] nums) {                                  // House Robber
        int prev = 0, curr = 0;
        for (int x : nums) {
            int next = Math.max(curr, prev + x);
            prev = curr;
            curr = next;
        }
        return curr;
    }

    static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);                              // "infinity"
        dp[0] = 0;
        for (int a = 1; a <= amount; a++) {
            for (int c : coins) {
                if (c <= a) dp[a] = Math.min(dp[a], dp[a - c] + 1);
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    static int lengthOfLIS(int[] nums) {                          // O(n^2)
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int best = 1;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) dp[i] = Math.max(dp[i], dp[j] + 1);
            }
            best = Math.max(best, dp[i]);
        }
        return best;
    }

    static int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int[] row : dp) Arrays.fill(row, 1);
        for (int i = 1; i < m; i++)
            for (int j = 1; j < n; j++)
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
        return dp[m - 1][n - 1];
    }

    static boolean wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && dict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    static int longestCommonSubsequence(String a, String b) {
        int[][] dp = new int[a.length() + 1][b.length() + 1];
        for (int i = 1; i <= a.length(); i++) {
            for (int j = 1; j <= b.length(); j++) {
                if (a.charAt(i - 1) == b.charAt(j - 1)) dp[i][j] = dp[i - 1][j - 1] + 1;
                else dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return dp[a.length()][b.length()];
    }

    static int numDecodings(String s) {
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = s.charAt(0) == '0' ? 0 : 1;
        for (int i = 2; i <= n; i++) {
            int one = s.charAt(i - 1) - '0';
            int two = Integer.parseInt(s.substring(i - 2, i));
            if (one >= 1) dp[i] += dp[i - 1];
            if (two >= 10 && two <= 26) dp[i] += dp[i - 2];
        }
        return dp[n];
    }

    // =====================================================================
    // 12. SMALLER BUT FREQUENT PATTERNS
    // =====================================================================

    static int subarraySum(int[] nums, int k) {                   // prefix sum + hash map
        Map<Integer, Integer> prefixCount = new HashMap<>();
        prefixCount.put(0, 1);
        int sum = 0, count = 0;
        for (int x : nums) {
            sum += x;
            count += prefixCount.getOrDefault(sum - k, 0);
            prefixCount.merge(sum, 1, Integer::sum);
        }
        return count;
    }

    static int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        res[0] = 1;
        for (int i = 1; i < n; i++) res[i] = res[i - 1] * nums[i - 1];   // prefix products
        int right = 1;
        for (int i = n - 1; i >= 0; i--) {                                // multiply suffix products
            res[i] *= right;
            right *= nums[i];
        }
        return res;
    }

    static int maxSubArray(int[] nums) {                          // Kadane's algorithm
        int cur = nums[0], best = nums[0];
        for (int i = 1; i < nums.length; i++) {
            cur = Math.max(nums[i], cur + nums[i]);
            best = Math.max(best, cur);
        }
        return best;
    }

    static int[][] mergeIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        List<int[]> merged = new ArrayList<>();
        for (int[] iv : intervals) {
            if (merged.isEmpty() || merged.get(merged.size() - 1)[1] < iv[0]) {
                merged.add(iv);
            } else {
                int[] last = merged.get(merged.size() - 1);
                last[1] = Math.max(last[1], iv[1]);
            }
        }
        return merged.toArray(new int[0][]);
    }

    static int singleNumber(int[] nums) {                         // XOR cancels pairs
        int x = 0;
        for (int n : nums) x ^= n;
        return x;
    }

    static int hammingWeight(int n) {                             // Brian Kernighan
        int count = 0;
        while (n != 0) {
            n &= (n - 1);                                         // drops the lowest set bit
            count++;
        }
        return count;
    }

    static void rotateImage(int[][] m) {                          // transpose, then reverse each row
        int n = m.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int t = m[i][j];
                m[i][j] = m[j][i];
                m[j][i] = t;
            }
        }
        for (int[] row : m) {
            for (int i = 0, j = n - 1; i < j; i++, j--) {
                int t = row[i];
                row[i] = row[j];
                row[j] = t;
            }
        }
    }

    // =====================================================================
    // MAIN: quick sanity checks
    // =====================================================================

    public static void main(String[] args) {
        System.out.println("twoSum              " + Arrays.toString(twoSum(new int[]{2, 7, 11, 15}, 9)));
        System.out.println("nextGreater         " + Arrays.toString(nextGreaterElements(new int[]{2, 1, 2, 4, 3})));
        System.out.println("dailyTemperatures   " + Arrays.toString(dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73})));
        System.out.println("longestSubstring    " + lengthOfLongestSubstring("abcabcbb"));
        System.out.println("isValid             " + isValid("({[]})"));
        System.out.println("searchRotated       " + searchRotated(new int[]{4, 5, 6, 7, 0, 1, 2}, 0));
        System.out.println("minEatingSpeed      " + minEatingSpeed(new int[]{3, 6, 7, 11}, 8));
        System.out.println("threeSum            " + threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
        System.out.println("subsets             " + subsets(new int[]{1, 2, 3}));
        System.out.println("generateParenthesis " + generateParenthesis(3));
        System.out.println("coinChange          " + coinChange(new int[]{1, 2, 5}, 11));
        System.out.println("LIS                 " + lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
        System.out.println("LCS                 " + longestCommonSubsequence("abcde", "ace"));
        System.out.println("maxSubArray         " + maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
        System.out.println("subarraySum         " + subarraySum(new int[]{1, 1, 1}, 2));
        System.out.println("kthLargest          " + findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 2));
        System.out.println("mergeIntervals      " + Arrays.deepToString(mergeIntervals(new int[][]{{1, 3}, {2, 6}, {8, 10}})));

        char[][] grid = {
            {'1', '1', '0', '0'},
            {'1', '1', '0', '0'},
            {'0', '0', '1', '0'},
            {'0', '0', '0', '1'}
        };
        System.out.println("numIslands          " + numIslands(grid));
        System.out.println("canFinish           " + canFinish(2, new int[][]{{1, 0}}));
    }
}
