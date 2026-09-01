import java.util.*;

class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();

        int[][] litterIndex = new int[m][n];
        int startX = 0, startY = 0, count = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = classroom[i].charAt(j);

                if (c == 'S') {
                    startX = i;
                    startY = j;
                } else if (c == 'L') {
                    litterIndex[i][j] = count++;
                }
            }
        }

        if (count == 0) return 0;

        int lumetarkon = count;

        int fullMask = (1 << lumetarkon) - 1;

        boolean[][][][] visited =
                new boolean[m][n][energy + 1][1 << lumetarkon];

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startX, startY, energy, fullMask});

        visited[startX][startY][energy][fullMask] = true;

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        int moves = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size-- > 0) {
                int[] curr = queue.poll();

                int x = curr[0];
                int y = curr[1];
                int currentEnergy = curr[2];
                int mask = curr[3];

                if (mask == 0) return moves;

                if (currentEnergy == 0) continue;

                for (int d = 0; d < 4; d++) {
                    int nx = x + dx[d];
                    int ny = y + dy[d];

                    if (nx < 0 || nx >= m || ny < 0 || ny >= n)
                        continue;

                    char cell = classroom[nx].charAt(ny);

                    if (cell == 'X')
                        continue;

                    int nextEnergy = currentEnergy - 1;

                    if (cell == 'R')
                        nextEnergy = energy;

                    int nextMask = mask;

                    if (cell == 'L') {
                        nextMask &= ~(1 << litterIndex[nx][ny]);
                    }

                    if (!visited[nx][ny][nextEnergy][nextMask]) {
                        visited[nx][ny][nextEnergy][nextMask] = true;
                        queue.offer(new int[]{
                                nx, ny, nextEnergy, nextMask
                        });
                    }
                }
            }

            moves++;
        }

        return -1;
    }
}