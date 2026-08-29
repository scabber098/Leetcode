import java.util.*;

class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        int[] sorted = nums.clone();
        Arrays.sort(sorted);

        Map<Integer, ArrayDeque<Integer>> map = new HashMap<>();

        for (int i = 0; i < n;) {
            int j = i + 1;

            while (j < n && sorted[j] - sorted[j - 1] <= limit) {
                j++;
            }

            ArrayDeque<Integer> queue = new ArrayDeque<>();

            for (int k = i; k < j; k++) {
                queue.offer(sorted[k]);
            }

            for (int k = i; k < j; k++) {
                map.put(sorted[k], queue);
            }

            i = j;
        }

        for (int i = 0; i < n; i++) {
            nums[i] = map.get(nums[i]).poll();
        }

        return nums;
    }
}