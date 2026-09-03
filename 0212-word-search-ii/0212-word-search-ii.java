class Solution {

    static class TrieNode {
        TrieNode[] next = new TrieNode[26];
        int wordIndex = -1;
    }

    TrieNode root = new TrieNode();
    List<String> result = new ArrayList<>();
    String[] words;
    int rows, cols;

    public List<String> findWords(char[][] board, String[] words) {
        this.words = words;
        rows = board.length;
        cols = board[0].length;

        for (int i = 0; i < words.length; i++) {
            insert(words[i], i);
        }

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                dfs(board, r, c, root);
            }
        }

        return result;
    }

    private void insert(String word, int index) {
        TrieNode node = root;

        for (int i = 0; i < word.length(); i++) {
            int x = word.charAt(i) - 'a';

            if (node.next[x] == null) {
                node.next[x] = new TrieNode();
            }

            node = node.next[x];
        }

        node.wordIndex = index;
    }

    private void dfs(char[][] board, int r, int c, TrieNode parent) {

        char ch = board[r][c];

        if (ch == '#')
            return;

        TrieNode node = parent.next[ch - 'a'];

        if (node == null)
            return;

        if (node.wordIndex != -1) {
            result.add(words[node.wordIndex]);
            node.wordIndex = -1;
        }

        board[r][c] = '#';

        if (r > 0)
            dfs(board, r - 1, c, node);

        if (r + 1 < rows)
            dfs(board, r + 1, c, node);

        if (c > 0)
            dfs(board, r, c - 1, node);

        if (c + 1 < cols)
            dfs(board, r, c + 1, node);

        board[r][c] = ch;

        // Trie pruning
        if (node.wordIndex == -1 &&
            node.next[0] == null &&
            node.next[1] == null &&
            node.next[2] == null &&
            node.next[3] == null &&
            node.next[4] == null &&
            node.next[5] == null &&
            node.next[6] == null &&
            node.next[7] == null &&
            node.next[8] == null &&
            node.next[9] == null &&
            node.next[10] == null &&
            node.next[11] == null &&
            node.next[12] == null &&
            node.next[13] == null &&
            node.next[14] == null &&
            node.next[15] == null &&
            node.next[16] == null &&
            node.next[17] == null &&
            node.next[18] == null &&
            node.next[19] == null &&
            node.next[20] == null &&
            node.next[21] == null &&
            node.next[22] == null &&
            node.next[23] == null &&
            node.next[24] == null &&
            node.next[25] == null) {

            parent.next[ch - 'a'] = null;
        }
    }
}