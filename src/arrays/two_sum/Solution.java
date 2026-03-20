package arrays.two_sum;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> savedNumbers = new HashMap<>(nums.length);

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];

            if (savedNumbers.containsKey(complement))
                return new int[]{savedNumbers.get(complement), i};

            savedNumbers.put(nums[i], i);
        }
        throw new IllegalArgumentException("No solution found");
    }
}
