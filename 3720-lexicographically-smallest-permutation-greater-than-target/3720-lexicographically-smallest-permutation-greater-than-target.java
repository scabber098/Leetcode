class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int[] cnt = new int[26];

        // Count characters in s
        for (char c : s.toCharArray()) {
            cnt[c - 'a']++;
        }

        int[] temp = cnt.clone();
        int position = -1;

        // Find the rightmost position where we can make
        // the string greater than target
        for (int i = 0; i < target.length(); i++) {
            int current = target.charAt(i) - 'a';

            // Check if there is any character > target[i]
            for (int j = current + 1; j < 26; j++) {
                if (temp[j] > 0) {
                    position = i;
                    break;
                }
            }

            // Cannot continue matching target
            if (temp[current] == 0) {
                break;
            }

            temp[current]--;
        }

        if (position == -1) {
            return "";
        }

        StringBuilder ans = new StringBuilder();

        // Match target before position
        for (int i = 0; i < position; i++) {
            char c = target.charAt(i);
            ans.append(c);
            cnt[c - 'a']--;
        }

        // Find smallest available character > target[position]
        int current = target.charAt(position) - 'a';

        for (int j = current + 1; j < 26; j++) {
            if (cnt[j] > 0) {
                ans.append((char) ('a' + j));
                cnt[j]--;
                break;
            }
        }

        // Add remaining characters in sorted order
        for (int i = 0; i < 26; i++) {
            while (cnt[i] > 0) {
                ans.append((char) ('a' + i));
                cnt[i]--;
            }
        }

        return ans.toString();
    }
}