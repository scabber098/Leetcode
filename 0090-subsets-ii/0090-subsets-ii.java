class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);

        List<List<Integer>> ans = new ArrayList<>();
        backtrack(nums, 0, new ArrayList<>(), ans);

        return ans;
    }

    void backtrack(int[] nums, int start, List<Integer> current,
                   List<List<Integer>> ans) {

        ans.add(new ArrayList<>(current));

        for (int i = start; i < nums.length; i++) {

            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }

            current.add(nums[i]);

            backtrack(nums, i + 1, current, ans);

            current.remove(current.size() - 1);
        }
    }
}