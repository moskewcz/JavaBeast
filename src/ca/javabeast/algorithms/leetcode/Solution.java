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
import java.util.LinkedList;
import java.util.List;
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

    public String intToRoman(int num) {
        String M[] = {"", "M", "MM", "MMM"};
        String C[] = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String X[] = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String I[] = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        return M[num / 1000] + C[(num % 1000) / 100] + X[(num % 100) / 10] + I[num % 10];
    }

    public static int romanToInt(String s) {
        int res = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            switch (c) {
                case 'I':
                    res += (res >= 5 ? -1 : 1);
                    break;
                case 'V':
                    res += 5;
                    break;
                case 'X':
                    res += 10 * (res >= 50 ? -1 : 1);
                    break;
                case 'L':
                    res += 50;
                    break;
                case 'C':
                    res += 100 * (res >= 500 ? -1 : 1);
                    break;
                case 'D':
                    res += 500;
                    break;
                case 'M':
                    res += 1000;
                    break;
                default:
                    break;
            }
        }
        return res;
    }

    public int romanToInt2(String str) {
        int[] a = new int[26];
        a['I' - 'A'] = 1;
        a['V' - 'A'] = 5;
        a['X' - 'A'] = 10;
        a['L' - 'A'] = 50;
        a['C' - 'A'] = 100;
        a['D' - 'A'] = 500;
        a['M' - 'A'] = 1000;
        char prev = 'A';
        int sum = 0;
        for (char s : str.toCharArray()) {
            if (a[s - 'A'] > a[prev - 'A']) {
                sum = sum - 2 * a[prev - 'A'];
            }
            sum = sum + a[s - 'A'];
            prev = s;
        }
        return sum;
    }

    //60. Permutation Sequence
    public String getPermutation(int n, int k) {
        List<Integer> numbers = new LinkedList<>();
        int[] factorial = new int[n + 1];
        StringBuilder sb = new StringBuilder();

        // create an array of factorial lookup
        factorial[0] = 1;
        for (int i = 1, sum = 1; i <= n; i++) {
            sum *= i;
            factorial[i] = sum;
            numbers.add(i);
        }

        k--;
        for (int i = 1; i <= n; i++) {
            int index = k / factorial[n - i];
            sb.append(numbers.get(index));
            numbers.remove(index);
            k = k % factorial[n - i];
        }

        return sb.toString();
    }
}
