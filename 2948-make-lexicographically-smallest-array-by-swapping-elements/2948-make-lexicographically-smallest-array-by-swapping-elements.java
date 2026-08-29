import java.util.*;

class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;

        int[] sorted = nums.clone();
        Arrays.sort(sorted);

        Map<Integer, List<Integer>> groups = new HashMap<>();
        Map<Integer, Integer> groupMap = new HashMap<>();

        int group = 0;
        groups.put(group, new ArrayList<>());

        groups.get(group).add(sorted[0]);
        groupMap.put(sorted[0], group);

        for (int i = 1; i < n; i++) {
            if (sorted[i] - sorted[i - 1] > limit) {
                group++;
                groups.put(group, new ArrayList<>());
            }

            groups.get(group).add(sorted[i]);
            groupMap.put(sorted[i], group);
        }

        Map<Integer, Queue<Integer>> values = new HashMap<>();

        for (int key : groups.keySet()) {
            values.put(key, new LinkedList<>(groups.get(key)));
        }

        int[] result = new int[n];

        for (int i = 0; i < n; i++) {
            int currentGroup = groupMap.get(nums[i]);
            result[i] = values.get(currentGroup).poll();
        }

        return result;
    }
}