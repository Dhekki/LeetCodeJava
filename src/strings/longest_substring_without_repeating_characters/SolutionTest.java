package strings.longest_substring_without_repeating_characters;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    private final Solution solution = new Solution();

    @Test
    @DisplayName("Should find length for standard string with repetitions")
    void testStandardCase() {
        assertEquals(3, solution.lengthOfLongestSubstring("abcabcbb")); // "abc"
    }

    @Test
    @DisplayName("Should return 1 for string with all identical characters")
    void testAllSameChars() {
        assertEquals(1, solution.lengthOfLongestSubstring("bbbbb")); // "b"
    }

    @Test
    @DisplayName("Should find length when longest substring is in the middle")
    void testMiddleSubstring() {
        assertEquals(3, solution.lengthOfLongestSubstring("pwwkew")); // "wke"
    }

    @Test
    @DisplayName("Should return 0 for empty string")
    void testEmptyString() {
        assertEquals(0, solution.lengthOfLongestSubstring(""));
    }

    @Test
    @DisplayName("Should handle single space character")
    void testSpace() {
        assertEquals(1, solution.lengthOfLongestSubstring(" "));
    }

    @Test
    @DisplayName("Should handle case where repetition is not at the start of current window")
    void testInterleavedRepetition() {
        assertEquals(3, solution.lengthOfLongestSubstring("dvdf")); // "vdf"
    }
}
