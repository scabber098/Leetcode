class Solution {

    class TrieNode {
        TrieNode[] child = new TrieNode[26];
        String word = "";
    }

    TrieNode root = new TrieNode();
    List<String> ans = new ArrayList<>();

    int m, n;

    void insert(String word) {
        TrieNode node = root;

        for (char c : word.toCharArray()) {
            int idx = c - 'a';

            if (node.child[idx] == null) {
                node.child[idx] = new TrieNode();
            }

            node = node.child[idx];
        }

        node.word = word;
    }

    void dfs(char[][] board, int r, int c, TrieNode node) {

        if (r < 0 || c < 0 || r >= m || c >= n)
            return;

        char ch = board[r][c];

        if (ch == '#')
            return;

        TrieNode next = node.child[ch - 'a'];

        if (next == null)
            return;

        if (!next.word.equals("")) {
            ans.add(next.word);
            next.word = "";
        }

        board[r][c] = '#';

        dfs(board, r + 1, c, next);
        dfs(board, r - 1, c, next);
        dfs(board, r, c + 1, next);
        dfs(board, r, c - 1, next);

        board[r][c] = ch;
    }

    public List<String> findWords(char[][] board, String[] words) {

        m = board.length;
        n = board[0].length;

        for (String word : words) {
            insert(word);
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(board, i, j, root);
            }
        }

        return ans;
    }
}