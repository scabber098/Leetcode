import java.util.*;

class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> ans = new ArrayList<>();

        int n = s.length();
        int wordLen = words[0].length();
        int wordCount = words.length;
        int totalLen = wordLen * wordCount;

        if (n < totalLen) return ans;

        Map<String, Integer> id = new HashMap<>();
        int[] required = new int[words.length];
        int unique = 0;

        for (String word : words) {
            Integer index = id.get(word);

            if (index == null) {
                index = unique++;
                id.put(word, index);
            }

            required[index]++;
        }

        for (int offset = 0; offset < wordLen; offset++) {
            int left = offset;
            int count = 0;
            int[] current = new int[unique];

            for (int right = offset; right + wordLen <= n; right += wordLen) {
                String word = s.substring(right, right + wordLen);
                Integer index = id.get(word);

                if (index == null) {
                    Arrays.fill(current, 0);
                    count = 0;
                    left = right + wordLen;
                    continue;
                }

                current[index]++;
                count++;

                while (current[index] > required[index]) {
                    String remove = s.substring(left, left + wordLen);
                    int removeIndex = id.get(remove);

                    current[removeIndex]--;
                    count--;
                    left += wordLen;
                }

                if (count == wordCount) {
                    ans.add(left);

                    String remove = s.substring(left, left + wordLen);
                    int removeIndex = id.get(remove);

                    current[removeIndex]--;
                    count--;
                    left += wordLen;
                }
            }
        }

        return ans;
    }
}