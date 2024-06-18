package arrays;

// https://leetcode.com/problems/container-with-most-water/description/

/*
You are given an integer array height of length n. There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).

Find two lines that together with the x-axis form a container, such that the container contains the most water.

Return the maximum amount of water a container can store.

    Input: height = [1,8,6,2,5,4,8,3,7]
    Output: 49
    Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7].
    In this case, the max area of water (blue section) the container can contain is 49 => (idx:8 - idx:1) * min(arr[8]:8, arr[1]:7)

 */

class ContainerWithMostWater {
    // two pointer to hold height's
    int maxArea(int height[]) {
        int maxArea = 0;
        int l = 0, r = height.length - 1;
        while (l < r) {
            int currArea = (r - l) * Math.min(height[l], height[r]);
            if (maxArea < currArea) {
                maxArea = currArea;
            }
            if (height[l] <= height[r]) {
                l++;
            } else {
                r--;
            }
        }
        return maxArea;
    }
}
