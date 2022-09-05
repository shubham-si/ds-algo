package leetcode;

import java.util.*;

public class ShortestPathGridBfs {
    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;

        if (grid[0][0] == 1 || grid[n - 1][n - 1] == 1) {
            return -1;
        }

        boolean visited[][] = new boolean[n][n];

        int dir[][] = {{0, -1}, {0, +1}, {-1, 0}, {+1, 0}, {-1, -1}, {+1, +1}, {-1, +1}, {+1, -1}};
        Queue<Cell> que = new LinkedList<>();

        que.offer(new Cell(0, 0, 1, null));
        visited[0][0] = true;

        while (!que.isEmpty()) {
            Cell c = que.poll();

            if (grid[c.x][c.y] == 1) {
                continue;
            }

            if (c.x == (n - 1) && c.y == (n - 1)) {
                return c.dist;
            }

            // move in 8-directions
            for (int k[] : dir) {
                if (isValidCell(c.x + k[0], c.y + k[1], n) && !visited[c.x + k[0]][c.y + k[1]]) {
                    que.offer(new Cell(c.x + k[0], c.y + k[1], c.dist + 1, c));
                    visited[c.x + k[0]][c.y + k[1]] = true;
                }
            }
        }

        return -1;
    }

    public boolean isValidCell(int x, int y, int n) {
        if (x < 0 || y < 0 || x >= n || y >= n) {
            return false;
        }
        return true;
    }

}

class Cell {
    int x, y, dist;
    Cell parent;

    Cell(int x, int y, int dist, Cell parent) {
        this.x = x;
        this.y = y;
        this.dist = dist;
        this.parent = parent;
    }
}