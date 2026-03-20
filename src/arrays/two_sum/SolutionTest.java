package arrays.two_sum;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SolutionTest {
    private final Solution solution = new Solution();

    @Test
    @DisplayName("Should find indices when target is sum of two numbers")
    void testStandardCase() {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] expected = {0, 1};
        assertArrayEquals(expected, solution.twoSum(nums, target));
    }

    @Test
    @DisplayName("Should handle negative numbers correctly")
    void testNegativeNumbers() {
        int[] nums = {-3, 4, 3, 90};
        int target = 0;
        int[] expected = {0, 2};
        assertArrayEquals(expected, solution.twoSum(nums, target));
    }

    @Test
    @DisplayName("Should throw exception when no solution exists")
    void testNoSolution() {
        int[] nums = {1, 2, 3};
        int target = 7;
        assertThrows(IllegalArgumentException.class, () -> solution.twoSum(nums, target));
    }
}
