class Solution {
    public String getHint(String secret, String guess) {
        int bulls = 0;
        int cows = 0;
        int[] counts = new int[10];

        for (int i = 0; i < secret.length(); i++) {
            int s = secret.charAt(i) - '0';
            int g = guess.charAt(i) - '0';

            if (s == g) {
                bulls++;
            } else {
                // If s was previously seen in guess, it contributes to cows
                if (counts[s] < 0) cows++;
                // If g was previously seen in secret, it contributes to cows
                if (counts[g] > 0) cows++;

                counts[s]++; // secret digit increases count
                counts[g]--; // guess digit decreases count
            }
        }

        return bulls + "A" + cows + "B";
    }
}