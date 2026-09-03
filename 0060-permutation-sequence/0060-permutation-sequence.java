class Solution {
    public String getPermutation(int n, int k) {
        List<Integer> nums = new ArrayList<>();
        int fact = 1;

        for (int i = 1; i <= n; i++) {
            nums.add(i);
            fact *= i;
        }

        k--;

        StringBuilder ans = new StringBuilder();

        for (int i = n; i >= 1; i--) {
            fact /= i;
            int index = k / fact;
            ans.append(nums.get(index));
            nums.remove(index);
            k %= fact;
        }

        return ans.toString();
    }
}