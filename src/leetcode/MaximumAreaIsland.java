package leetcode;

// https://leetcode.com/problems/max-area-of-island/

import java.util.*;

public class MaximumAreaIsland {

    public int maxAreaOfIsland(int[][] grid) {
        int area = 0;

        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    area = Math.max(area, bfs(i,j, grid));
                    // or
                    // area = Math.max(area, dfs(i,j, grid));
                }
            }
        }
        return area;
    }

    int bfs(int i, int j , int[][] grid) {
        int area = 1;
        int m = grid.length;
        int n = grid[0].length;

        Queue<int[]> que = new LinkedList<>();
        que.offer(new int[]{i,j});

        int dirs[][] = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
        grid[i][j] = 0;

        while(!que.isEmpty()) {
            int []src = que.poll();

            for(int []dir : dirs) {
                int x = src[0] + dir[0];
                int y = src[1] + dir[1];
                int []cell = new int[]{x,y};
                if (isValidCell(cell, m , n) && grid[x][y] == 1) {
                    que.offer(cell);
                    grid[x][y] = 0;
                    area++;
                }
            }
        }

        return area;
    }

    int dfs(int i, int j, int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        if (!isValidCell(new int[]{i,j}, m , n) || grid[i][j] == 0) {
            return 0;
        }

        int area = 1;

        int dirs[][] = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
        grid[i][j] = 0;

        for(int []dir : dirs) {
            int cell[] = new int[]{i + dir[0], j + dir[1]};
            area += dfs(cell[0], cell[1], grid);
        }

        return area;
    }

    boolean isValidCell(int cell[], int row, int col) {
        if (cell[0] < 0 || cell[0] >= row || cell[1] < 0 || cell[1] >= col) {
            return false;
        }
        return true;
    }
}