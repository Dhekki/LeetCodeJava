package strings.longest_substring_without_repeating_characters;

public class Solution {
    public int lengthOfLongestSubstring(String s) {
        boolean[] seen = new boolean[256];
        int left = 0, maxSize = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);

            while (seen[c]) {
                seen[s.charAt(left)] = false;
                left++;
            }

            seen[c] = true;
            maxSize = Math.max(maxSize, right - left + 1);
        }
        return maxSize;
    }
}
