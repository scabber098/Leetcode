class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> ans = new ArrayList<>();
        int i = 0;

        while (i < words.length) {
            int j = i;
            int letters = 0;

            // Find words that fit in this line
            while (j < words.length &&
                   letters + words[j].length() + (j - i) <= maxWidth) {
                letters += words[j].length();
                j++;
            }

            int gaps = j - i - 1;
            StringBuilder line = new StringBuilder();

            // Last line or line with one word
            if (j == words.length || gaps == 0) {
                for (int k = i; k < j; k++) {
                    if (k > i) line.append(" ");
                    line.append(words[k]);
                }

                while (line.length() < maxWidth)
                    line.append(" ");
            } 
            else {
                int spaces = maxWidth - letters;
                int each = spaces / gaps;
                int extra = spaces % gaps;

                for (int k = i; k < j; k++) {
                    line.append(words[k]);

                    if (k < j - 1) {
                        for (int s = 0; s < each; s++)
                            line.append(" ");

                        if (extra > 0) {
                            line.append(" ");
                            extra--;
                        }
                    }
                }
            }

            ans.add(line.toString());
            i = j;
        }

        return ans;
    }
}