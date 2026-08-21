import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public String removeKdigits(String num, int k) {
        if (k >= num.length()) return "0";

        Deque<Character> stack = new ArrayDeque<>();

        for (char digit : num.toCharArray()) {
            // Remove previous larger digits to keep the stack monotonic increasing
            while (!stack.isEmpty() && k > 0 && stack.peekLast() > digit) {
                stack.pollLast();
                k--;
            }
            stack.addLast(digit);
        }

        // If there are still digits left to remove, remove from the end
        while (k > 0 && !stack.isEmpty()) {
            stack.pollLast();
            k--;
        }

        // Build result and strip leading zeros
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            char c = stack.pollFirst();
            if (sb.length() == 0 && c == '0') continue; // Skip leading zeros
            sb.append(c);
        }

        return sb.length() == 0 ? "0" : sb.toString();
    }
}