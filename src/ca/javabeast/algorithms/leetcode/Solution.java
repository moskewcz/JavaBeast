/*
 * Copyright 2015 alpenliebe <alpseinstein@gmail.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ca.javabeast.algorithms.leetcode;

import java.util.HashMap;
import java.util.Stack;

/**
 *
 * @author alpenliebe <alpseinstein@gmail.com>
 */
public class Solution {

    public static void main(String[] args) {
        LeetCode lc = new LeetCode();
        LeetCode.print(lc.longestValidParentheses("()(()"));
        LeetCode.print(lc.bulbSwitch(100));
    }

}

class LeetCode {

    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        int left = 0;
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {  //push '(' set v=0
                stack.push(i);
            } else {
                if (stack.isEmpty()) {
                    //add the left char directly, as this char will never be matched
                    left = i + 1;
                } else {
                    stack.pop();
                    if (stack.isEmpty()) {
                        max = Math.max(max, i - left + 1);
                    } else {
                        max = Math.max(max, i - stack.peek());
                    }
                }
            }
            //print("left:", left);
            //print("max:", max);

        }
        return max;
    }

    public int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        int max = 0;
        //record the start point
        for (int i = 0, j = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            if (map.containsKey(c)) {  //encounter a repeat character
                j = Math.max(j, map.get(c) + 1);
            }
            map.put(c, i);
            max = Math.max(max, i - j + 1);
        }
        return max;
    }

    public void Rotate2DMatrix(int[][] matrix) {
        int n = matrix.length;
        int[] temp = new int[4];
        for (int i = 0, j = 0; i < n / 2; i++, j++) { //only start to rotate the node on the diagonal 
            int e = n - 2 * i; //get length of the edge
            for (int k = 0; e > 1 && k < e - 1; k++) {  //Rotate every node on the edge 
                temp[1] = matrix[i][j + k];
                temp[2] = matrix[i + k][j + e - 1];
                temp[3] = matrix[i + e - 1][j + e - 1 - k];
                temp[0] = matrix[i + e - 1 - k][j];
                matrix[i][j + k] = temp[0];
                matrix[i + k][j + e - 1] = temp[1];
                matrix[i + e - 1][j + e - 1 - k] = temp[2];
                matrix[i + e - 1 - k][j] = temp[3];
            }

        }
    }

    public static String ezFormat(Object... args) {
        String format = new String(new char[args.length])
                .replace("\0", "%s");
        return String.format(format, args);
    }

    public static void print(Object... args) {
        System.out.println(ezFormat(args));
    }

    public int bulbSwitch(int n) {
        return (int) Math.sqrt(n);
    }
}
