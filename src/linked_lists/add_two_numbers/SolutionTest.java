package linked_lists.add_two_numbers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    private final Solution solution = new Solution();

    @Test
    @DisplayName("Should add two numbers correctly (Standard Case)")
    void testStandardCase() {
        ListNode l1 = arrayToList(new int[]{2, 4, 3});
        ListNode l2 = arrayToList(new int[]{5, 6, 4});
        int[] expected = {7, 0, 8};

        assertArrayEquals(expected, listToArray(solution.addTwoNumbers(l1, l2)));
    }

    @Test
    @DisplayName("Should handle different list lengths and carries")
    void testDifferentLengths() {
        ListNode l1 = arrayToList(new int[]{9, 9, 9, 9, 9, 9, 9});
        ListNode l2 = arrayToList(new int[]{9, 9, 9, 9});
        int[] expected = {8, 9, 9, 9, 0, 0, 0, 1};

        assertArrayEquals(expected, listToArray(solution.addTwoNumbers(l1, l2)));
    }

    @Test
    @DisplayName("Should add zero values")
    void testZeros() {
        ListNode l1 = arrayToList(new int[]{0});
        ListNode l2 = arrayToList(new int[]{0});
        int[] expected = {0};

        assertArrayEquals(expected, listToArray(solution.addTwoNumbers(l1, l2)));
    }

    // Helper: Converts int[] to ListNode
    private ListNode arrayToList(int[] nums) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        for (int num : nums) {
            curr.next = new ListNode(num);
            curr = curr.next;
        }
        return dummy.next;
    }

    // Helper: Converts ListNode to int[] for easy assertion
    private int[] listToArray(ListNode node) {
        java.util.List<Integer> res = new java.util.ArrayList<>();
        while (node != null) {
            res.add(node.val);
            node = node.next;
        }
        return res.stream().mapToInt(i -> i).toArray();
    }
}
