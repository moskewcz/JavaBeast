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

import java.util.ArrayList;
import java.util.Arrays;
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

    //32. Longest Valid Parentheses
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

    //3. Longest Substring Without Repeating Characters
    public int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
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

    //48. Rotate Image
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
    
    //237. Delete Node in a Linked List
    public void deleteNode(ListNode node) {
        ListNode cur=node.next;
        while(cur.next !=null){
            node.val = cur.val;
            node=cur;
            cur=cur.next;
        }
        node.val = cur.val;
        node.next=null;
    }

    //319. Bulb Switcher
    public int bulbSwitch(int n) {
        return (int) Math.sqrt(n);
    }

    //12. Integer to Roman
    public String intToRoman(int num) {
        String M[] = {"", "M", "MM", "MMM"};
        String C[] = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String X[] = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String I[] = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        return M[num / 1000] + C[(num % 1000) / 100] + X[(num % 100) / 10] + I[num % 10];
    }

    //13. Roman to Integer
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

    public String add(String num1, String num2) {
        if (num1.length() < num2.length()) {
            String temp = num1;
            num1 = num2;
            num2 = temp;
        }
        int len1 = num1.length();
        int len2 = num2.length();
        char[] arr1 = num1.toCharArray();
        char[] arr2 = num2.toCharArray();
        boolean flag = false;
        for (int i = 0, i1 = 0, i2 = 0, temp = 0; i < len1; i++) {
            i1 = arr1[len1 - i - 1] - '0';
            i2 = i < len2 ? arr2[len2 - i - 1] - '0' : 0;
            temp = i1 + i2 + (flag ? 1 : 0);
            if (temp >= 10) {
                flag = true;
                temp -= 10;
            } else {
                flag = false;
            }
            //System.out.println(i2);
            arr1[len1 - i - 1] = (char) (temp + '0');
        }
        String res = new String(arr1);
        if (flag) {
            res = 1 + res;
        }
        return res;
    }

    //43 Multiply Strings
    public String multiply(String num1, String num2) {

        int len1 = num1.length(), len2 = num2.length();
        int p1 = 0, p2 = 0;
        int m = 0;
        int[] arr = new int[len1 + len2];
        for (int i = len1 - 1; i >= 0; i--) {
            for (int j = len2 - 1; j >= 0; j--) {
                m = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                p1 = i + j;
                p2 = i + j + 1;
                arr[p2] += m;
                arr[p1] += arr[p2] / 10;
                arr[p2] = arr[p2] % 10;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int p : arr) {
            if (!(sb.length() == 0 && p == 0)) {
                sb.append(p);
            }
        }

        return sb.length() == 0 ? "0" : sb.toString();
    }

    //67. Add Binary
    public String addBinary(String a, String b) {
        if (a.length() < b.length()) {
            String temp = a;
            a = b;
            b = temp;
        }

        int len1 = a.length(),
                len2 = b.length(),
                c = 0,
                sum = 0;
        char[] arr = a.toCharArray();
        for (int i = 0; i < len2 || (c != 0 && i < len1); i++) {
            if (i < len2) {
                sum = (a.charAt(len1 - i - 1) - '0') + (b.charAt(len2 - i - 1) - '0') + c;
            } else {
                sum = (a.charAt(len1 - i - 1) - '0') + c;
            }
            arr[len1 - i - 1] = (char) (sum % 2 + '0');
            c = sum / 2;
        }

        String s = new String(arr);
        return c > 0 ? "1" + s : s;
    }

    //2. Add Two Numbers
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        int carry = 0;
        ListNode head = new ListNode(0);
        ListNode cur = head;
        int i1 = 0, i2 = 0;
        //iterate over l1 and l2;
        while (carry != 0 || (l1 != null && l2 != null)) {
            i1 = l1 != null ? l1.val : 0;
            i2 = l2 != null ? l2.val : 0;
            cur.next = new ListNode((i1 + i2 + carry) % 10);
            cur = cur.next;

            carry = (i1 + i2 + carry) / 10;
            l1 = l1 != null ? l1.next : null;
            l2 = l2 != null ? l2.next : null;

        }
        if (l1 == null) {
            cur.next = l2;
        } else if (l2 == null) {
            cur.next = l1;
        }

        return head.next;
    }
    class ListNode {

        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
    
    //66. Plus One
    public int[] plusOne(int[] digits) {
         int c = 0, sum = 0, len = digits.length;
         sum = digits[len-1] + 1;
         c = sum/10;
         digits[len-1] = sum%10;
         
         for(int i = len - 2; c!=0&&i>=0; i--){
             sum = digits[i] + c;
             c = sum/10;
             digits[i] = sum%10;
         }
         
         int [] res =digits;
         if( c > 0) {
             res = new int[len+1];
             res[0] = c;
             for(int i = 0; i < len; i++){
                 res[i+1] = digits[i];
             }
         }
         
         return res;
    }
    
    
    //39. Combination Sum
    public List<List<Integer>> combinationSum(int[] cand, int target) {
        Arrays.sort(cand);
        List<List<Integer>> res = new LinkedList<>();
        List<Integer> path = new ArrayList<>();
        dfs_com(cand, 0, target, path, res);
        return res;
    }
    void dfs_com(int[] cand, int cur, int target, List<Integer> path, List<List<Integer>> res) {
        if (target > 0) {
            for (int i = cur; i < cand.length; i++){
                if (i > cur && cand[i] == cand[i-1]) continue;
                if(target-cand[i]<0) break;
                path.add(cand[i]);
                dfs_com(cand, i, target - cand[i], path, res);
                path.remove(path.size()-1);
            }
        } else if(target == 0){
            res.add(new ArrayList(path));
        }
    }
    
    //40. Combination Sum II
    public List<List<Integer>> combinationSum2(int[] cand, int target) {
        Arrays.sort(cand);
        List<List<Integer>> res = new LinkedList<>();
        List<Integer> path = new ArrayList<>();
        dfs_com2(cand, 0, target, path, res);
        return res;
    }
    void dfs_com2(int[] cand, int cur, int target, List<Integer> path, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList(path));
            return ;
        }
        if (target < 0) return;
        for (int i = cur; i < cand.length; i++){
            if (i > cur && cand[i] == cand[i-1]) continue;
            if(target-cand[i]<0) break;
            path.add(cand[i]);
            dfs_com(cand, i+1, target - cand[i], path, res);
            path.remove(path.size()-1);
        }
    }
}
