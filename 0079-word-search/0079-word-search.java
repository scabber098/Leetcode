class Solution {
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(board, word, i, j, 0)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean dfs(char[][] board, String word, int i, int j, int k) {

        // Out of bounds
        if (i < 0 || i >= board.length ||
            j < 0 || j >= board[0].length) {
            return false;
        }

        // Character doesn't match
        if (board[i][j] != word.charAt(k)) {
            return false;
        }

        // Last character matched
        if (k == word.length() - 1) {
            return true;
        }

        // Mark as visited
        char temp = board[i][j];
        board[i][j] = '#';

        // Search 4 directions
        boolean found =
            dfs(board, word, i + 1, j, k + 1) ||
            dfs(board, word, i - 1, j, k + 1) ||
            dfs(board, word, i, j + 1, k + 1) ||
            dfs(board, word, i, j - 1, k + 1);

        // Backtrack
        board[i][j] = temp;

        return found;
    }
}