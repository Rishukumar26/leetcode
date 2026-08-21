import java.util.*;

class Solution {
    public List<String> removeInvalidParentheses(String s) {
        int leftRem = 0, rightRem = 0;

        // Step 1: Calculate the minimum number of '(' and ')' to remove
        for (char c : s.toCharArray()) {
            if (c == '(') {
                leftRem++;
            } else if (c == ')') {
                if (leftRem > 0) {
                    leftRem--;
                } else {
                    rightRem++;
                }
            }
        }

        Set<String> result = new HashSet<>();
        backtrack(s, 0, 0, 0, leftRem, rightRem, new StringBuilder(), result);
        return new ArrayList<>(result);
    }

    private void backtrack(String s, int index, int openCount, int closeCount, 
                           int leftRem, int rightRem, StringBuilder expression, Set<String> result) {
        // Base Case: Reached the end of the string
        if (index == s.length()) {
            if (leftRem == 0 && rightRem == 0) {
                result.add(expression.toString());
            }
            return;
        }

        char currentChar = s.charAt(index);
        int length = expression.length();

        // Option 1: Skip (remove) the current character if it's a misplaced bracket
        if (currentChar == '(' && leftRem > 0) {
            backtrack(s, index + 1, openCount, closeCount, leftRem - 1, rightRem, expression, result);
        } else if (currentChar == ')' && rightRem > 0) {
            backtrack(s, index + 1, openCount, closeCount, leftRem, rightRem - 1, expression, result);
        }

        // Option 2: Keep the current character
        expression.append(currentChar);

        if (currentChar != '(' && currentChar != ')') {
            // Non-parenthesis characters are always kept
            backtrack(s, index + 1, openCount, closeCount, leftRem, rightRem, expression, result);
        } else if (currentChar == '(') {
            backtrack(s, index + 1, openCount + 1, closeCount, leftRem, rightRem, expression, result);
        } else if (currentChar == ')' && openCount > closeCount) {
            // Only add ')' if there is a matching '(' prior to it
            backtrack(s, index + 1, openCount, closeCount + 1, leftRem, rightRem, expression, result);
        }

        // Backtrack (undo choice)
        expression.setLength(length);
    }
}