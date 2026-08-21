import java.util.Stack;

class Solution {
    public int calculate(String s) {
        int result = 0;
        int currentNumber = 0;
        int sign = 1; // 1 for '+', -1 for '-'
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (Character.isDigit(c)) {
                // Construct multi-digit numbers
                currentNumber = currentNumber * 10 + (c - '0');
            } else if (c == '+') {
                result += sign * currentNumber;
                currentNumber = 0;
                sign = 1;
            } else if (c == '-') {
                result += sign * currentNumber;
                currentNumber = 0;
                sign = -1;
            } else if (c == '(') {
                // Save current sub-result and sign, then reset scope
                stack.push(result);
                stack.push(sign);
                result = 0;
                sign = 1;
            } else if (c == ')') {
                result += sign * currentNumber;
                currentNumber = 0;
                
                // Apply the sign preceding '(' and add the result prior to '('
                result *= stack.pop(); // Pop saved sign
                result += stack.pop(); // Pop saved result
            }
        }

        // Add any remaining number at the end of the string
        if (currentNumber != 0) {
            result += sign * currentNumber;
        }

        return result;
    }
}