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

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
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

        //String s = "boo:and:foo";
        //System.out.println(Arrays.asList(s.split("o")));
        System.out.println(lc.reverseWords(" "));
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
        ListNode cur = node.next;
        while (cur.next != null) {
            node.val = cur.val;
            node = cur;
            cur = cur.next;
        }
        node.val = cur.val;
        node.next = null;
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

    private class RandomListNode {

        int label;
        RandomListNode next, random;

        public RandomListNode(int x) {
            label = x;
        }
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
        sum = digits[len - 1] + 1;
        c = sum / 10;
        digits[len - 1] = sum % 10;

        for (int i = len - 2; c != 0 && i >= 0; i--) {
            sum = digits[i] + c;
            c = sum / 10;
            digits[i] = sum % 10;
        }

        int[] res = digits;
        if (c > 0) {
            res = new int[len + 1];
            res[0] = c;
            for (int i = 0; i < len; i++) {
                res[i + 1] = digits[i];
            }
        }

        return res;
    }

    //39. Combination Sum
    public List<List<Integer>> combinationSum(int[] cand, int target) {
        Arrays.sort(cand);
        List<List<Integer>> res = new LinkedList<>();
        List<Integer> path = new ArrayList<>();
        bt_com(cand, 0, target, path, res);
        return res;
    }

    void bt_com(int[] cand, int cur, int target, List<Integer> path, List<List<Integer>> res) {
        if (target > 0) {
            for (int i = cur; i < cand.length; i++) {
                if (i > cur && cand[i] == cand[i - 1]) {
                    continue;
                }
                if (target - cand[i] < 0) {
                    break;
                }
                path.add(cand[i]);
                bt_com(cand, i, target - cand[i], path, res);
                path.remove(path.size() - 1);
            }
        } else if (target == 0) {
            res.add(new ArrayList(path));
        }
    }

    //40. Combination Sum II
    public List<List<Integer>> combinationSum2(int[] cand, int target) {
        Arrays.sort(cand);
        List<List<Integer>> res = new LinkedList<>();
        List<Integer> path = new ArrayList<>();
        bt_com2(cand, 0, target, path, res);
        return res;
    }

    void bt_com2(int[] cand, int cur, int target, List<Integer> path, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList(path));
            return;
        }
        if (target < 0) {
            return;
        }
        for (int i = cur; i < cand.length; i++) {
            if (i > cur && cand[i] == cand[i - 1]) {
                continue;
            }
            if (target - cand[i] < 0) {
                break;
            }
            path.add(cand[i]);
            bt_com2(cand, i + 1, target - cand[i], path, res);
            path.remove(path.size() - 1);
        }
    }

    //77. Combinations
    public List<List<Integer>> combine(int n, int k) {
        List<Integer> path = new ArrayList<>();
        List<List<Integer>> res = new LinkedList<>();
        bt_com(n, 1, k, path, res);
        return res;
    }

    void bt_com(int n, int cur, int k, List<Integer> path, List<List<Integer>> res) {
        if (k > 0) {
            for (int i = cur; i < n + 1; i++) {
                path.add(i);
                bt_com(n, i + 1, k - 1, path, res);
                path.remove(path.size() - 1);
            }
        } else if (k == 0) {
            res.add(new ArrayList(path));
        }
    }

