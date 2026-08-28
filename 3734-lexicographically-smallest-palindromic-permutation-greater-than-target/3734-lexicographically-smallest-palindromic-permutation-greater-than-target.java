class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int[] count = new int[26];

        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        int odd = 0;
        char middle = 0;

        for (int i = 0; i < 26; i++) {
            if (count[i] % 2 == 1) {
                odd++;
                middle = (char) ('a' + i);
            }
        }

        if (odd > 1) return "";

        int[] half = new int[26];
        for (int i = 0; i < 26; i++) {
            half[i] = count[i] / 2;
        }

        StringBuilder left = new StringBuilder();
        int halfLength = s.length() / 2;

        for (int pos = 0; pos < halfLength; pos++) {
            boolean found = false;

            for (int c = 0; c < 26; c++) {
                if (half[c] == 0) continue;

                half[c]--;
                left.append((char) ('a' + c));

                String candidate = buildLargest(left, half, middle, s.length());

                if (candidate.compareTo(target) > 0) {
                    found = true;
                    break;
                }

                left.deleteCharAt(left.length() - 1);
                half[c]++;
            }

            if (!found) return "";
        }

        StringBuilder answer = new StringBuilder(left);

        if (s.length() % 2 == 1) {
            answer.append(middle);
        }

        answer.append(left.reverse());

        String result = answer.toString();

        return result.compareTo(target) > 0 ? result : "";
    }

    private String buildLargest(StringBuilder prefix, int[] half,
                                char middle, int n) {
        StringBuilder left = new StringBuilder(prefix);

        for (int c = 25; c >= 0; c--) {
            for (int k = 0; k < half[c]; k++) {
                left.append((char) ('a' + c));
            }
        }

        StringBuilder result = new StringBuilder(left);

        if (n % 2 == 1) {
            result.append(middle);
        }

        result.append(left.reverse());

        return result.toString();
    }
}