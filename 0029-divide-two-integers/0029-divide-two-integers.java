class Solution {
    public int divide(int dividend, int divisor) {
        // Handle special overflow edge case
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        // Determine if the result will be negative
        boolean negative = (dividend < 0) ^ (divisor < 0);

        // Convert both numbers to negative to prevent 32-bit overflow
        int a = dividend < 0 ? dividend : -dividend;
        int b = divisor < 0 ? divisor : -divisor;

        int quotient = 0;

        // Since both a and b are negative, a <= b means |a| >= |b|
        while (a <= b) {
            int tempDivisor = b;
            int multiple = 1;

            // Double the divisor as long as it fits into 'a' without overflowing
            while (tempDivisor >= (Integer.MIN_VALUE >> 1) && a <= (tempDivisor << 1)) {
                tempDivisor <<= 1;
                multiple <<= 1;
            }

            a -= tempDivisor;
            quotient += multiple;
        }

        return negative ? -quotient : quotient;
    }
}