//    public List<List<Integer>> combine(int n, int k) {
//        if (n == 0 || k == 0
//                || k > n) {
//            return Collections.emptyList();
//        }
//        List<List<Integer>> res
//                = new ArrayList<>();
//        for (int i = 1; i <= n + 1 - k; i++) {
//            res.add(Arrays.asList(i));
//        }
//        for (int i = 2; i <= k; i++) {
//            List<List<Integer>> tmp = new ArrayList<>();
//            for (List<Integer> list
//                    : res) {
//                for (int m = list.get(list.size() - 1) + 1; m <= n - (k - i); m++) {
//                    List<Integer> newList = new ArrayList<>(list);
//                    newList.add(m);
//                    tmp.add(newList);
//                }
//            }
//            res = tmp;
//        }
//        return res;
//    }
    //46. Permutations
    public List<List<Integer>> permute(int[] num) {
        List<List<Integer>> result = new LinkedList<>();
        doPermute(result, num, 0);
        return result;
    }

    private void doPermute(List<List<Integer>> result, int[] array, int start) {
        if (start >= array.length) {
            List<Integer> current = new ArrayList<>();
            for (int a : array) {
                current.add(a);
            }
            result.add(current);
        } else {
            for (int i = start; i < array.length; i++) {
                swap(array, start, i);
                doPermute(result, array, start + 1);
                swap(array, start, i);
            }
        }
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

//    public List<List<Integer>> permute(int[] nums) {
//        List<Integer> path = new ArrayList<>();
//        List<List<Integer>> res = new LinkedList<>();
//        bt_per(nums, 0, path, res);
//        return res;
//    }
//
//    void bt_per(int[] nums, int cur, List<Integer> path, List<List<Integer>> res) {
//        if (cur < nums.length) {
//            for (int i = 0; i <= cur; i++) {
//                path.add(i, nums[cur]);
//                bt_per(nums, cur + 1, path, res);
//                path.remove(i);
//            }
//        } else if (cur == nums.length) {
//            res.add(new ArrayList(path));
//        }
//    }
    //31. Next Permutation
    public void nextPermutation(int[] nums) {
        int n = nums.length;
        int index = n - 1;
        if (n < 2) {
            return;
        }
        while (index > 0) {
            if (nums[index - 1] < nums[index]) {
                if (index > 0) {
                    for (int i = n - 1; i >= index; i--) {
                        if (nums[i] > nums[index - 1]) {
                            swap(nums, i, index - 1);
                            break;
                        }
                    }
                }
                break;
            }
            index--;
        }

        reverse(nums, index, n - 1);
    }

    void reverse(int[] nums, int left, int right) {
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }

    //47. Permutations II
    public List<List<Integer>> permuteUnique(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<List<Integer>>();
        }
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        doPermuteUnique(nums, 0, result);
        return result;
    }

    public boolean hasSameElement(int[] a, int begin, int i) {
        for (int j = begin; j < i; j++) {
            if (a[i] == a[j]) {
                return true;
            }
        }
        return false;
    }

    public void doPermuteUnique(int[] a, int begin, List<List<Integer>> result) {
        int n = a.length;
        if (begin == n) {
            ArrayList<Integer> list = new ArrayList<Integer>();
            for (int i = 0; i < n; i++) {
                list.add(a[i]);
            }
            result.add(list);
        } else {
            for (int i = begin; i < n; i++) {
                if (i > begin && hasSameElement(a, begin, i)) {
                    continue;
                }
                swap(a, begin, i);
                doPermuteUnique(a, begin + 1, result);
                swap(a, begin, i);
            }

        }
    }

    //22. Generate Parentheses
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        char[] perm = new char[n * 2];
        perms(n, n, perm, 0, res);
        return res;
    }

    private void perms(int open, int close, char[] perm, int i, List<String> res) {
        if (i == perm.length) {
            res.add(new String(perm));
            return;
        }
        if (open > 0 && close >= open) {
            perm[i] = '(';
            perms(open - 1, close, perm, i + 1, res);
        }
        if (close > 0) {
            perm[i] = ')';
            perms(open, close - 1, perm, i + 1, res);
        }
    }

    //273. Integer to English Words
    public String numberToWords(int num) {
        String[] units = new String[]{"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
        String[] tens = new String[]{"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety", "Hundred"};
        String[] digits = new String[]{"Billion", "Million", "Thousand", ""};
        StringBuilder sb = new StringBuilder();
        int f = 1000000000;
        int i = 0, n = 0;
        if (num == 0) {
            sb.append(units[0]);
        }
        while (num > 0) {
            n = num / f;
            if (n > 0) {
                numberToWordsHelper(sb, n, units, tens);
                sb.append(digits[i]).append(" ");
            }
            num %= f;
            f /= 1000;
            i++;
        }

        return sb.toString().trim();
    }

    private void numberToWordsHelper(StringBuilder sb, int num, String[] units, String[] tens) {
        if (num == 0) {

        } else if (num < 20) {
            sb.append(units[num]).append(" ");
        } else if (num < 100) {
            sb.append(tens[num / 10]).append(" ");
            if (num % 10 > 0) {
                sb.append(units[num % 10]).append(" ");
            }
        } else {
            sb.append(units[num / 100]).append(" Hundred ");
            numberToWordsHelper(sb, num % 100, units, tens);
        }
    }
//    public String numberToWords(int num) {
//        String[] units = new String[]{"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
//        String[] tens = new String[]{"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety", "Hundred"};
//        String[] digits = new String[]{"Billion", "Million", "Thousand", ""};
//        StringBuilder sb = new StringBuilder();
//        int f = 1000000000;
//        int i = 0, n = 0;
//        if (num == 0) {
//            sb.append(units[0]);
//        }
//        while (num > 0) {
//            n = num / f;
//            int h = 100;
//            if (n > 0) {
//                if (n / h > 0) {
//                    sb.append(units[n / h])
//                            .append(" ")
//                            .append(tens[tens.length - 1])
//                            .append(" ");
//                }
//                n %= h;
//                h /= 10;
//                if (n / h == 1) {
//                    sb.append(units[n])
//                            .append(" ");
//                } else {
//                    if (n / h > 1) {
//                        sb.append(tens[n / h])
//                                .append(" ");
//                    }
//                    if (n % h > 0) {
//                        sb.append(units[n % h])
//                                .append(" ");
//                    }
//                }
//                sb.append(digits[i])
//                        .append(" ");
//            }
//            num %= f;
//            f /= 1000;
//            i++;
//        }
//        return sb.toString().trim();
//    }

    //6. ZigZag Conversion
    public String ZigZagConvert(String text, int nRow) {
        if (nRow < 2) {
            return text;
        }
        int g = (2 * nRow - 2);
        StringBuilder sb = new StringBuilder(text.length());
        for (int k = 0; k < nRow; k++) {
            for (int i = k; i < text.length();) {
                sb.append(text.charAt(i));
                if (k == 0 || k == nRow - 1) {
                    i += g;
                } else if (i % g > nRow - 1) {
                    i += 2 * k;
                } else {
                    i += (g - 2 * k);
                }
            }
        }
        return sb.toString();
    }

    //71. Simplify Path
    public String simplifyPath(String path) {
        Deque<String> stack = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder(path.length());
        StringBuilder forders = new StringBuilder(path.length());
        String folder = "";
        for (int i = 0; i <= path.length(); i++) {
            if (i == path.length() || path.charAt(i) == '/') {
                if ("..".equals(folder)) {
                    if (stack.size() > 0) {
                        stack.pop();
                    }
                } else if (!".".equals(folder) && !"".equals(folder)) {
                    stack.push(folder);
                }
                folder = "";
            } else {
                folder += path.charAt(i);
            }
        }

        while (stack.size() > 0) {
            sb.append('/')
                    .append(stack.pollLast());
        }
        return sb.length() > 0 ? sb.toString() : "/";
    }

    //151. Reverse Words in a String
    public String reverseWords(String s) {
        char[] words = s.toCharArray();
        reverse(words, 0, words.length - 1);
        //System.out.println(new String(words));
        int k = 0;
        for (int i = 0, j = 0; i < words.length; i++) {
            if (words[i] != ' ') {
                if (k != 0) {
                    words[k++] = ' ';
                }
                j = i;
                while (j < words.length && words[j] != ' ') {
                    words[k++] = words[j++];
                }
                //System.out.println(k);
                reverse(words, k - (j - i), k - 1);
                i = j;
            }
        }
        return new String(words, 0, k).trim();
    }

    void reverse(char[] array, int left, int right) {
        while (left < right) {
            char c = array[left];
            array[left] = array[right];
            array[right] = c;
            left++;
            right--;
        }
    }

    //138. Copy List with Random Pointer
    public RandomListNode copyRandomList(RandomListNode head) {
        RandomListNode cur = head, next, copy;

        //insert copy into every cur's next;
        while (cur != null) {
            next = cur.next;
            copy = new RandomListNode(cur.label);
            copy.next = next;
            cur.next = copy;
            cur = next;
        }

        //assign random code point for copy
        cur = head;
        while (cur != null) {
            copy = cur.next;
            if (cur.random != null) {
                copy.random = cur.random.next;
            }
            cur = copy.next;
        }

        //link copy into a list
        cur = head;
        if (head != null) {
            head = head.next;
        }
        while (cur != null) {
            copy = cur.next;
            cur.next = copy.next;
            if (copy.next != null) {
                copy.next = cur.next.next;
            }
            cur = cur.next;
        }
        return head;
    }

    //209. Minimum Size Subarray Sum
    public int minSubArrayLen(int s, int[] nums) {
        int start = 0, end = 0, sum = 0, minLen = Integer.MAX_VALUE;
        while (end < nums.length) {
            while (end < nums.length && sum < s) {
                sum += nums[end++];
            }
            if (sum < s) {
                break;
            }
            while (start < end && sum >= s) {
                sum -= nums[start++];
            }
            if (end - start + 1 < minLen) {
                minLen = end - start + 1;
            }
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }

    public int minSubArrayLen2(int s, int[] nums) {
        int[] sums = new int[nums.length + 1];
        for (int i = 1; i < nums.length + 1; i++) {
            sums[i] = sums[i - 1] + nums[i - 1];
        }
        int min = sums.length + 1;
        for (int i = 0; i < sums.length; i++) {
            int end = binarySearch(sums, sums[i] + s, i + 1, sums.length - 1);
            if (end == sums.length) {
                break;
            }
            if (end - i < min) {
                min = end - i;
            }
        }
        return min == sums.length + 1 ? 0 : min;
    }

    int binarySearch(int[] sums, int v, int left, int right) {
        while (left <= right) {
            int mid = (left + right) / 2;
            if (sums[mid] >= v) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    //76. Minimum Window Substring
    public String minWindow(String s, String t) {
        if (t.length() <= 0 || s.length() < t.length()) {
            return "";
        }
        int start = 0, end = 0, i = 0, j = 0, count = t.length(), min = s.length() + 1;
        int[] table = new int[256];

        for (int k = 0; k < count; k++) {
            char c = t.charAt(k);
            table[c]++;
        }
        for (int k = 0; k < 256; k++) {
            if (table[k] < 1) {
                table[k] = Integer.MIN_VALUE;
            }
        }
        while (end < s.length()) {
            while (end < s.length() && count > 0) {
                char c = s.charAt(end++);
                if (table[c] != Integer.MIN_VALUE) {
                    if (table[c] > 0) {
                        count--;
                    }
                    table[c]--;
                }
            }
            if (count > 0) {
                break;
            }
            while (start < s.length() && count <= 0) {
                char c = s.charAt(start++);
                if (table[c] != Integer.MIN_VALUE) {
                    if (table[c] >= 0) {
                        count++;
                    }
                    table[c]++;
                }
            }
            if (end - start + 1 < min) {
                min = end - start + 1;
                i = start - 1;
                j = end;
            }
        }
        return min == s.length() + 1 ? "" : s.substring(i, j);
    }

    //127. Word Ladder
    public int ladderLength(String start, String end, Set<String> dict) {
        Set<String> set1 = new HashSet<>();
        Set<String> set2 = new HashSet<>();

        set1.add(start);
        set2.add(end);

        return ladderLengthHelper(dict, set1, set2, 1);
    }

    int ladderLengthHelper(Set<String> dict, Set<String> set1, Set<String> set2, int level) {
        if (set1.isEmpty()) {
            return 0;
        }

        if (set1.size() > set2.size()) {
            return ladderLengthHelper(dict, set2, set1, level);
        }

        // remove words from both ends
        for (String word : set1) {
            dict.remove(word);
        };
        for (String word : set2) {
            dict.remove(word);
        };

        // the set for next level
        Set<String> set = new HashSet<>();

        // for each string in the current level
        for (String str : set1) {
            for (int i = 0; i < str.length(); i++) {
                char[] chars = str.toCharArray();

                // change letter at every position
                for (char ch = 'a'; ch <= 'z'; ch++) {
                    chars[i] = ch;
                    String word = new String(chars);

                    // found the word in other end(set)
                    if (set2.contains(word)) {
                        return level + 1;
                    }

                    // if not, add to the next level
                    if (dict.contains(word)) {
                        set.add(word);
                    }
                }
            }
        }

        return ladderLengthHelper(dict, set2, set, level + 1);
    }

    //G questions 1: for all i in  i .. n sum(i/3+i/5)
    public int sumDividend(int n) {
        int i = n / 3, j = n / 5, k = n / 15;
        return 3 * i * (i + 1) / 2 + 5 * j * (j + 1) / 2 + 15 * k * (k + 1) / 2;
    }

    //G questions 2: how many combinations of String of n length with abc and in which the consistency of any letter is not over 3.
    public int abcCombination(int n) {
        if (n < 1) {
            return 0;
        }
        //initial dp
        int[][] dp = new int[n][2];
        dp[0][0] = 3;
        dp[0][1] = 0;
        for (int i = 0; i < n; i++) {
            dp[i][0] = dp[i - 1][0] * 2 + dp[i - 1][1] * 2;
            dp[i][1] = dp[i - 1][0];
        }

        return dp[n - 1][0] + dp[n - 1][1];
    }

    //G questions 3: how many zero exchanges would be made to sort a array with 0...n-1 misplaced
    public int zeroExchange(int[] nums) {
        int res = 0;
        boolean[] mark = new boolean[nums.length];
        for (int i = 0; i < nums.length; i++) {
            res += give(nums[i], nums, mark);
        }
        return res;
    }

    int give(int x, int[] nums, boolean[] mark) {
        int r = 0;
        boolean have = false;
        for (; !mark[x]; r++) {
            if (x == 0) {
                have = true;
            }
            mark[x] = true;
            x = nums[x];
        }
        return have ? r - 1 : (r <= 1 ? 0 : r + 1);
    }

    //G questions 4: how many opreations will occur when insert one element to the end to sort a array with 1...n per time?
    public int insertLastCount(int[] nums) {
        int want = 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == want) {
                want++;
            }
        }
        return nums.length - want + 1;
    }

    //G questions 5: 
    //Given  a 2 dimensional matrix where some of the elements are filled with 1 and rest of the elements
    //are filled. Here X means you cannot traverse to that particular points. From a cell you can either traverse to left, right, up or down
    //Given two points in the matrix find the shortest path between these points 
    private class Point {

        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public int getMatrixStepsBFS(char[][] matrix, Point s, Point e) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] steps = new int[rows][cols];
        boolean[][] visited = new boolean[rows][cols];
        Deque<Point> q = new ArrayDeque<>();
        q.add(s);
        while (q.size() > 0) {
            Point p = q.poll();
            visited[p.x][p.y] = true;
            if (p.x == e.x && p.y == e.y) {
                break;
            }
            if (p.x + 1 >= 0 && p.x + 1 < rows && !visited[p.x + 1][p.y] && matrix[p.x + 1][p.y] != 'X') {
                q.add(new Point(p.x + 1, p.y));
                steps[p.x + 1][p.y] = steps[p.x][p.y] + 1;
            }
            if (p.x - 1 >= 0 && p.x - 1 < rows && !visited[p.x - 1][p.y] && matrix[p.x - 1][p.y] != 'X') {
                q.add(new Point(p.x - 1, p.y));
                steps[p.x - 1][p.y] = steps[p.x][p.y] + 1;
            }
            if (p.y + 1 >= 0 && p.y + 1 < cols && !visited[p.x][p.y + 1] && matrix[p.x][p.y + 1] != 'X') {
                q.add(new Point(p.x, p.y + 1));
                steps[p.x][p.y + 1] = steps[p.x][p.y] + 1;
            }
            if (p.y - 1 >= 0 && p.y - 1 < cols && !visited[p.x][p.y - 1] && matrix[p.x][p.y - 1] != 'X') {
                q.add(new Point(p.x, p.y - 1));
                steps[p.x][p.y - 1] = steps[p.x][p.y] + 1;
            }

        }

        return steps[e.x][e.y];
    }

    //DP Questions:53,64,72,85,91,97,120,131,132,139,140,152
    //  1,equations
    //  2,initial value
    //  3,space optimal
    //53. Maximum Subarray
    public int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int res = dp[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            if (res < dp[i]) {
                res = dp[i];
            }
        }
        return res;
    }

    //64. Minimum Path Sum
    public int minPathSum(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[] dp = new int[cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (i == 0 && j == 0) {
                    dp[j] = grid[i][j];
                } else if (i == 0) {
                    dp[j] = dp[j - 1] + grid[i][j];
                } else if (j == 0) {
                    dp[j] = dp[j] + grid[i][j];
                } else {
                    dp[j] = Math.min(dp[j], dp[j - 1]) + grid[i][j];
                }
            }
        }
        return dp[cols - 1];
    }

    //72. Edit Distance
    public int minDistance0(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        int[][] cost = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0) {
                    cost[i][j] = j;
                } else if (j == 0) {
                    cost[i][j] = i;
                } else if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    cost[i][j] = cost[i - 1][j - 1];
                } else {
                    int a = cost[i - 1][j - 1];
                    int b = cost[i - 1][j];
                    int c = cost[i][j - 1];
                    cost[i][j] = a < b ? (a < c ? a : c) : (b < c ? b : c);
                    cost[i][j]++;
                }
            }
        }
        return cost[m][n];
    }

    //Sorting Questions:74,4,75
    //74. Search a 2D Matrix
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        for (int i = 0, j = n - 1; i < m && j >= 0;) {
            if (matrix[i][j] < target) {
                i++;
            } else if (matrix[i][j] > target) {
                j--;
            } else {
                return true;
            }
        }
        return false;
    }

    //4. Median of Two Sorted Arrays
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        return (findK(nums1, nums2, 0, 0, (m + n + 1) / 2) + findK(nums1, nums2, 0, 0, (m + n + 2) / 2)) * 0.5;
    }

    double findK(int[] a, int[] b, int sa, int sb, int k) {
        if (sa > a.length - 1) {
            return b[sb + k - 1];
        }
        if (sb > b.length - 1) {
            return a[sa + k - 1];
        }
        if (k == 1) {
            return Math.min(a[sa], b[sb]);
        }

        int aMid = Integer.MAX_VALUE, bMid = Integer.MAX_VALUE;
        if (sa + k / 2 - 1 < a.length) {
            aMid = a[sa + k / 2 - 1];
        }
        if (sb + k / 2 - 1 < b.length) {
            bMid = b[sb + k / 2 - 1];
        }

        if (aMid < bMid) {
            return findK(a, b, sa + k / 2, sb, k - k / 2);
        } else {
            return findK(a, b, sa, sb + k / 2, k - k / 2);
        }
    }

    //75. Sort Colors
    public void sortColors(int[] nums) {
        int n = nums.length;
        for (int i = 0, j = n, k = 0; k < j; ++k) {
            if (nums[k] < 1) {
                swap(nums, i++, k);
            } else if (nums[k] > 1) {
                swap(nums, --j, k--);
            }

        }
    }
}
