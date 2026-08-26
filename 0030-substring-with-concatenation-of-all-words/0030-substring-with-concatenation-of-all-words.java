class Solution {
    public List<Integer> findSubstring(String s, String[] words) {

        List<Integer> ans = new ArrayList<>();

        int wordLen = words[0].length();
        int wordCount = words.length;
        int totalLen = wordLen * wordCount;

        // Required frequency of each word
        HashMap<String, Integer> required = new HashMap<>();

        for (String word : words) {
            required.put(word, required.getOrDefault(word, 0) + 1);
        }

        // Try every possible alignment
        for (int offset = 0; offset < wordLen; offset++) {

            int left = offset;
            int right = offset;
            int count = 0;

            HashMap<String, Integer> current = new HashMap<>();

            while (right + wordLen <= s.length()) {

                String word = s.substring(right, right + wordLen);
                right += wordLen;

                // Word doesn't exist
                if (!required.containsKey(word)) {
                    current.clear();
                    count = 0;
                    left = right;
                    continue;
                }

                // Add word to window
                current.put(word, current.getOrDefault(word, 0) + 1);
                count++;

                // Too many copies of this word
                while (current.get(word) > required.get(word)) {

                    String remove = s.substring(left, left + wordLen);

                    current.put(remove, current.get(remove) - 1);
                    left += wordLen;
                    count--;
                }

                // All words are present
                if (count == wordCount) {
                    ans.add(left);

                    // Move forward to search for next answer
                    String remove = s.substring(left, left + wordLen);
                    current.put(remove, current.get(remove) - 1);
                    left += wordLen;
                    count--;
                }
            }
        }

        return ans;
    }
}