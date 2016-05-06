/*
 * The MIT License
 *
 * Copyright 2016 alpenliebe <alpseinstein@gmail.com>.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package ca.javabeast.algorithms.leetcode;

import ca.javabeast.algorithms.leetcode.LeetCode.TreeNode;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;

/**
 *
 * @author alpenliebe <alpseinstein@gmail.com>
 */
public class Solution {

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] arr = {0, 5, 0, 2, 3, 0, 3, 5, 0};
        LeetCode.print(s.getNumOfSwap(arr));
        //getFirstLetter
        LeetCode.print(s.getFirstLetter("aabbccdee"));
        System.out.println(findRoot(16.0));
    }

    
        //https://www.hackerrank.com/challenges/plus-minus  
    public static void plusMinus(int[] arr) {
        int n = arr.length;
        int pos = 0, neg = 0, zero = 0;
        for (int arr_i = 0; arr_i < n; arr_i++) {
            if (arr[arr_i] > 0) {
                pos++;
            } else if (arr[arr_i] < 0) {
                neg++;
            } else {
                zero++;
            }
        }
        System.out.format("%5f%n%5f%n%5f", (float) pos / n, (float) neg / n, (float) zero / n);
    }

    //https://www.hackerrank.com/challenges/staircase
    public static void staircase(int n) {
        for (int i = 1; i <= n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = n; j > 0; j--) {
                if (j > i) {
                    sb.append(' ');
                } else {
                    sb.append('#');
                }
            }
            System.out.println(sb.toString());
        }

    }

    //https://www.hackerrank.com/challenges/maxsubarray
    public static String maximumSub(int[] ar, int n) {
        int max = ar[0];
        int ans = ar[0] > 0 ? ar[0] : 0;
        int[][] dp = new int[n][2];
        dp[0] = new int[]{ar[0], Integer.MIN_VALUE};
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0] + ar[i], ar[i]);
            dp[i][1] = Math.max(dp[i - 1][0], dp[i - 1][1]);
            max = Math.max(ar[i], max);
            if (ar[i] > 0) {
                ans += ar[i];
            }
        }
        if (ans == 0) {
            ans = max;
        }
        return Math.max(dp[n - 1][0], dp[n - 1][1]) + " " + ans;
    }

    //https://www.hackerrank.com/challenges/tutorial-intro
    public static int binarySearch(int[] ar, int low, int high, int v) {
        while (low < high) {
            int m = (low + high) / 2;
            if (ar[m] == v) {
                low = m;
                break;
            } else if (ar[m] > v) {
                high = m;
            } else {
                low = m + 1;
            }
        }
        return low;
    }

    //https://www.hackerrank.com/challenges/bfsshortreach
    public static String shortestReach(int[][] edges, int n, int e, int start) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        int[] nodes = new int[n];
        Arrays.fill(nodes, -1);
        for (int i = 0; i < e; i++) {
            int a = edges[i][0], b = edges[i][1];
            List<Integer> al = map.get(a);
            if (al == null) {
                al = new ArrayList<>();
                map.put(a, al);
            }
            al.add(b);

            al = map.get(b);
            if (al == null) {
                al = new ArrayList<>();
                map.put(b, al);
            }
            al.add(a);
        }

        Deque<Integer> q = new ArrayDeque<>();
        q.offer(start);
        nodes[start - 1] = 0;
        while (q.size() > 0) {
            int s = q.poll();
            List<Integer> al = map.get(s);
            for (int i = 0; al != null && i < al.size(); i++) {
                int v = al.get(i);
                if (nodes[v - 1] == -1) {
                    q.offer(v);
                    nodes[v - 1] = nodes[s - 1] + 6;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (nodes[i] != 0) {
                sb.append(nodes[i]).append(' ');
            }
        }
        return sb.substring(0, sb.length() - 1);
    }

    //https://www.hackerrank.com/challenges/coin-change
    public static long exchangeCoins(int[] coins, int n) {
        int m = coins.length;
        long[][] dp = new long[n + 1][m + 1];
        return helper(coins, dp, m, (long) n);
    }

    private static long helper(int[] coins, long[][] dp, int used, long sum) {
        if (sum == 0) {
            return 1;
        }
        if (sum < 0 || used <= 0) {
            return 0;
        } else {
            if (dp[(int) sum][used] == 0) {
                dp[(int) sum][used] = helper(coins, dp, used - 1, sum) + helper(coins, dp, used, sum - coins[used - 1]);
            }
            return dp[(int) sum][used];
        }
    }

    //https://www.hackerrank.com/challenges/sherlock-and-the-beast
    public static String findBeast(int n) {
        int all = n;
        while (n % 3 != 0) {
            n -= 5;
        }
        if (n < 0) {
            return "-1";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= all; i++) {
            if (i <= n) {
                sb.append('5');
            } else {
                sb.append('3');
            }
        }
        return sb.toString();
    }

    //https://www.hackerrank.com/challenges/utopian-tree
    public static int getHeight(int n) {
        int height = 1;
        height = (height << ((n / 2) + 1)) - 1;
        if (n % 2 != 0) {
            height <<= 1;
        }
        return height;
    }

    //https://www.hackerrank.com/challenges/find-digits
    public static int findDigits(int n) {
        int x = n;
        int res = 0;
        while (x > 0) {
            if (x % 10 != 0 && n % (x % 10) == 0) {
                res++;
            }
            x /= 10;
        }
        return res;
    }

    //https://www.hackerrank.com/challenges/sherlock-and-squares
    public static int getSquareNum(int a, int b) {
        int count = 0;
        for (int i = (int) Math.ceil(Math.sqrt(a)); i * i <= b; i++) {
            count++;
        }
        return count;
    }

    //https://www.hackerrank.com/challenges/candies
    public static int getCandies(int[] ratings) {
        if (ratings.length <= 0) {
            return 0;
        }
        int count = 0, n = ratings.length;
        int[] candies = new int[n];
        candies[0] = 1;
        for (int i = 1; i < n; i++) {
            candies[i] = 1;
            if (ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1;
            }
        }
        for (int i = n - 1; i > 0; i--) {
            if (ratings[i] < ratings[i - 1] && candies[i] >= candies[i - 1]) {
                candies[i - 1] = candies[i] + 1;
            }
            count += candies[i];
        }
        return count + candies[0];
    }

    static int getIntegerComplement(int n) {
        int ret = 0;
        int i = 1;
        while (n > 0) {
            int remainder = n % 2;
            ret += (i * (1 - remainder));
            i <<= 1;
            n /= 2;
        }
        return ret;
    }

    static int getIntegerComplement2(int n) {

        int ones = (Integer.highestOneBit(n) << 1) - 1;
        return n ^ ones;
    }

    //uber perfect city: https://codefights.com/fight/KuDq7HSxk4En2Z62S
    double perfectCity(double[] departure, double[] destination) {
        double x1 = 0.0, y1 = 0.0, x2 = 0.0, y2 = 0.0, res = 0.0;
        if (Math.floor(departure[0]) == departure[0] && Math.floor(destination[0]) == destination[0]) {
            x1 = departure[0];
            y1 = departure[1];
            x2 = destination[0];
            y2 = destination[1];
            res = Math.abs(y1 - y2);
            if (Math.floor(y1) == Math.floor(y2)) {
                res = Math.min(((y1 - Math.floor(y1)) + (y2 - Math.floor(y2))), (Math.ceil(y1) - y1) + (Math.ceil(y2) - y2));
            }
        } else if (Math.floor(departure[1]) == departure[1] && Math.floor(destination[1]) == destination[1]) {
            y1 = departure[0];
            x1 = departure[1];
            y2 = destination[0];
            x2 = destination[1];
            res = Math.abs(y1 - y2);
            if (Math.floor(y1) == Math.floor(y2)) {
                res = Math.min(((y1 - Math.floor(y1)) + (y2 - Math.floor(y2))), (Math.ceil(y1) - y1) + (Math.ceil(y2) - y2));
            }
        } else {
            x1 = departure[0];
            y1 = departure[1];
            x2 = destination[0];
            y2 = destination[1];
            res = Math.abs(y1 - y2);
        }

        res += Math.abs(x1 - x2);
        return res;
    }

    //Uber driver and you are trying your best to park your car in a parking lot: https://codefights.com/fight/KuDq7HSxk4En2Z62S
    boolean parkingSpot(int[] carDimensions, int[][] parkingLot, int[] luckySpot) {
        boolean isHorizontal = luckySpot[2] - luckySpot[0] + 1 == carDimensions[1];
        int x1 = luckySpot[0], x2 = luckySpot[2], y1 = luckySpot[1], y2 = luckySpot[3], rows = parkingLot.length, cols = parkingLot[0].length;
        if (!isHorizontal) {
            boolean flag = true;
            while (x1 < rows) {
                for (int i = y1; i <= y2; i++) {
                    if (parkingLot[x1][i] == 1) {
                        flag = false;
                        break;
                    }
                }
                x1++;
            }

            if (!flag) {
                flag = true;
                while (x2 >= 0) {
                    for (int i = y1; i <= y2; i++) {
                        if (parkingLot[x2][i] == 1) {
                            flag = false;
                            break;
                        }
                    }
                    x2--;
                }
            }
            return flag;
        } else {
            boolean flag = true;
            while (y1 < cols) {
                for (int i = x1; i <= x2; i++) {
                    if (parkingLot[i][y1] == 1) {
                        flag = false;
                        break;
                    }
                }
                y1++;
            }

            if (!flag) {
                flag = true;
                while (y2 >= 0) {
                    for (int i = x1; i <= x2; i++) {
                        if (parkingLot[i][y2] == 1) {
                            flag = false;
                            break;
                        }
                    }
                    y2--;
                }
            }
            return flag;
        }

    }

    int arrayMode(int[] sequence) {
        Map<Integer, Integer> map = new HashMap<>();
        int n = sequence.length;
        for (int i = 0; i < n; i++) {
            Integer x = map.get(sequence[i]);
            if (x == null) {
                x = 0;
            }
            map.put(sequence[i], x + 1);
        }
        int num = 0, max = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                num = entry.getKey();
            }
        }
        return num;
    }

    public static String[] findCommonElement(String[][] arr) {
        int len = arr.length;
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            Set<String> set = new HashSet<>();
            for (int j = 0; j < arr[i].length; j++) {
                String s = arr[i][j];
                if (!set.contains(s)) {
                    Integer count = map.get(s);
                    if (count == null) {
                        count = 0;
                    }
                    map.put(s, count + 1);
                    set.add(s);
                }
            }
        }
        List<String> list = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() >= len) {
                list.add(entry.getKey());
            }
        }
        return list.toArray(new String[list.size()]);
    }

    public static double getPercentageOfStrings(String[] arr1, String[] arr2) {
        int len1 = arr1.length, len2 = arr2.length, num = 0;
        Set<String> set = new HashSet<>();
        for (int i = 0; i < len1; i++) {
            set.add(arr1[i]);
        }
        for (int i = 0; i < len2; i++) {
            if (set.contains(arr2[i])) {
                set.remove(arr2[i]);
                num++;
            }
        }
        return Math.round((double) num / len2 * 10000) / 100.0;
    }

    boolean crossroads(int[] road1, int[] road2, int crossTime) {
        int j = 0;
        for (int i = 0; i < road1.length; i++) {
            while (j < road2.length && road2[j] < road1[i]) {
                if (road2[j] + crossTime > road1[i]) {
                    return true;
                }
                j++;
            }
            if (j < road2.length && road1[i] + crossTime > road2[j]) {
                return true;
            }
        }
        return false;
    }

    int validRoute(int[] travelTimes, int[] readyTimes, int[] cancelTimes) {

        int earliestStartTime = 0;
        int latestStartTime = 24 * 60 * 60;
        int totalWorkTime = 0;
        for (int i = 0; i < readyTimes.length; i++) {
            if (totalWorkTime + earliestStartTime > cancelTimes[i]) {
                return -1;
            }
            latestStartTime = Math.min(latestStartTime,
                    cancelTimes[i] - totalWorkTime);
            if (latestStartTime + totalWorkTime < readyTimes[i]) {
                totalWorkTime = readyTimes[i] - latestStartTime;
            }
            earliestStartTime = Math.max(earliestStartTime,
                    readyTimes[i] - totalWorkTime);

            totalWorkTime += travelTimes[i];
        }

        return totalWorkTime;
    }

    /*1 - Passing:
    INPUT: ["doc", "doc", "image", "doc(1)", "doc"]/3,2,1,1,1
    OUTPUT: ["doc", "doc(1)", "image", "doc(1)(1)", "doc(2)"]
    2 - Failing:

    INPUT: ["a(1)","a(6)","a","a","a","a","a","a","a","a","a","a"]//1,1,10,1,1,1,1,1,1,1,1
    OUTPUT: ["a(1)","a(6)","a","a(2)","a(3)","a(4)","a(5)","a(7)","a(8)","a(9)","a(10)","a(11)"]
    */
    public static String[] renameFilenames(String[] names){
        Map<String, Integer> map = new HashMap<>();
        for(int i = 0; i < names.length; i++){
            String name = names[i];
            Integer count = map.get(name);
            if(count == null){
                count = 0;
            }
            map.put(name, count + 1);
            
            if(count == 0)
                continue;
            
            while(map.get(name + "(" + count + ")") != null){
                count++;
            }
            names[i] = name + "(" + count + ")";
            map.put(names[i], 1);
        }
        return names;
    }
    
    public static double findRoot(double n) {
        double x = n;
        double y = 1;
        while (x > y) {
            System.out.println(x + "-" + y);
            x = (x + y) / 2;
            y = n / x;
        }
        return x;
    }

    public int getNumOfSwap(int[] nums) {
        if (nums == null || nums.length < 3) {
            return 0;
        }
        int n = nums.length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                count++;
            }

        }
        int leftCount = count, rightCount = count;
        for (int i = 0; i < count; i++) {
            if (nums[i] == 0) {
                leftCount--;
            }
            if (nums[n - 1 - i] == 0) {
                rightCount--;
            }
        }
        return Math.min(leftCount, rightCount);
    }

    public int getNumOfSwap1(int[] nums) {
        if (nums == null || nums.length < 3) {
            return 0;
        }
        int n = nums.length;
        int left = 0, right = n - 1;
        int res1 = 0, res2 = 0;
        while (true) {
            while (nums[left] == 0) {
                left++;
            }
            while (nums[right] != 0) {
                right--;
            }
            if (left < right) {
                left++;
                right--;
                res1++;
            } else {
                break;
            }
        }
        left = 0;
        right = n - 1;
        while (true) {
            while (nums[left] != 0) {
                left++;
            }
            while (nums[right] == 0) {
                right--;
            }
            if (left < right) {
                left++;
                right--;
                res2++;
            } else {
                break;
            }
        }
        return Math.min(res1, res2);
    }

    //287. Find the Duplicate Number
    //Floyd loop detection(https://leetcode.com/problems/find-the-duplicate-number/)
    public int getDuplicateNum(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        int n = nums.length - 1;
        int slow = 0, fast = 0;
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);
        fast = 0;
        while (fast != slow) {
            fast = nums[fast];
            slow = nums[slow];
        }
        return slow;
    }

    class Interval {

        int start;
        int end;

        public Interval() {
            start = 0;
            end = 0;
        }

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public List<Interval> mergeIntervals(List<Interval> intervals) {
        if (intervals == null || intervals.size() < 2) {
            return intervals;
        }
        Collections.sort(intervals, new Comparator<Interval>() {
            public int compare(Interval i1, Interval i2) {
                return i1.start - i2.start;
            }
        });
        List<Interval> res = new ArrayList<>();
        Interval temp = null;
        for (Interval in : intervals) {
            if (temp == null) {
                temp = in;
            } else if (temp.end >= in.start) {
                temp.end = Math.max(temp.end, in.end);
            } else {
                res.add(temp);
                temp = in;
            }
        }
        res.add(temp);
        return res;
    }

    //given a string, find the first letter in that string which appears only once in this string.
    //example: aabbccdee, return d
    public char getFirstLetter(String str) {
        if (str == null || str.length() < 1) {
            return 0;
        }

        char[] arr = str.toCharArray();
        int n = arr.length;
        int[] letters = new int[26];
        for (int i = 0; i < n; i++) {
            int j = arr[i] - 'a';
            if (letters[j] < 2) {
                letters[j]++;
            }
        }
        for (int i = 0; i < n; i++) {
            int j = arr[i] - 'a';
            if (letters[j] == 1) {
                return (char) ('a' + j);
            }
        }
        return 0;
    }

    //if the input is not a string. It is a internet Stream. What situation should you handle?
    public char getFirstLetterFromStream(InputStream in) {
        int c = 0;
        try {
            if (in == null) {
                return (char) c;
            }
            int[] letters = new int[26];
            int[] pos = new int[26];
            int count = 0;
            try {
                while ((c = in.read()) != -1) {
                    int i = c - 'a';
                    if (i <= 25 && i >= 0) {
                        if (letters[i] < 2) {
                            letters[i]++;
                        }
                        pos[i] = count;
                    }
                    count++;
                    if (count == 1000) {
                        count -= 999;
                        for (int j = 0; j < 26; j++) {
                            pos[j] -= 999;
                        }

                    }
                }
            } finally {
                for (int i = 0; i < 26; i++) {
                    if (letters[i] == 1) {
                        if (pos[i] < count) {
                            c = 'a' + i;
                            count = pos[i];
                        }
                    }
                }
                in.close();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return (char) c;
    }

    public static void main1(String[] args) {
        LeetCode lc = new LeetCode();
        LeetCode.print(lc.longestValidParentheses("()(()"));
        LeetCode.print(lc.bulbSwitch(100));
        System.out.println(Arrays.toString(lc.solveNQueens(8).get(0).toArray()));
        System.out.println(Math.log10(0) / Math.log10(3) % 1 == 0);
        //String s = "boo:and:foo";
        //System.out.println(Arrays.asList(s.split("o")));
        System.out.println(lc.reverseWords(" "));
        //original:[53,64,72,85,91,97,120,131,132,139,140,152,77,78,90,51,52,126,74,4,75]
        int[] deck = {53, 64, 72, 85, 91, 97, 120, 131, 132, 139, 152, 77, 78, 90, 51, 52, 126};
        //shuffle(deck);
        System.out.println(Arrays.toString(deck));
        //[161]
        LeetCode.print("9,3,4,#,#,1,#,#,2,#,6,#,#".split(","));
        TreeNode root = lc.deserializePreTree("9,3,4,#,#,1,#,#,2,#,6,#,#".split(","));
        lc.preTraversal(root);
        System.out.println("abc".compareTo("ab"));
        System.out.println(Arrays.equals(new String[]{"abc", "cba"}, new String[]{"abc", "cba"}));
    }

    static void shuffle(int[] arr) {
        Random rnd = new Random();
        int n = arr.length;
        for (int i = n - 1; i >= 0; i--) {
            swap(arr, i, rnd.nextInt(i + 1));
        }
    }

    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

}

class LeetCode {

    //deserialize preorder serialization binary tree
    int i = 0;

    public TreeNode deserializePreTree(String[] str) {
        TreeNode root = null;
        if (i < str.length) {
            String s = str[i];
            i++;
            if (!s.equals("#")) {
                root = new TreeNode(Integer.parseInt(s));
                root.left = deserializePreTree(str);
                root.right = deserializePreTree(str);
            }
        }
        return root;
    }

    public void preTraversal(TreeNode root) {
        if (root == null) {
            System.out.print("#");
            return;
        }
        System.out.print(root.val);
        preTraversal(root.left);
        preTraversal(root.right);
    }

    //32. Longest Valid Parentheses
    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        int left = 0;
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {  //push '(' set v=0
                stack.push(i);
            } else if (stack.isEmpty()) {
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

    // add One
    public int[] addOne(int[] digits) { //[9,9,9]
        int n = digits.length;//3
        int[] res = new int[n + 1];
        int sum = digits[n - 1] + 1;//10
        int c = sum / 10;//1
        for (int i = n - 1; i >= 0; i--) {//i=1
            res[i] = sum % 10;//[9,0,0]
            if (i == 0) {
                break;
            }
            sum = digits[i - 1] + c;//10
            c = sum / 10;//1

        }
        //res[1] = sum%10;//[0,0,0]
        if (c > 0) {//c=1
            res[0] = c;//[1,0,0,0]
            //for(int i = 1; i < n+1; i++){//i=1,2,3
            //	res[i] = temp[i-1];
            //}
        }//[0,9,0,0]
        return res;//[1,0,0,0]
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
        }
        for (String word : set2) {
            dict.remove(word);
        }

        // the set for next level
        Set<String> set = new HashSet<>();

        // for each string in the current level
        for (String str : set1) {

            char[] chars = str.toCharArray();
            for (int i = 0; i < str.length(); i++) {
                // change letter at every position
                char c = chars[i];
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
                chars[i] = c;
            }

        }

        return ladderLengthHelper(dict, set, set2, level + 1);
    }

    //126. Word Ladder II
    public List<List<String>> findLadders(String start, String end, Set<String> dict) {
        // hash set for both ends
        Set<String> set1 = new HashSet<>();
        Set<String> set2 = new HashSet<>();

        // initial words in both ends
        set1.add(start);
        set2.add(end);

        // we use a map to help construct the final result
        Map<String, List<String>> map = new HashMap<>();

        // build the map
        findLaddersHelper(dict, set1, set2, map, false);

        // recursively build the final result using BackTracking
        List<List<String>> res = new ArrayList<>();
        List<String> sol = new ArrayList<>(Arrays.asList(start));
        generateList(start, end, map, sol, res);

        return res;
    }

    void findLaddersHelper(Set<String> dict, Set<String> set1, Set<String> set2, Map<String, List<String>> map, boolean flip) {

        if (set1.size() > set2.size()) {
            findLaddersHelper(dict, set2, set1, map, !flip);
            return;
        }

        // remove words on current both ends from the dict
        dict.removeAll(set1);
        dict.removeAll(set2);

        // as we only need the shortest paths
        // we use a boolean value help early termination
        boolean done = false;

        // set for the next level
        Set<String> set = new HashSet<>();

        // for each string in end 1
        for (String str : set1) {

            char[] chars = str.toCharArray();
            for (int i = 0; i < str.length(); i++) {
                char c = chars[i];
                // change one character for every position
                for (char ch = 'a'; ch <= 'z'; ch++) {
                    chars[i] = ch;
                    String word = new String(chars);

                    // make sure we construct the tree in the correct direction
                    String key = flip ? word : str;
                    String val = flip ? str : word;

                    List<String> list = map.containsKey(key) ? map.get(key) : new ArrayList<>();

                    if (set2.contains(word)) {
                        done = true;

                        list.add(val);
                        map.put(key, list);
                    }

                    if (!done && dict.contains(word)) {
                        set.add(word);

                        list.add(val);
                        map.put(key, list);
                    }
                }
                chars[i] = c;

            }
        }

        // early terminate if done is true
        if (!done && !set.isEmpty()) {
            findLaddersHelper(dict, set2, set, map, !flip);
        }
    }

    void generateList(String start, String end, Map<String, List<String>> map, List<String> sol, List<List<String>> res) {
        if (start.equals(end)) {
            res.add(new ArrayList<>(sol));
            return;
        }

        // need this check in case the diff between start and end happens to be one
        // e.g "a", "c", {"a", "b", "c"}
        if (!map.containsKey(start)) {
            return;
        }

        for (String word : map.get(start)) {
            sol.add(word);
            generateList(word, end, map, sol, res);
            sol.remove(sol.size() - 1);
        }
    }

//    //78. Subsets
//    public List<List<Integer>> subsets(int[] nums) {
//        int n = nums.length;
//        Arrays.sort(nums);
//        List<Integer> path = new ArrayList<>(n * 2);
//        List<List<Integer>> res = new LinkedList<>();
//        subsetsHelper(0, nums, path, res);
//        return res;
//    }
//
//    void subsetsHelper(int cur, int[] nums, List<Integer> path, List<List<Integer>> res) {
//        res.add(new ArrayList<>(path));
//        if (path.size() < nums.length) {
//            for (int i = cur; i < nums.length; i++) {
//                path.add(nums[i]);
//                subsetsHelper(i + 1, nums, path, res);
//                path.remove(path.size() - 1);
//            }
//        }
//    }
    //90. Subsets II
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        List<Integer> path = new ArrayList<>(n * 2);
        List<List<Integer>> res = new ArrayList<>();
        subsetsWithDupHelper(0, nums, path, res);
        return new ArrayList<>(res);
    }

    void subsetsWithDupHelper(int cur, int[] nums, List<Integer> path, List<List<Integer>> res) {
        res.add(new ArrayList<>(path));
        if (path.size() < nums.length) {
            for (int i = cur; i < nums.length; i++) {
                if (i > cur && nums[i] == nums[i - 1]) {
                    continue;
                }
                path.add(nums[i]);
                subsetsWithDupHelper(i + 1, nums, path, res);
                path.remove(path.size() - 1);
            }
        }
    }

    //91. Decode Ways
    public int numDecodings(String s) {
        if (s == null || s.isEmpty() || s.charAt(0) == '0') {
            return 0;
        }
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = dp[1] = 1;
        for (int i = 2; i < n + 1; i++) {
            char c2 = s.charAt(i - 2);
            char c1 = s.charAt(i - 1);
            int preTwo = (c2 == '1' || (c2 == '2' && c1 < '7')) ? dp[i - 2] : 0;
            int preOne = (c1 != '0') ? dp[i - 1] : 0;
            dp[i] = preTwo + preOne;
        }
        return dp[n];
    }

    //131. Palindrome Partitioning
    public List<List<String>> partition(String s) {
        List<String> path = new ArrayList<>();
        List<List<String>> res = new LinkedList<>();
        partitionHelper(0, s, path, res);
        return res;
    }

    void partitionHelper(int cur, String s, List<String> path, List<List<String>> res) {
        if (cur >= s.length()) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = cur; i < s.length(); i++) {
            if (!isPalindrome(s, cur, i)) {
                continue;
            }
            path.add(s.substring(cur, i + 1));
            partitionHelper(i + 1, s, path, res);
            path.remove(path.size() - 1);
        }

    }

    boolean isPalindrome(String s, int l, int r) {
        while (l <= r) {
            if (s.charAt(l) != s.charAt(r)) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }

    //132. Palindrome Partitioning II
    public int minCut(String s) {
        int n = s.length();
        char[] c = s.toCharArray();
        int[] cuts = new int[n];
        boolean[][] pal = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            int min = i;
            for (int j = 0; j <= i; j++) {
                if (c[i] == c[j] && (j + 1 > i - 1 || pal[j + 1][i - 1])) {
                    pal[j][i] = true;
                    min = j == 0 ? 0 : Math.min(min, cuts[j - 1] + 1);
                }
            }
            cuts[i] = min;
        }
        return cuts[n - 1];
    }

    //120. Triangle
    public int minimumTotal(List<List<Integer>> triangle) {
        int m = triangle.size();
        int[] dp = new int[m + 1];
        for (int i = m - 1; i >= 0; i--) {
            for (int j = 0; j < i + 1; j++) {
                dp[j] = Math.min(dp[j], dp[j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0];
    }

    //51. N-Queens
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new LinkedList<>();
        List<String> path = new ArrayList<>();
        char[] c = new char[n];
        int[] q = new int[n];
        Arrays.fill(c, '.');
        Arrays.fill(q, -1);
        QueenHelper(0, q, c, path, res);
        return res;
    }

    void QueenHelper(int cur, int[] q, char[] c, List<String> p, List<List<String>> r) {
        if (cur >= q.length) {
            r.add(new ArrayList<>(p));
            return;
        }
        for (int i = 0; i < q.length; i++) {
            if (!isValid(q, cur, i)) {
                continue;
            }
            q[cur] = i;
            c[i] = 'Q';
            p.add(new String(c));
            c[i] = '.';
            QueenHelper(cur + 1, q, c, p, r);
            p.remove(p.size() - 1);
            q[cur] = -1;
        }
    }

    boolean isValid(int[] q, int x, int y) {
        for (int i = 0; i < q.length; i++) {
            if (q[i] == -1) {
                return true;
            } else if (x == i || y == q[i] || Math.abs(x - i) == Math.abs(y - q[i])) {
                return false;
            }

        }
        return true;
    }

    //52. N-Queens II
    public int totalNQueens(int n) {
        int[] res = new int[1];
        int[] q = new int[n];
        Arrays.fill(q, -1);
        QueenHelper(0, q, res);
        return res[0];
    }

    void QueenHelper(int cur, int[] q, int[] r) {
        if (cur >= q.length) {
            r[0]++;
            return;
        }
        for (int i = 0; i < q.length; i++) {
            if (!isValid(q, cur, i)) {
                continue;
            }
            q[cur] = i;
            QueenHelper(cur + 1, q, r);
            q[cur] = -1;
        }
    }

    //85. Maximal Rectangle
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int res = 0, m = matrix.length, n = matrix[0].length;
        int[] left = new int[n], right = new int[n], height = new int[n];
        Arrays.fill(right, n);
        for (int i = 0; i < m; i++) {
            int l = 0, r = n;
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    height[j]++;
                    left[j] = Math.max(left[j], l);
                } else {
                    height[j] = 0;
                    left[j] = 0;
                    l = j + 1;
                }
                if (matrix[i][n - j - 1] == '1') {
                    right[n - j - 1] = Math.min(right[n - j - 1], r);
                } else {
                    right[n - j - 1] = n;
                    r = n - j - 1;
                }
            }
            for (int j = 0; j < n; j++) {
                res = Math.max(res, (right[j] - left[j]) * height[j]);
            }
        }
        return res;
    }

    //284. Peeking Iterator
    // Java Iterator interface reference:
    // https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html
    class PeekingIterator<T> implements Iterator<T> {

        private T head = null;
        private Iterator<T> iterator;

        public PeekingIterator(Iterator<T> iterator) {
            // initialize any member here.
            this.iterator = iterator;
            if (iterator.hasNext()) {
                head = iterator.next();
            }
        }

        // Returns the next element in the iteration without advancing the iterator.
        public T peek() {
            return head;
        }

        // hasNext() and next() should behave the same as in the Iterator interface.
        // Override them if needed.
        @Override
        public T next() {
            T h = head;
            head = iterator.hasNext() ? iterator.next() : null;
            return h;
        }

        @Override
        public boolean hasNext() {
            return head != null;
        }
    }

    //282. Expression Add Operators
    public List<String> addOperators(String digits, int target) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() < 1) {
            return res;
        }
        helper(digits, res, 0, 0, target + 0l, "");
        return res;
    }

    void helper(String digits, List<String> res, long pre, int cur, long target, String exp) {
        if (cur >= digits.length()) {
            if (target == 0) {
                res.add(exp);
            }
            return;
        }
        for (int i = cur + 1; i <= digits.length(); i++) {
            long val = Long.parseLong(digits.substring(cur, i));
            if (val > (long) Integer.MAX_VALUE || val < (long) Integer.MIN_VALUE) {
                break;
            }
            if (cur == 0) {
                helper(digits, res, val, i, target - val, exp + val);
            } else {
                helper(digits, res, val, i, target - val, exp + "+" + val);
                helper(digits, res, -val, i, target + val, exp + "-" + val);
                helper(digits, res, pre * val, i, target + pre - pre * val, exp + "*" + val);
            }
            if (val == 0) {
                break;
            }
        }
    }

    //173. Binary Search Tree Iterator
    public class BSTIterator {

        private Deque<TreeNode> stack;

        public BSTIterator(TreeNode root) {
            stack = new ArrayDeque<>();
            pushAll(root);
        }

        /**
         * @return whether we have a next smallest number
         */
        public boolean hasNext() {
            return stack.size() > 0;
        }

        /**
         * @return the next smallest number
         */
        public int next() {
            TreeNode cur = stack.pop();
            pushAll(cur.right);
            return cur == null ? null : cur.val;
        }

        private void pushAll(TreeNode node) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }
    }

    //10. Regular Expression Matching
    public boolean isMatch(String s, String p) {
        int sl = s.length(), pl = p.length();
        boolean[][] dp = new boolean[sl + 1][pl + 1];
        dp[0][0] = true;
        for (int i = 0; i <= sl; i++) {
            for (int j = 1; j <= pl; j++) {
                char c = p.charAt(j - 1);
                if (c == '*') {
                    dp[i][j] = (j > 1 && dp[i][j - 2]) || (i > 0 && dp[i - 1][j] && (p.charAt(j - 2) == '.' || p.charAt(j - 2) == s.charAt(i - 1)));
                } else {
                    dp[i][j] = i > 0 && dp[i - 1][j - 1] && (c == '.' || c == s.charAt(i - 1));
                }
            }
        }
        return dp[sl][pl];
    }

    //M/E
    //336. Palindrome Pairs
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> res = new ArrayList<>();
        if (words == null || words.length < 1) {
            return res;
        }
        int n = words.length;
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(words[i], i);
        }
        for (int i = 0; i < n; i++) {
            int len = words[i].length(), l = 0, r = 0;
            while (l <= r) {
                String str1 = new StringBuilder(words[i].substring(l, r)).reverse().toString();
                String str2 = words[i].substring(l == 0 ? r : 0, l == 0 ? len : l);
                Integer j = map.get(str1);
                if (j != null && j != i && isPalindrome(str2)) {
                    List<Integer> pair = new ArrayList<>();
                    pair.add(l == 0 ? i : j);
                    pair.add(l == 0 ? j : i);
                    res.add(pair);
                }
                if (r < len) {
                    r++;
                } else {
                    l++;
                }
            }
        }
        return res;
    }

    boolean isPalindrome(String s) {
        if (s == null && s.length() == 0) {
            return true;
        }
        char[] arr = s.toCharArray();
        for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
            if (arr[i] != arr[j]) {
                return false;
            }
        }
        return true;
    }

    //334. Increasing Triplet Subsequence
    public boolean increasingTriplet(int[] nums) {
        int s = Integer.MAX_VALUE, b = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= s) {
                s = nums[i];
            } else if (nums[i] <= b) {
                b = nums[i];
            } else {
                return true;
            }
        }
        return false;
    }

    //332. Reconstruct Itinerary
    public List<String> findItinerary(String[][] tickets) {
        Map<String, List<String>> graph = new HashMap<>();
        List<String> res = new LinkedList<>();
        for (String[] ticket : tickets) {
            String s = ticket[0];
            String d = ticket[1];
            List<String> l = graph.get(s);
            if (l == null) {
                l = new ArrayList<>();
                graph.put(s, l);
            }
            l.add(d);
        }
        for (Map.Entry<String, List<String>> entry : graph.entrySet()) {
            Collections.sort(entry.getValue());
        }
        res.add("JFK");
        findItineraryHelper("JFK", tickets.length, graph, res);
        return res;
    }

    boolean findItineraryHelper(String s, int l, Map<String, List<String>> g, List<String> res) {
        if (l <= 0) {
            return true;
        }

        List<String> dest = g.get(s);
        if (dest != null) {
            for (int i = 0; i < dest.size(); i++) {
                String d = dest.get(i);
                res.add(d);
                dest.remove(i);
                if (findItineraryHelper(d, l - 1, g, res)) {
                    return true;
                }
                res.remove(res.size() - 1);
                dest.add(i, d);
            }
        }
        return false;
    }

    //331. Verify Preorder Serialization of a Binary Tree,# of leaves = # of nonleaves + 1
    public boolean isValidSerialization(String preorder) {
        if (preorder == null || preorder.length() < 1) {
            return false;
        }
        String[] strs = preorder.split(",");
        int diff = 0;
        for (String s : strs) {
            if (diff > 0) {
                return false;
            }
            if (s.equals("#")) {
                diff++;
            } else {
                diff--;
            }
        }
        return diff == 1;
    }

    //330. Patching Array
    public int minPatches(int[] nums, int n) {
        int i = 0, res = 0, l = nums.length;
        long top = 0;
        while (top < n) {
            if (i < l && nums[i] <= (top + 1)) {
                top += nums[i++];
            } else {
                res++;
                top = 2 * top + 1;
            }
        }
        return res;
    }

    //329. Longest Increasing Path in a Matrix
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length < 1 || matrix[0].length < 1) {
            return 0;
        }
        int m = matrix.length, n = matrix[0].length, res = 1;
        int[][] cache = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res = Math.max(res, LIPHelper(matrix, cache, i, j, Integer.MAX_VALUE));
            }
        }
        return res;
    }

    int LIPHelper(int[][] m, int[][] c, int i, int j, int pre) {
        if (i < 0 || i > m.length - 1 || j < 0 || j > m[0].length - 1 || m[i][j] >= pre) {
            return 0;
        }
        if (c[i][j] > 0) {
            return c[i][j];
        }
        int cur = m[i][j], temp = 0;
        temp = Math.max(LIPHelper(m, c, i + 1, j, cur), temp);
        temp = Math.max(LIPHelper(m, c, i - 1, j, cur), temp);
        temp = Math.max(LIPHelper(m, c, i, j + 1, cur), temp);
        temp = Math.max(LIPHelper(m, c, i, j - 1, cur), temp);
        c[i][j] = ++temp;
        return temp;
    }

    //328. Odd Even Linked List
    public ListNode oddEvenList(ListNode head) {
        if (head != null) {
            ListNode eHead = head.next;
            ListNode o = head, e = eHead;
            while (e != null) {
                o.next = e.next;
                if (e.next != null) {
                    e.next = e.next.next;
                    o = o.next;
                }
                e = e.next;
            }
            o.next = eHead;
        }
        return head;
    }

    //327. Count of Range Sum
    public int countRangeSum(int[] nums, int lower, int upper) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        int n = nums.length, res = 0;
        long[] sums = new long[n + 1];
        for (int i = 0; i < n; i++) {
            sums[i + 1] = nums[i] + sums[i];
        }
        return countRangeSumMergeSort(sums, 0, n + 1, lower, upper);
    }

    int countRangeSumMergeSort(long[] sums, int s, int e, int lo, int up) {
        if (e - s <= 1) {
            return 0;
        }
        int m = (s + e) / 2;
        int count = countRangeSumMergeSort(sums, s, m, lo, up) + countRangeSumMergeSort(sums, m, e, lo, up);
        long[] cache = new long[e - s];
        int l = m, u = m, t = m;
        for (int i = s, k = 0; i < m; i++, k++) {
            while (l < e && sums[l] - sums[i] < lo) {
                l++;
            }
            while (u < e && sums[u] - sums[i] <= up) {
                u++;
            }
            while (t < e && sums[t] < sums[i]) {
                cache[k++] = sums[t++];
            }
            cache[k] = sums[i];
            count += u - l;
        }
        System.arraycopy(cache, 0, sums, s, t - s);
        return count;
    }

    //326. Power of Three
    public boolean isPowerOfThree(int n) {
        return (Math.log10(n) / Math.log10(3) % 1 == 0);
    }

    //324. Wiggle Sort II 
    public void wiggleSort20(int[] nums) {//O(NlogN)
        if (nums == null || nums.length < 2) {
            return;
        }
        int n = nums.length;
        int[] sorted = Arrays.copyOf(nums, n);
        Arrays.sort(sorted);
        for (int i = (n - 1) / 2, j = n - 1, k = 0; k < n; k++) {
            if (k % 2 == 1) {
                nums[k] = sorted[j--];
            } else {
                nums[k] = sorted[i--];
            }
        }
    }

    public void wiggleSort2(int[] nums) {//O(N)
        if (nums == null || nums.length < 2) {
            return;
        }
        int n = nums.length;
        int median = findMedian(nums);
        int l = 0, k = 0, r = n - 1;
        while (k <= r) {
            int l1 = mapping(l, n), k1 = mapping(k, n), r1 = mapping(r, n);
            if (nums[k1] > median) {
                swap(nums, k1, l1);
                k++;
                l++;
            } else if (nums[k1] < median) {
                swap(nums, k1, r1);
                r--;
            } else {
                k++;
            }
        }
    }

    int mapping(int i, int n) {
        return (2 * i + 1) % (n | 1);
    }

    int findMedian(int[] nums) {
        int n = nums.length, median = 0, i = 0, j = n - 1;
        while (i <= j) {
            int p = i, a = i + 1, b = j;
            while (a <= b) {
                if (nums[a] < nums[p]) {
                    swap(nums, a, b--);
                } else {
                    a++;
                }
            }
            swap(nums, p, b);
            if (b == (n - 1) / 2) {
                median = nums[b];
                break;
            } else if (b < (n - 1) / 2) {
                i = b + 1;
            } else {
                j = b - 1;
            }
        }
        return median;
    }

    //323. Number of Connected Components in an Undirected Graph
    public int countComponents(int n, int[][] edges) {
        int[] roots = new int[n];
        for (int i = 0; i < n; i++) {
            roots[i] = i;
        }
        for (int[] e : edges) {
            int r1 = findRoot(roots, e[0]);
            int r2 = findRoot(roots, e[1]);
            if (r1 != r2) {
                roots[r1] = r2;
                n--;
            }
        }
        return n;
    }

    int findRoot(int[] roots, int i) {
        while (roots[i] != i) {
            roots[i] = roots[roots[i]];
            i = roots[i];
        }
        return i;
    }

    //322. Coin Change
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        dp[0] = 0;
        for (int sum = 1; sum <= amount; sum++) {
            dp[sum] = Integer.MAX_VALUE;
            for (int i = 0; i < coins.length; i++) {
                if (sum >= coins[i] && dp[sum - coins[i]] >= 0) {
                    dp[sum] = Math.min(dp[sum], dp[sum - coins[i]] + 1);
                }
            }
            if (dp[sum] == Integer.MAX_VALUE) {
                dp[sum] = -1;
            }
        }

        return dp[amount];
    }

    //321. Create Maximum Number
    public int[] maxNumber(int[] ma, int[] na, int k) {
        if (k < 0) {
            return null;
        }
        if (ma == null) {
            ma = new int[0];
        }
        if (na == null) {
            na = new int[0];
        }
        int[] ka = new int[k];
        int m = ma.length;
        int n = na.length;
        for (int i = 0; i < k + 1; i++) {
            if (m < i || n < k - i) {
                continue;
            }
            int[] left = maxNumberGetChunk(ma, i);
            int[] right = maxNumberGetChunk(na, k - i);
            ka = maxNumberMerge(ka, left, right);
        }
        return ka;
    }

    int[] maxNumberGetChunk(int[] nums, int t) {
        int[] res = new int[t];
        int n = nums.length;
        for (int i = 0, len = 0; i < n; i++) {
            while (len > 0 && n - i + len > t && nums[i] > res[len - 1]) {
                len--;
            }
            if (len < t) {
                res[len++] = nums[i];
            }
        }
        return res;
    }

    boolean maxNumberGreater(int[] nums1, int s1, int[] nums2, int s2) {
        for (; s1 < nums1.length && s2 < nums2.length; s1++, s2++) {
            if (nums1[s1] > nums2[s2]) {
                return true;
            }
            if (nums1[s1] < nums2[s2]) {
                return false;
            }
        }
        return s1 != nums1.length;
    }

    int[] maxNumberMerge(int[] ka, int[] left, int[] right) {
        int k = ka.length, m = left.length, n = right.length;
        int i = 0, j = 0, t = 0;
        int[] res = new int[k];
        while (i < m && j < n) {
            if (maxNumberGreater(left, i, right, j)) {
                res[t++] = left[i++];
            } else {
                res[t++] = right[j++];
            }
        }
        while (i < m) {
            res[t++] = left[i++];
        }
        while (j < n) {
            res[t++] = right[j++];
        }
        if (maxNumberGreater(res, 0, ka, 0)) {
            ka = res;
        }
        return ka;
    }

    //320. Generalized Abbreviation
    public List<String> generateAbbreviations(String word) {
        List<String> res = new ArrayList<>();
        if (word != null) {
            generateAbbreviationsHelper(new StringBuilder(), res, word.toCharArray(), 0, 0);
        }
        return res;
    }

    void generateAbbreviationsHelper(StringBuilder sb, List<String> res, char[] c, int i, int n) {
        int len = sb.length();
        if (i >= c.length) {
            if (n != 0) {
                sb.append(n);
            }
            res.add(sb.toString());
        } else {
            generateAbbreviationsHelper(sb, res, c, i + 1, n + 1);
            if (n != 0) {
                sb.append(n);
            }
            generateAbbreviationsHelper(sb.append(c[i]), res, c, i + 1, 0);
        }
        sb.setLength(len);
    }

    //318. Maximum Product of Word Lengths
    public int maxProduct(String[] words) {
        int res = 0;
        if (words != null) {

            int n = words.length;
            int[] dict = new int[n];
            for (int i = 0; i < n; i++) {
                String s1 = words[i];
                for (char c : s1.toCharArray()) {
                    dict[i] |= 1 << (c - 'a');
                }
                for (int j = 0; j < i; j++) {
                    if ((dict[i] & dict[j]) == 0) {
                        res = Math.max(res, s1.length() * words[j].length());
                    }
                }
            }
        }
        return res;
    }

    //317. Shortest Distance from All Buildings
    public int shortestDistance(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return -1;
        }
        int m = grid.length;
        int n = grid[0].length;
        int num = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    num++;
                }
            }
        }

        int[][] dis = new int[m][n];
        int[][] nb = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    if (!shortestDistanceBFS(grid, new boolean[m][n], dis, nb, i, j, num)) {
                        return -1;
                    }
                }
            }
        }

        int res = -1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0 && dis[i][j] != 0 && nb[i][j] == num && (res == -1 || res > dis[i][j])) {
                    res = dis[i][j];
                }
            }
        }
        return res;
    }

    boolean shortestDistanceBFS(int[][] grid, boolean[][] visited, int[][] dis, int[][] nb, int i, int j, int num) {
        Deque<Integer> is = new ArrayDeque<>();
        Deque<Integer> js = new ArrayDeque<>();
        Deque<Integer> ds = new ArrayDeque<>();
        is.add(i);
        js.add(j);
        ds.add(0);
        int[] xx = new int[]{-1, 0, 1, 0};
        int[] yy = new int[]{0, -1, 0, 1};
        int neigh = 0;
        while (is.size() > 0) {
            i = is.poll();
            j = js.poll();
            int d = ds.poll();
            for (int k = 0; k < 4; k++) {
                int x = i + xx[k];
                int y = j + yy[k];
                if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && !visited[x][y]) {
                    if (grid[x][y] == 0) {
                        nb[x][y]++;
                        dis[x][y] += d + 1;
                        is.add(x);
                        js.add(y);
                        ds.add(d + 1);
                        neigh++;
                    } else if (grid[x][y] == 1) {
                        num--;
                    }
                    visited[x][y] = true;
                }
            }
        }
        return num <= 1 && neigh > 0;
    }

    //314. Binary Tree Vertical Order Traversal
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> q = new ArrayDeque<>();
        Deque<Integer> cols = new ArrayDeque<>();
        Map<Integer, List<Integer>> map = new HashMap<>();
        int min = 0, max = 0;
        q.offer(root);
        cols.offer(0);
        while (q.size() > 0) {
            TreeNode node = q.poll();
            int col = cols.poll();
            List<Integer> list = map.get(col);
            if (list == null) {
                list = new ArrayList<>();
                map.put(col, list);
            }
            list.add(node.val);

            if (node.left != null) {
                q.offer(node.left);
                cols.offer(col - 1);
                if (min > col - 1) {
                    min = col - 1;
                }
            }

            if (node.right != null) {
                q.offer(node.right);
                cols.offer(col + 1);
                if (max < col + 1) {
                    max = col + 1;
                }
            }
        }
        for (int i = min; i <= max; i++) {
            res.add(map.get(i));
        }
        return res;
    }

    //313. Super Ugly Number
    public int nthSuperUglyNumber(int n, int[] primes) {
        if (n < 1 || primes == null || primes.length < 1) {
            return 0;
        }
        int m = primes.length;
        int[] idx = new int[m];
        int[] nextVal = new int[m];
        int[] res = new int[n];
        Arrays.fill(nextVal, 1);
        int next = 1;
        for (int i = 0; i < n; i++) {
            res[i] = next;
            next = Integer.MAX_VALUE;
            for (int p = 0; p < m; p++) {
                if (res[i] == nextVal[p]) {
                    nextVal[p] = primes[p] * res[idx[p]++];
                }
                if (next > nextVal[p]) {
                    next = nextVal[p];
                }
            }
        }
        return res[n - 1];
    }

    //312. Burst Balloons
    public int maxCoins(int[] old_nums) {
        int n = old_nums.length + 2;
        if (n < 3) {
            return 0;
        }
        int[] nums = new int[n];
        nums[0] = nums[n - 1] = 1;
        System.arraycopy(old_nums, 0, nums, 1, n - 2);
        int[][] dp = new int[n][n];
        for (int len = 3; len < n + 1; len++) {
            for (int i = 0; i + len < n + 1; i++) {
                int j = i + len - 1;
                for (int k = i + 1; k < j; k++) {
                    dp[i][j] = Math.max(dp[i][j], (dp[i][k] + dp[k][j] + nums[i] * nums[k] * nums[j]));
                }
            }
        }
        return dp[0][n - 1];
    }

    //310. Minimum Height Trees
    public List<Integer> findMinHeightTrees0(int n, int[][] edges) {
        List<Integer> res = new ArrayList<>();

        if (n == 1) {
            res.add(0);
        }
        if (n <= 1) {
            return res;
        }
        List<Set<Integer>> map = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            map.add(new HashSet<>());
        }
        for (int[] edge : edges) {
            map.get(edge[0]).add(edge[1]);
            map.get(edge[1]).add(edge[0]);
        }
        for (int i = 0; i < n; i++) {
            if (map.get(i).size() == 1) {
                res.add(i);
            }
        }
        while (n > 2) {
            n -= res.size();
            List<Integer> list = new ArrayList<>();
            for (int i : res) {
                int j = map.get(i).iterator().next();
                map.get(j).remove(i);
                if (map.get(j).size() == 1) {
                    list.add(j);
                }
            }
            res = list;
        }
        return res;
    }

    //309. Best Time to Buy and Sell Stock with Cooldown
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int pre_buy = Integer.MIN_VALUE, pre_pre_sell = 0, pre_sell = 0;
        for (int i = 0; i < prices.length; i++) {
            int temp_buy = Math.max(pre_pre_sell - prices[i], pre_buy);
            int temp_sell = Math.max(pre_buy + prices[i], pre_sell);
            pre_buy = temp_buy;
            pre_pre_sell = pre_sell;
            pre_sell = temp_sell;
        }
        return Math.max(pre_buy, pre_sell);
    }

    //305. Number of Islands II
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> res = new ArrayList<>();
        if (m < 1 || n < 1) {
            return res;
        }
        UnionFind2D uf = new UnionFind2D(m, n);
        int[][] dir = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
        for (int i = 0; i < positions.length; i++) {
            int x = positions[i][0], y = positions[i][1];
            int p = uf.add(x, y);
            for (int[] d : dir) {
                int q = uf.getID(x + d[0], y + d[1]);
                if (q > 0 && !uf.find(p, q)) {
                    uf.unite(p, q);
                }
            }
            res.add(uf.size());
        }
        return res;
    }

    //https://www.cs.princeton.edu/~rs/AlgsDS07/01UnionFind.pdf
    class UnionFind2D {

        private int[] id;
        private int[] sz;
        private int m, n, count;

        public UnionFind2D(int m, int n) {
            this.count = 0;
            this.n = n;
            this.m = m;
            this.id = new int[m * n + 1];
            this.sz = new int[m * n + 1];
        }

        public int index(int x, int y) {
            return x * n + y + 1;
        }

        public int size() {
            return this.count;
        }

        public int getID(int x, int y) {
            if (0 <= x && x < m && 0 <= y && y < n) {
                return id[index(x, y)];
            }
            return 0;
        }

        public int add(int x, int y) {
            int i = index(x, y);
            id[i] = i;
            sz[i] = 1;
            ++count;
            return i;
        }

        public boolean find(int p, int q) {
            return root(p) == root(q);
        }

        public void unite(int p, int q) {
            int i = root(p), j = root(q);
            if (sz[i] < sz[j]) { //weighted quick union
                id[i] = j;
                sz[j] += sz[i];
            } else {
                id[j] = i;
                sz[i] += sz[j];
            }
            --count;
        }

        private int root(int i) {
            for (; i != id[i]; i = id[i]) {
                id[i] = id[id[i]]; //path compression
            }
            return i;
        }
    }

    //302. Smallest Rectangle Enclosing Black Pixels
    public int minArea(char[][] image, int x, int y) {
        int m = image.length, n = image[0].length;
        int left = searchColumn(0, y, 0, m, true, image);
        int right = searchColumn(y + 1, n, 0, m, false, image);
        int top = searchRow(0, x, left, right, true, image);
        int bottom = searchRow(x + 1, m, left, right, false, image);
        return (right - left) * (bottom - top);
    }

    int searchColumn(int i, int j, int top, int bottom, boolean left, char[][] image) {
        if (i >= j) {
            return i;
        }
        int k = top, mid = (i + j) / 2;
        while (k < bottom && image[k][mid] == '0') {
            k++;
        }
        if (k < bottom == left) {
            j = mid;
        } else {
            i = mid + 1;
        }
        return searchColumn(i, j, top, bottom, left, image);
    }

    int searchRow(int i, int j, int left, int right, boolean top, char[][] image) {
        if (i >= j) {
            return i;
        }
        int k = left, mid = (i + j) / 2;
        while (k < right && image[mid][k] == '0') {
            k++;
        }
        if (k < right == top) {
            j = mid;
        } else {
            i = mid + 1;
        }
        return searchRow(i, j, left, right, top, image);
    }

    //300. Longest Increasing Subsequence
    //O(N^2)
    public int lengthOfLIS0(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        int res = 0;
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    break;
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    //O(Nlog(N))
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        int len = 0;
        for (int i = 0; i < n; i++) {
            int p = Arrays.binarySearch(dp, 0, len, nums[i]);
            if (p < 0) {
                p = -(p + 1);
            }
            dp[p] = nums[i];
            if (p == len) {
                len++;
            }
        }
        return len;
    }

    //298. Binary Tree Longest Consecutive Sequence
    public int longestConsecutive(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int[] res = new int[1];
        helper(root, res, 0, root.val);
        return res[0];
    }

    void helper(TreeNode root, int[] res, int cur, int target) {
        if (root == null) {
            return;
        }
        if (root.val == target) {
            cur++;
        } else {
            cur = 1;
        }
        res[0] = Math.max(res[0], cur);
        helper(root.left, res, cur, root.val + 1);
        helper(root.right, res, cur, root.val + 1);
    }

    //295. Find Median from Data Stream
    class MedianFinder {

        PriorityQueue<Integer> maxHeap;
        PriorityQueue<Integer> minHeap;
        double median;

        public MedianFinder() {
            median = 0.0;
            maxHeap = new PriorityQueue<>(10, new Comparator<Integer>() {
                public int compare(Integer i1, Integer i2) {
                    return i2 - i1;
                }
            });
            minHeap = new PriorityQueue<>(10, new Comparator<Integer>() {
                public int compare(Integer i1, Integer i2) {
                    return i1 - i2;
                }
            });
        }

        // Adds a number into the data structure.
        public void addNum(int num) {
            int minSize = minHeap.size(), maxSize = maxHeap.size();
            if (num > median) {
                minHeap.offer(num);
                if (minSize > maxSize) {
                    maxHeap.offer(minHeap.poll());
                }
                median = minHeap.peek();
            } else {
                maxHeap.offer(num);
                if (minSize < maxSize) {
                    minHeap.offer(maxHeap.poll());
                }
                median = maxHeap.peek();
            }
            if (minSize != maxSize) {
                median = (minHeap.peek() + maxHeap.peek()) / 2.0;
            }
        }

        // Returns the median of current data stream
        public double findMedian() {
            return median;
        }
    }

    //294. Flip Game II
    public boolean canWin(String s) {
        if (s == null || s.length() < 2) {
            return false;
        }
        Map<String, Boolean> res = new HashMap<>();
        return canWin(s, res);
    }

    public boolean canWin(String s, Map<String, Boolean> res) {
        char[] chs = s.toCharArray();
        Boolean flag = res.get(s);
        if (flag != null) {
            return flag;
        }
        for (int i = 0; i < chs.length - 1; i++) {
            if (chs[i] == '+' && chs[i + 1] == '+') {
                chs[i] = chs[i + 1] = '-';
                boolean win = !canWin(new String(chs), res);
                chs[i] = chs[i + 1] = '+';
                if (win) {
                    res.put(s, win);
                    return true;
                }
            }
        }
        res.put(s, false);
        return false;
    }

    //293. Flip Game
    public List<String> generatePossibleNextMoves(String s) {
        List<String> res = new ArrayList<>();
        if (s == null || s.length() < 2) {
            return res;
        }
        char[] chs = s.toCharArray();
        for (int i = 0; i < chs.length - 1; i++) {
            if (chs[i] == '+' && chs[i + 1] == '+') {
                chs[i] = chs[i + 1] = '-';
                res.add(new String(chs));
                chs[i] = chs[i + 1] = '+';
            }
        }
        return res;
    }

    //289. Game of Life
    public void gameOfLife(int[][] board) {
        if (board == null) {
            return;
        }
        int m = board.length, n = board[0].length;
        int[][] dir = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}, {-1, 1}, {-1, -1}, {1, -1}, {1, 1}};
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int num = 0;
                for (int k = 0; k < dir.length; k++) {
                    int x = i + dir[k][0], y = j + dir[k][1];
                    if (x >= 0 && x < m && y >= 0 && y < n && (board[x][y] & 1) == 1) {
                        num++;
                    }
                }
                if ((board[i][j] & 1) == 1 && (num == 2 || num == 3)) {
                    board[i][j] = 3;
                } else if (num == 3) {
                    board[i][j] = 2;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] >>= 1;  // Get the 2nd state.
            }
        }
    }

    //288. Unique Word Abbreviation
    public class ValidWordAbbr {

        private Map<String, String> map;

        public ValidWordAbbr(String[] dictionary) {
            map = new HashMap<>();
            if (dictionary != null) {
                for (String word : dictionary) {
                    String abbr = abbreviate(word);
                    String value = map.get(abbr);
                    if (value != null && !value.equals(word)) {
                        map.put(abbr, "");
                    } else {
                        map.put(abbr, word);
                    }
                }
            }
        }

        private String abbreviate(String word) {
            if (word.length() > 2) {
                return word.charAt(0) + String.valueOf(word.length() - 2) + word.charAt(word.length() - 1);
            } else {
                return word;
            }
        }

        public boolean isUnique(String word) {
            if (word != null) {
                String value = map.get(abbreviate(word));
                if (value == null || value.equals(word)) {
                    return true;
                }
            }
            return false;
        }
    }

    //281. Zigzag Iterator
    public class ZigzagIterator {

        Deque<Iterator<Integer>> queue;

        public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
            queue = new ArrayDeque<>();
            if (!v1.isEmpty()) {
                queue.offer(v1.iterator());
            }
            if (!v2.isEmpty()) {
                queue.offer(v2.iterator());
            }
        }

        public int next() {
            Iterator<Integer> it = queue.poll();
            int val = it.next();
            if (it.hasNext()) {
                queue.offer(it);
            }
            return val;
        }

        public boolean hasNext() {
            return queue.size() > 0;
        }

    }

    //280. Wiggle Sort
    public void wiggleSort(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }
        int n = nums.length;
        for (int i = 1; i < n; i++) {
            int a = nums[i - 1];
            if ((i % 2 == 1 && a > nums[i]) || (i % 2 == 0 && a < nums[i])) {
                nums[i - 1] = nums[i];
                nums[i] = a;
            }

        }
    }

    //279. Perfect Squares
    public int numSquares(int n) {
        if (n < 4) {
            return n;
        }
        int[] dp = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            if (i < 4) {
                dp[i] = i;
            } else {
                int k = (int) Math.sqrt(i + 0d);
                dp[i] = dp[i - 1] + 1;
                for (int j = k; j > 1; j--) {
                    if (dp[i] > dp[i - (j * j)] + 1) {
                        dp[i] = dp[i - j * j] + 1;
                    }
                }
            }
        }
        return dp[n];
    }

    //276. Paint Fence
    public int numWays(int n, int k) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return k;
        }
        int same = k;
        int diff = k * (k - 1);
        for (int i = 3; i < n + 1; i++) {
            int newSame = diff;
            diff = (same + diff) * (k - 1);
            same = newSame;
        }
        return same + diff;
    }

    //274. H-Index
    public int hIndex(int[] citations) {
        if (citations == null || citations.length < 1) {
            return 0;
        }
        int n = citations.length;
        int[] count = new int[n + 1];
        for (int i = 0; i < n; i++) {
            if (citations[i] > n) {
                count[n]++;
            } else {
                count[citations[i]]++;
            }
        }
        int t = 0;
        for (int i = n; i >= 0; i--) {
            t += count[i];
            if (t >= i) {
                return i;
            }
        }
        return 0;
    }

    //275. H-Index II
    public int hIndex2(int[] citations) {
        if (citations == null || citations.length < 1) {
            return 0;
        }
        int n = citations.length;
        int left = 0, right = n - 1, res = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (citations[mid] >= (n - mid)) {
                res = n - mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }

    //272. Closest Binary Search Tree Value II
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> res = new LinkedList<>();
        if (k == 0) {
            return res;
        }
        helper(root, target, k, res);
        return res;
    }

    void helper(TreeNode root, double target, int k, List<Integer> res) {
        if (root == null) {
            return;
        }
        helper(root.left, target, k, res);
        if (res.size() < k) {
            res.add(root.val);
        } else if (Math.abs(root.val - target) < Math.abs(res.get(0) - target)) {
            res.remove(0);
            res.add(root.val);
        } else {
            return;
        }
        helper(root.right, target, k, res);
    }

    //271. Encode and Decode Strings
    public class Codec {

        // Encodes a list of strings to a single string.
        public String encode(List<String> strs) {
            String encoded_str = null;
            if (strs != null) {
                StringBuilder sb = new StringBuilder();
                for (String s : strs) {
                    sb.append(s.length())
                            .append('/')
                            .append(s);
                }
                encoded_str = sb.toString();
            }
            return encoded_str;
        }

        // Decodes a single string to a list of strings.
        public List<String> decode(String s) {
            List<String> res = null;
            if (s != null) {
                res = new ArrayList<>();
                int i = 0;
                while (i < s.length()) {
                    int slash = s.indexOf('/', i);
                    int size = Integer.valueOf(s.substring(i, slash));
                    res.add(s.substring(slash + 1, slash + size + 1));
                    i = slash + size + 1;
                }
            }
            return res;
        }
    }

    //270. Closest Binary Search Tree Value
    public int closestValue(TreeNode root, double target) {
        int a = root.val;
        TreeNode child = target > a ? root.right : root.left;
        if (child == null) {
            return a;
        }
        int b = closestValue(child, target);
        return Math.abs(a - target) < Math.abs(b - target) ? a : b;
    }

    //269. Alien Dictionary
    public String alienOrder(String[] words) { //["wrt","wrf","er","ett","rftt"],["zy","zx"],["aac","aabb","aaba"],["qjatu","ekp","daysdbi","ntbjwvta"]
        if (words == null || words.length < 1) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Map<Character, Set<Character>> map = new HashMap<>();
        int[] degree = new int[26];
        int n = words.length;
        for (String word : words) {
            for (char c : word.toCharArray()) {
                degree[c - 'a'] = 1;
            }
        }
        for (int i = 0; i < n - 1; i++) {//max=4,2
            int len = Math.min(words[i].length(), words[i + 1].length());
            for (int j = 0; j < len; j++) {//i=0,zy
                char c1 = words[i].charAt(j);
                char c2 = words[i + 1].charAt(j);
                if (c1 != c2) {
                    Set<Character> set = map.get(c1);
                    if (set == null) {
                        set = new HashSet<>();
                    }
                    if (!set.contains(c2)) {
                        set.add(c2);
                        degree[c2 - 'a']++;
                        map.put(c1, set);
                    }
                    break;
                }
            }
        }

        Deque<Character> q = new ArrayDeque<>();
        int size = 0;
        for (int i = 0; i < degree.length; i++) {
            if (degree[i] > 0) {
                size++;
            }
            if (degree[i] == 1) {
                q.offer((char) (i + 'a'));
            }
        }
        while (q.size() > 0) {
            char c = q.poll();
            sb.append(c);
            Set<Character> set = map.get(c);
            if (set != null) {
                for (char v : set) {
                    degree[v - 'a']--;
                    if (degree[v - 'a'] == 1) {
                        q.offer(v);
                    }
                }
            }
        }
        if (sb.length() != size) {
            return "";
        }
        return sb.toString();
    }

    //268. Missing Number
    public int missingNumber(int[] nums) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        int n = nums.length;
        int res = 0;
        for (int i = 0; i < n; i++) {
            res ^= (i ^ nums[i]);
        }
        res ^= n;
        return res;
    }

    //266. Palindrome Permutation
    public boolean canPermutePalindrome(String s) {
        if (s == null || s.length() < 1) {
            return false;
        }
        char[] chs = s.toCharArray();
        int[] ac = new int[256];
        for (int i = 0; i < chs.length; i++) {
            ac[chs[i]]++;
        }
        int count = 0;
        for (int i : ac) {
            if (i % 2 != 0) {
                count++;
            }
        }
        return count < 2;
    }

    //264. Ugly Number II
    public int nthUglyNumber(int n) {
        if (n < 1) {
            return 0;
        }
        int[] res = new int[n];
        int next_2 = 2, next_3 = 3, next_5 = 5;
        int index_2 = 0, index_3 = 0, index_5 = 0;
        res[0] = 1;
        for (int i = 1; i < n; i++) {
            int min = Math.min(next_2, Math.min(next_3, next_5));
            res[i] = min;
            if (min == next_2) {
                next_2 = 2 * res[++index_2];
            }
            if (min == next_3) {
                next_3 = 3 * res[++index_3];
            }
            if (min == next_5) {
                next_5 = 5 * res[++index_5];
            }
        }
        return res[n - 1];
    }

    //263. Ugly Number
    public boolean isUgly(int num) {
        if (num < 1) {
            return false;
        }
        if (num == 1) {
            return true;
        }
        int[] primes = new int[]{2, 3, 5};
        for (int i = 0; i < primes.length; i++) {
            while (num % primes[i] == 0) {
                num /= primes[i];
            }
        }
        return num == 1;
    }

    //261. Graph Valid Tree
    class UnionFind {

        int[] graph;

        public UnionFind(int n) {
            graph = new int[n];
            for (int i = 0; i < n; i++) {
                graph[i] = i;
            }
        }

        void union(int x, int y) {
            int rx = find(x);
            int ry = find(y);
            if (rx != ry) {
                graph[rx] = ry;
            }
        }

        int find(int x) {
            int root = graph[x];
            while (root != graph[root]) {
                root = graph[root];
            }
            int temp = -1;
            int parent = graph[x];
            while (parent != graph[parent]) {
                temp = graph[parent];
                graph[parent] = root;
                parent = temp;
            }
            return root;
        }

    }

    public boolean validTree(int n, int[][] edges) {
        if (n - 1 != edges.length) {
            return false;
        }
        UnionFind uf = new UnionFind(n);
        for (int[] edge : edges) {
            int r1 = uf.find(edge[0]);
            int r2 = uf.find(edge[1]);
            if (r1 == r2) {
                return false;
            }
            uf.union(edge[0], edge[1]);
        }
        return true;
    }

    //259. 3Sum Smaller
    public int threeSumSmaller(int[] nums, int target) {
        if (nums == null || nums.length < 3) {
            return 0;
        }
        Arrays.sort(nums);
        int res = 0;
        int n = nums.length;
        for (int i = 0; i < n - 2; i++) {
            int lo = i + 1, hi = n - 1;
            while (lo < hi) {
                int val = nums[lo] + nums[hi] + nums[i];
                if (val < target) {
                    res += (hi - lo);
                    lo++;
                } else {
                    hi--;
                }
            }
        }
        return res;
    }

    //257. Binary Tree Paths
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        String path = "";
        helper(root, path, res);
        return res;
    }

    void helper(TreeNode root, String path, List<String> res) {
        if (root == null) {
            return;
        }
        path += root.val;
        if (root.left == null && root.right == null) {
            res.add(path);
            return;
        }
        path += "->";
        helper(root.left, path, res);
        helper(root.right, path, res);
    }

    //253. Meeting Rooms II 
    public int minMeetingRooms(Interval[] meets) {
        int n = meets.length;
        int[] starts = new int[n];
        int[] ends = new int[n];
        for (int i = 0; i < n; i++) {
            starts[i] = meets[i].start;
            ends[i] = meets[i].end;
        }
        Arrays.sort(starts);
        Arrays.sort(ends);
        int res = 0, ei = 0;
        for (int i = 0; i < n; i++) {
            if (starts[i] < ends[ei]) {
                res++;
            } else {
                ei++;
            }
        }
        return res;
    }

    //252. Meeting Rooms
    public boolean canAttendMeetings(Interval[] meets) {
        Arrays.sort(meets, new Comparator<Interval>() {
            @Override
            public int compare(Interval i1, Interval i2) {
                return i1.start - i2.start;
            }
        });
        for (int i = 0; i < meets.length - 1; i++) {
            if (meets[i].end > meets[i + 1].start) {
                return false;
            }
        }
        return true;
    }

    //242. Valid Anagram
    public boolean isAnagram(String s, String t) {
        if (s == null || t == null || s.length() != t.length()) {
            return false;
        }
        int[] alphabets = new int[256];
        char[] sc = s.toCharArray();
        char[] tc = t.toCharArray();
        for (char c : sc) {
            alphabets[c]++;
        }
        for (char c : tc) {
            if (alphabets[c] > 0) {
                alphabets[c]--;
            } else {
                return false;
            }
        }
        return true;
    }

    //240. Search a 2D Matrix II
    public boolean searchMatrix2(int[][] matrix, int target) {
        if (matrix == null || matrix.length < 1 || matrix[0].length < 1) {
            return false;
        }
        int m = matrix.length, n = matrix[0].length;
        int i = 0, j = n - 1;
        while (i >= 0 && i < m && j >= 0 && j < n) {
            if (matrix[i][j] == target) {
                return true;
            } else if (matrix[i][j] > target) {
                j--;
            } else {
                i++;
            }
        }
        return false;
    }

    //236. Lowest Common Ancestor of a Binary Tree
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        return left == null ? right : right == null ? left : root;
    }

    //235. Lowest Common Ancestor of a Binary Search Tree
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == null || q == null) {
            return null;
        }
        if (root.val < p.val && root.val < q.val) {
            return lowestCommonAncestor(root.right, p, q);
        } else if (root.val > p.val && root.val > q.val) {
            return lowestCommonAncestor(root.left, p, q);
        }
        return root;
    }

    //231. Power of Two
    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }

    //230. Kth Smallest Element in a BST 
    public int kthSmallest(TreeNode root, int k) {
        TreeNodeWithCount rootWithCount = buildTreeWithCount(root);
        return kthSmallest(rootWithCount, k);
    }

    private TreeNodeWithCount buildTreeWithCount(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNodeWithCount rootWithCount = new TreeNodeWithCount(root.val);
        rootWithCount.left = buildTreeWithCount(root.left);
        rootWithCount.right = buildTreeWithCount(root.right);
        if (rootWithCount.left != null) {
            rootWithCount.count += rootWithCount.left.count;
        }
        if (rootWithCount.right != null) {
            rootWithCount.count += rootWithCount.right.count;
        }
        return rootWithCount;
    }

    private int kthSmallest(TreeNodeWithCount root, int k) {
        int count = 0;
        if (root.left != null) {
            count = root.left.count;
        }
        if (k <= count) {
            return kthSmallest(root.left, k);
        } else if (k > count + 1) {
            return kthSmallest(root.right, k - 1 - count); // 1 is counted as current node
        }

        return root.val;
    }

    class TreeNodeWithCount {

        int val;
        int count;
        TreeNodeWithCount left;
        TreeNodeWithCount right;

        TreeNodeWithCount(int x) {
            val = x;
            count = 1;
        }
    ;

    }
    
    //228. Summary Ranges
    public List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<>();
        if (nums == null || nums.length < 1) {
            return res;
        }
        int n = nums.length;
        for (int i = 0, k; i < n; i = k + 1) {
            k = helper(nums, i, n);
            if (i == k) {
                res.add(nums[i] + "");
            } else {
                res.add(nums[i] + "->" + nums[k]);
            }
        }
        return res;
    }

    int helper(int[] nums, int l, int r) {
        while (l + 1 < r) {
            int m = (l + r) / 2;
            if (nums[m] - nums[l] == m - l) {
                l = m;
            } else {
                r = m;
            }
        }
        return l;
    }

    //218. The Skyline Problem 
    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> res = new ArrayList<>();
        List<int[]> heights = new ArrayList<>();
        if (buildings == null || buildings.length < 1) {
            return res;
        }
        for (int[] b : buildings) {
            heights.add(new int[]{b[0], -b[2]});
            heights.add(new int[]{b[1], b[2]});
        }
        Collections.sort(heights, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                if (a[0] != b[0]) {
                    return a[0] - b[0];
                }
                return a[1] - b[1];
            }
        });
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(0, 1);
        int pre = 0;
        for (int[] h : heights) {
            if (h[1] < 0) {
                Integer count = map.get(-h[1]);
                if (count == null) {
                    map.put(-h[1], 1);
                } else {
                    map.put(-h[1], count + 1);
                }
            } else {
                Integer count = map.get(h[1]);
                if (count == 1) {
                    map.remove(h[1]);
                } else {
                    map.put(h[1], count - 1);
                }
            }
            int cur = map.lastKey();
            if (pre != cur) {
                res.add(new int[]{h[0], cur});
                pre = cur;
            }
        }
        return res;
    }

    //206. Reverse Linked List
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode pre = head;
        ListNode next = pre.next;
        head.next = null;
        while (next != null) {
            ListNode temp = next.next;
            next.next = pre;
            pre = next;
            next = temp;
        }
        return pre;
    }

    //204. Count Primes
    public int countPrimes(int n) {
        if (n < 3) {
            return 0;
        }
        boolean[] marked = new boolean[n];
        //int count = n/2;

        for (int i = 3; i * i < n; i += 2) {
            if (marked[i]) {
                continue;
            }
            for (int j = i * i; j < n; j += 2 * i) {
                marked[j] = true;
            }
            /*for(int j=i*i; j<n; j+=2*i){
             if(!marked[j]){
             marked[j] = true;
             count--;
             }
             }*/
        }

        int count = 1;
        for (int i = 3; i < n; i += 2) {
            if (!marked[i]) {
                count++;
            }
        }
        return count;
    }

    //200. Number of Islands
    public int numIslands(char[][] grid) {
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    traversalIslands(grid, i, j);
                    res++;
                }
            }
        }
        return res;
    }

    void traversalIslands(char[][] a, int x, int y) {
        if (x < 0 || x >= a.length || y < 0 || y >= a[0].length || a[x][y] != '1') {
            return;
        }
        a[x][y] = '0';
        traversalIslands(a, x - 1, y);
        traversalIslands(a, x, y - 1);
        traversalIslands(a, x + 1, y);
        traversalIslands(a, x, y + 1);
    }

    //199. Binary Tree Right Side View
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        rightSideViewHelper(root, 0, res);
        return res;
    }

    void rightSideViewHelper(TreeNode root, int cur, List<Integer> res) {
        if (root == null) {
            return;
        }
        if (res.size() <= cur) {
            res.add(root.val);
        }
        rightSideViewHelper(root.right, cur + 1, res);
        rightSideViewHelper(root.left, cur + 1, res);
    }

    //186. Reverse Words in a String II(Locked)
    public void reverseWords(char[] s) {
        int n = s.length;
        reverseChar(0, n - 1, s);
        int l = 0, r = 0;
        while (r < n) {
            if (s[r] == ' ') {
                reverseChar(l, r - 1, s);
                l = r + 1;
            }
            r++;
        }
        if (l < n) {
            reverseChar(l, n - 1, s);
        }
    }

    void reverseChar(int l, int r, char[] s) {
        while (l < r) {
            char c = s[l];
            s[l] = s[r];
            s[r] = c;
            l++;
            r--;
        }
    }

    //170. Two Sum III - Data structure design
    public class TwoSum {

        private Map<Integer, Boolean> nums;
        private List<Integer> unique;

        public TwoSum() {
            nums = new HashMap<>();
            unique = new ArrayList<>();
        }

        // Add the number to an internal data structure.
        public void add(int number) {
            Boolean flag = nums.get(number);
            if (flag != null) {
                flag = true;
            } else {
                flag = false;
                unique.add(number);
            }
            nums.put(number, flag);
        }

        // Find if there exists any pair of numbers which sum is equal to the value.
        public boolean find(int value) {
            for (int i = 0; i < unique.size(); i++) {
                int first = unique.get(i);
                int second = value - first;
                Boolean flag = nums.get(second);
                if (flag != null && (first != second || flag)) {
                    return true;
                }
            }
            return false;
        }
    }

    //167. Two Sum II - Input array is sorted(Locked)
    public int[] twoSum2(int[] nums, int t) {
        int[] res = new int[2];
        int l = 0, r = nums.length - 1, sum = 0;
        while (l < r) {
            sum = nums[l] + nums[r];
            if (sum > t) {
                r--;
            } else if (sum < t) {
                l++;
            } else {
                res[0] = l + 1;
                res[1] = r + 1;
                break;
            }
        }
        return res;
    }

    //166. Fraction to Recurring Decimal 
    public String fractionToDecimal(int num, int den) {
        String neg = "-";
        if (num > 0 && den > 0 || num < 0 && den < 0) {
            neg = "";
        }
        if (den == 0) {
            return "";
        }
        if (num == 0) {
            return "0";
        }
        long n = Math.abs((long) num);
        long d = Math.abs((long) den);
        Map<Long, Integer> idx = new HashMap<>();
        String res = "" + n / d;
        long r = n % d;
        if (r != 0) {
            res += ".";
        }
        while (r != 0) {
            Integer i = idx.get(r);
            if (i != null) {
                res = res.substring(0, i) + "(" + res.substring(i, res.length()) + ")";
                break;
            }
            idx.put(r, res.length());
            res += (r * 10) / d;
            r = (r * 10) % d;
        }
        return neg + res;
    }

    //163. Missing Ranges 
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> res = new ArrayList<>();
        for (int n : nums) {
            int cur = n - 1;
            if (cur == lower) {
                res.add(lower + "");
            } else if (lower < cur) {
                res.add(lower + "->" + cur);
            }
            lower = n + 1;
        }
        if (upper == lower) {
            res.add(upper + "");
        } else if (upper > lower) {
            res.add(lower + "->" + upper);
        }
        return res;

    }

    //162. Find Peak Element
    public int findPeakElement(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int m = (l + r) / 2;
            if (nums[m] < nums[m + 1]) {
                l = m + 1;
            } else {
                r = m;
            }
        }
        return l;
    }

    //160. Intersection of Two Linked Lists
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode meet = null, cur = headA, slow = headB, fast = headB;
        while (cur != null) {
            if (cur.next == null) {
                cur.next = headA;
                break;
            }
            cur = cur.next;
        }
        while (fast != null && fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                for (fast = headB; fast != slow; fast = fast.next, slow = slow.next);
                meet = slow;
                break;
            }
        }
        if (cur != null) {
            cur.next = null;
        }
        return meet;
    }

    //155. Min Stack
    class MinStack {

        Deque<int[]> stack;
        int[] pair;

        MinStack() {
            stack = new ArrayDeque<>();
        }

        public void push(int x) {
            pair = new int[]{x, x};
            if (stack.size() > 0 && x > getMin()) {
                pair[1] = getMin();
            }
            stack.push(pair);
        }

        public void pop() {
            stack.pop();
        }

        public int top() {
            return stack.peek()[0];
        }

        public int getMin() {
            return stack.peek()[1];
        }
    }

    //146. LRU Cache
    public class LRUCache {

        final class Node {

            int key, value;
            Node pre, next;

            public Node(int key, int value) {
                this.key = key;
                this.value = value;
                this.pre = null;
                this.next = null;
            }
        }
        private Node head, tail;
        private Map<Integer, Node> map;
        private int count, max;

        public LRUCache(int capacity) {
            this.max = capacity;
            this.count = 0;
            map = new HashMap<>();
        }

        public int get(int key) {
            Node v = map.get(key);
            if (v != null) {
                prioritized(v);
                return v.value;
            }
            return -1;
        }

        public void set(int key, int value) {
            if (!map.containsKey(key)) {
                Node n = new Node(key, value);
                if (count >= max) {
                    map.remove(head.key);
                    removeNode(head);
                }
                addNode(n);
                map.put(key, n);
            } else {
                Node n = map.get(key);
                n.value = value;
                prioritized(n);
            }

        }

        private void removeNode(Node n) {
            if (n.pre != null) {
                n.pre.next = n.next;
                if (n.next != null) {
                    n.next.pre = n.pre;
                } else {
                    tail = n.pre;
                }
                n.pre = null;
                n.next = null;
            } else {
                head = n.next;
                if (head != null) {
                    head.pre = null;
                }
                n.next = null;
            }
            count--;
        }

        private void addNode(Node n) {
            if (head == null) {
                head = n;
            }
            if (tail != null) {
                tail.next = n;
                n.pre = tail;
            }
            tail = n;
            count++;
        }

        private void prioritized(Node n) {
            if (count < 2) {
                return;
            }
            removeNode(n);
            addNode(n);
        }
    }

    //102. Binary Tree Level Order Traversal
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        levelOrderHelper(root, 0, res);
        return res;
    }

    void levelOrderHelper(TreeNode root, int cur, List<List<Integer>> res) {
        if (root == null) {
            return;
        }
        if (res.size() == cur) {
            res.add(new ArrayList<>());
        }
        res.get(cur).add(root.val);
        levelOrderHelper(root.left, cur + 1, res);
        levelOrderHelper(root.right, cur + 1, res);
    }

    //98. Validate Binary Search Tree
    public boolean isValidBST(TreeNode root) {
        int[] last = new int[]{0};
        boolean[] isFirst = new boolean[]{true};
        return helper(root, isFirst, last);
    }

    public boolean helper(TreeNode root, boolean[] isFirst, int[] last) {
        if (root == null) {
            return true;
        }
        if (!helper(root.left, isFirst, last)) {
            return false;
        }

        if (isFirst[0]) {
            isFirst[0] = false;
        } else if (last[0] >= root.val) {
            return false;
        }

        last[0] = root.val;
        return helper(root.right, isFirst, last);

    }

    //89. Gray Code
    public List<Integer> grayCode(int n) {
        List<Integer> res = new ArrayList<>();
        res.add(0);
        for (int i = 0; i < n; i++) {
            int val = 1 << i;
            for (int j = res.size() - 1; j >= 0; j--) {
                res.add(val + res.get(j));
            }
        }
        return res;
    }

    //78. Subsets
    public List<List<Integer>> subsets(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        List<Integer> path = new ArrayList<>(n * 2);
        List<List<Integer>> res = new LinkedList<>();
        subsetsHelper(0, nums, path, res);
        return res;
    }

    void subsetsHelper(int cur, int[] nums, List<Integer> path, List<List<Integer>> res) {
        res.add(new ArrayList<>(path));
        if (path.size() < nums.length) {
            for (int i = cur; i < nums.length; i++) {
                path.add(nums[i]);
                subsetsHelper(i + 1, nums, path, res);
                path.remove(path.size() - 1);
            }
        }
    }

    class Interval {

        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }
    }

    //Sorting Questions:74,4,75
    //74. Search a 2D Matrix
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length < 1 || matrix[0].length < 1) {
            return false;
        }
        int m = matrix.length, n = matrix[0].length;

        int mr = binarySearch(matrix, target, 0, m - 1, n - 1, true);
        if (mr < m) {
            if (matrix[mr][n - 1] == target) {
                return true;
            }
            int mc = binarySearch(matrix, target, 0, n - 1, mr, false);
            if (mc < n && matrix[mr][mc] == target) {
                return true;
            }
        }
        return false;
    }

    int binarySearch(int[][] matrix, int target, int low, int high, int roc, boolean isCol) {
        while (low < high) {
            int m = (low + high) / 2;
            if (isCol && matrix[m][roc] == target || (!isCol && matrix[roc][m] == target)) {
                return m;
            } else if (isCol && matrix[m][roc] > target || (!isCol && matrix[roc][m] > target)) {
                high = m;
            } else {
                low = m + 1;
            }
        }
        return low;
    }

    //57. Insert Interval 
    public List<Interval> insert(List<Interval> ins, Interval newIn) {
        List<Interval> res = new ArrayList<>();
        if (ins == null || ins.size() < 1 || ins.get(0).start >= newIn.start && ins.get(ins.size() - 1).end <= newIn.end) {
            res.add(newIn);
            return res;
        }
        int pos = 0;
        for (int i = 0; i < ins.size(); i++) {
            Interval in = ins.get(i);
            if (in.end < newIn.start) {
                res.add(in);
                pos++;
            } else if (in.start > newIn.end) {
                res.add(in);
            } else {
                newIn.start = Math.min(newIn.start, in.start);
                newIn.end = Math.max(newIn.end, in.end);
            }
        }
        res.add(pos, newIn);
        return res;
    }

    //56. Merge Intervals
    public List<Interval> merge(List<Interval> intervals) {
        if (intervals == null || intervals.size() < 2) {
            return intervals;
        }
        Collections.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval i1, Interval i2) {
                return i1.start - i2.start;
            }
        });
        List<Interval> res = new ArrayList<>();
        Interval temp = null;
        for (Interval in : intervals) {
            if (temp == null) {
                temp = in;
            } else if (temp.end >= in.start) {
                temp.end = Math.max(temp.end, in.end);
            } else {
                res.add(temp);
                temp = in;
            }
        }
        res.add(temp);
        return res;
    }

    //49. Group Anagrams
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null) {
            return null;
        }
        List<List<String>> res = new ArrayList<>();
        Arrays.sort(strs);
        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < strs.length; i++) {
            char[] sc = strs[i].toCharArray();
            Arrays.sort(sc);
            String key = new String(sc);
            Integer index = map.get(key);
            if (index == null) {
                res.add(new ArrayList<>());
                index = res.size() - 1;
                map.put(key, index);
            }
            res.get(index).add(strs[i]);
        }
        return res;
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

    //44. Wildcard Matching
    public boolean isMatch2(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        int m = s.length(), n = p.length();
        char[] sc = s.toCharArray(), pc = p.toCharArray();
        int i = 0, j = 0, star = -1, mark = 0;
        while (i < m) {
            if (j < n && (sc[i] == pc[j] || pc[j] == '?')) {
                i++;
                j++;
            } else if (j < n && pc[j] == '*') {
                star = j;
                mark = i;
                j++;
            } else if (star != -1) {
                mark++;
                i = mark;
                j = star + 1;
            } else {
                return false;
            }
        }
        while (j < n && pc[j] == '*') {
            j++;
        }
        return j == n;
    }

    //26. Remove Duplicates from Sorted Array
    public int removeDuplicates(int[] nums) {
        if (nums.length < 1) {
            return 0;
        }
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }

    //23. Merge k Sorted Lists
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        return mergeKLists(lists, 0, lists.length - 1);
    }

    ListNode mergeKLists(ListNode[] lists, int l, int r) {
        if (l == r) {
            return lists[l];
        } else if (l < r) {
            int mid = (r - l) / 2 + l;
            ListNode left = mergeKLists(lists, l, mid);
            ListNode right = mergeKLists(lists, mid + 1, r);
            return mergeTwoLists(left, right);
        } else {
            return null;
        }
    }

    //21. Merge Two Sorted Lists
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode head = new ListNode(0);
        ListNode cur = head;
        while (l1 != null && l2 != null) {
            if (l1.val >= l2.val) {
                cur.next = l2;
                l2 = l2.next;
            } else {
                cur.next = l1;
                l1 = l1.next;
            }
            cur = cur.next;
        }
        if (l1 != null) {
            cur.next = l1;
        } else if (l2 != null) {
            cur.next = l2;
        }
        return head.next;
    }

    //20. Valid Parentheses
    public boolean isValid(String s) {
        if (s == null || s.length() < 1) {
            return true;
        }
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else if (stack.size() == 0) {
                return false;
            } else if (stack.peek() == '(' && c == ')' || stack.peek() == '[' && c == ']' || stack.peek() == '{' && c == '}') {
                stack.pop();
            } else {
                return false;
            }
        }
        return stack.size() == 0;
    }

    //16. 3Sum Closest
    public int threeSumClosest(int[] nums, int target) {
        if (nums == null || nums.length < 3) {
            return 0;
        }
        Arrays.sort(nums);
        int res = nums[0] + nums[1] + nums[2];
        int n = nums.length;
        for (int i = 0; i < n - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int lo = i + 1, hi = n - 1;
            while (lo < hi) {
                int val = nums[lo] + nums[hi] + nums[i];
                if (Math.abs(target - res) > Math.abs(target - val)) {
                    res = val;
                }
                if (val < target) {
                    lo++;
                } else if (val > target) {
                    hi--;
                } else {
                    return res;
                }
            }
        }
        return res;
    }

    //15. 3Sum
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return res;
        }
        int n = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < n - 2 && nums[i] <= 0; i++) {
            if (i == 0 || (i > 0 && nums[i] != nums[i - 1])) {
                int left = i + 1, right = n - 1;
                while (left < right) {
                    if (nums[left] + nums[right] > -nums[i]) {
                        right--;
                    } else if (nums[left] + nums[right] < -nums[i]) {
                        left++;
                    } else {
                        res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }
                        left++;
                        right--;
                    }
                }
            }
        }
        return res;
    }

    //8. String to Integer (atoi)
    public int myAtoi(String str) {
        if (str == null || str.length() < 1) {
            return 0;
        }

        str = str.trim();
        int i = 0;
        boolean flag = false;
        if (str.charAt(i) == '-') {
            flag = true;
            i++;
        } else if (str.charAt(i) == '+') {
            i++;
        }

        double res = 0;
        while (str.length() > i && str.charAt(i) >= '0' && str.charAt(i) <= '9') {
            res = res * 10 + (str.charAt(i) - '0');
            i++;
        }

        if (flag) {
            res = -res;
        }
        if (res >= Integer.MAX_VALUE) {
            res = Integer.MAX_VALUE;
        } else if (res <= Integer.MIN_VALUE) {
            res = Integer.MIN_VALUE;
        }
        return (int) res;
    }

    //5. Longest Palindromic Substring
    public String longestPalindrome(String s) {
        int[] res = new int[2];
        int n = s.length();
        for (int i = 0; i < n - 1; i++) {
            longestPalindromeHelper(s, i, i, res);
            longestPalindromeHelper(s, i, i + 1, res);
        }
        return s.substring(res[0], res[1] + 1);
    }

    // Given a center, either one letter or two letter, 
    // Find longest palindrome
    public void longestPalindromeHelper(String s, int l, int r, int[] res) {
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            l--;
            r++;
        }
        if (r - l - 1 > res[1] - res[0] + 1) {
            res[0] = l + 1;
            res[1] = r - 1;
        }
    }

    //1. Two Sum
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] res = new int[2];

        for (int i = 0; i < nums.length; i++) {
            int r = target - nums[i];
            if (map.containsKey(r)) {
                res[1] = i;
                res[0] = map.get(r);
                break;
            } else {
                map.put(nums[i], i);
            }
        }
        return res;
    }

    //
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

    //140. Word Break II
    public List<String> wordBreak2(String s, Set<String> dict) {
        List<String> res = new LinkedList<>();
        boolean[] isBad = new boolean[s.length() + 1];
        int min = 0, max = 0;
        for (String w : dict) {
            min = Math.min(min, w.length());
            max = Math.max(max, w.length());
        }
        wordBreakhelper(s.toCharArray(), dict, isBad, min, max, "", 0, res);
        return res;
    }

    boolean wordBreakhelper(char[] a, Set<String> d, boolean[] isBad, int min, int max, String s, int cur, List<String> res) {
        boolean notFound = true;
        for (int i = cur + min; i <= a.length && i <= cur + max; i++) {
            String w = new String(a, cur, i - cur);
            if (d.contains(w)) {
                if (i == a.length) {
                    res.add(s + w);
                    notFound = false;
                } else if (isBad[i]) {
                    continue;
                } else if (!wordBreakhelper(a, d, isBad, min, max, s + w + " ", i, res)) {
                    notFound = false;
                }
            }
        }
        if (notFound) {
            isBad[cur] = true;
        }
        return notFound;
    }

    //97. Interleaving String
    public static boolean isInterleave(String s1, String s2, String s3) {
        if (s3.length() != s1.length() + s2.length()) {
            return false;
        }
        Set<Integer> visited = new HashSet<>();
        return isInterleave(s1, 0, s2, 0, s3, 0, visited);
    }

    private static boolean isInterleave(String s1, int i1, String s2, int i2, String s3, int i3, Set<Integer> visited) {
        int hash = i1 * s3.length() + i2;
        if (visited.contains(hash)) {
            return false;
        }

        if (i1 == s1.length()) {
            return s2.substring(i2).equals(s3.substring(i3));
        }
        if (i2 == s2.length()) {
            return s1.substring(i1).equals(s3.substring(i3));
        }

        if (s3.charAt(i3) == s1.charAt(i1) && isInterleave(s1, i1 + 1, s2, i2, s3, i3 + 1, visited)
                || s3.charAt(i3) == s2.charAt(i2) && isInterleave(s1, i1, s2, i2 + 1, s3, i3 + 1, visited)) {
            return true;
        }

        visited.add(hash);
        return false;
    }

    //Sorting Questions:74,4,75
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

    //Graphic Questions(BFS/DFS):77,78,90,51,52,126
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

//    //200. Number of Islands
//    public int numIslands(char[][] grid) {
//        int res = 0;
//        for (int i = 0; i < grid.length; i++) {
//            for (int j = 0; j < grid[0].length; j++) {
//                if (grid[i][j] == '1') {
//                    traversalIslands(grid, i, j);
//                    res++;
//                }
//            }
//        }
//        return res;
//    }
//
//    void traversalIslands(char[][] a, int x, int y) {
//        if (x < 0 || x >= a.length || y < 0 || y >= a[0].length || a[x][y] != '1') {
//            return;
//        }
//        a[x][y] = '0';
//        traversalIslands(a, x - 1, y);
//        traversalIslands(a, x, y - 1);
//        traversalIslands(a, x + 1, y);
//        traversalIslands(a, x, y + 1);
//    }
    //139. Word Break
    public boolean wordBreak(String s, Set<String> wordDict) {
        boolean[] visited = new boolean[s.length()];
        return wordBreakDFS(wordDict, visited, s, 0);
    }

    boolean wordBreakDFS(Set<String> d, boolean[] v, String s, int cur) {
        if (cur >= s.length()) {
            return true;
        }
        if (v[cur]) {
            return false;
        }
        v[cur] = true;
        for (int i = cur + 1; i <= s.length(); i++) {
            if (d.contains(s.substring(cur, i)) && wordBreakDFS(d, v, s, i)) {
                return true;
            }
        }
        return false;
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

    //Array Questions:
    //315. Count of Smaller Numbers After Self
    //Merge sort,Hashing,Binary Tree
    public List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        int[] count = new int[n];
        int[] pos = new int[n];
        for (int i = 0; i < n; i++) {
            pos[i] = i;
        }

        mergeSort(nums, pos, count, 0, n - 1);

        List<Integer> res = new ArrayList<>();
        for (int i : count) {
            res.add(i);
        }
        return res;
    }

    void mergeSort(int[] a, int[] p, int[] c, int l, int r) {
        if (l >= r) {
            return;
        }
        int m = (l + r) / 2;
        mergeSort(a, p, c, l, m);
        mergeSort(a, p, c, m + 1, r);
        merge(a, p, c, l, m, r);

    }

    void merge(int[] a, int[] p, int[] c, int l, int m, int r) {
        int i = 0, k = l, j = m + 1 - l;
        int[] t = new int[r - l + 1];
        System.arraycopy(p, l, t, 0, r - l + 1);
        while (i < m + 1 - l && j < r + 1 - l) {
            if (a[t[i]] <= a[t[j]]) {
                if (l + i < k) {
                    c[t[i]] += (k - i - l);
                }
                p[k] = t[i++];
            } else {
                p[k] = t[j++];
            }
            k++;
        }
        while (i < m + 1 - l) {
            if (l + i < k) {
                c[t[i]] += (k - i - l);
            }
            p[k++] = t[i++];
        }

    }

    //287. Find the Duplicate Number
    //Bitwise,Floyd's loop detection
    public int findDuplicate(int[] nums) {
        int slow = 0, fast = 0;
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);
        slow = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }
//    public int findDuplicate(int[] nums) {
//        int n = nums.length - 1;
//        int t = 0;
//        for (int i = 0; n >>> i != 0; i++) {
//            int indeed = 0;
//            for (int j = 0; j < n + 1; j++) {
//                if ((nums[j] & (1 << i)) != 0) {
//                    indeed++;
//                }
//            }
//            int should = 0;
//            for (int j = 1; j < n + 1; j++) {
//                if ((j & (1 << i)) != 0) {
//                    should++;
//                }
//            }
//            if (indeed > should) {
//                t |= (1 << i);
//            }
//        }
//        return t;
//    }

    //312. Burst Balloons
    //DP,https://leetcode.com/discuss/72216/share-some-analysis-and-explanations
    public int maxCoins1(int[] old_nums) {
        int n = old_nums.length + 2;
        if (n < 3) {
            return 0;
        }
        int[] nums = new int[n];
        nums[0] = nums[n - 1] = 1;
        System.arraycopy(old_nums, 0, nums, 1, n - 2);
        int[][] dp = new int[n][n];
        for (int len = 3; len < n + 1; len++) {
            for (int i = 0; i + len < n + 1; i++) {
                int j = i + len - 1;
                for (int k = i + 1; k < j; k++) {
                    dp[i][j] = Math.max(dp[i][j], (dp[i][k] + dp[k][j] + nums[i] * nums[k] * nums[j]));
                }
            }
        }
        return dp[0][n - 1];
    }

    //String Questions:
    //3. Longest Substring Without Repeating Characters
    public int lengthOfLongestSubstring1(String s) {
        boolean[] visited = new boolean[Character.MAX_VALUE];
        int max = 0;
        for (int i = 0, j = 0; j < s.length();) {
            if (!visited[s.charAt(j)]) {
                visited[s.charAt(j++)] = true;
                max = Math.max(max, j - i);
            } else {
                visited[s.charAt(i++)] = false;
            }
        }
        return max;
    }

    //316. Remove Duplicate Letters
    public String removeDuplicateLetters(String s) {
        int n = s.length();
        int[] num = new int[256];
        boolean[] in = new boolean[256];
        for (int i = 0; i < n; i++) {
            num[s.charAt(i)]++;
        }

        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            num[c]--;
            if (!in[c]) {
                while (stack.size() > 0 && stack.peek() > c && num[stack.peek()] > 0) {
                    in[stack.pop()] = false;
                }
                stack.push(c);
                in[c] = true;
            }

        }
        char[] res = new char[stack.size()];
        for (int i = 0; stack.size() > 0; i++) {
            res[i] = stack.removeLast();
        }
        return new String(res);
    }

    //301. Remove Invalid Parentheses
    public List<String> removeInvalidParentheses(String s) {
        List<String> res = new ArrayList<>();
        remove(s, res, 0, 0, new char[]{'(', ')'});
        return res;
    }

    void remove(String s, List<String> res, int l_i, int l_j, char[] p) {
        int stack = 0;
        for (int i = l_i; i < s.length(); i++) {
            if (s.charAt(i) == p[0]) {
                stack++;
            } else if (s.charAt(i) == p[1]) {
                stack--;
            }
            if (stack < 0) {
                for (int j = l_j; j <= i; j++) {
                    if (s.charAt(j) == p[1] && (j == l_j || s.charAt(j - 1) != p[1])) {
                        remove(s.substring(0, j) + s.substring(j + 1, s.length()), res, i, j, p);
                    }
                }
                return;
            }
        }
        String reversed = new StringBuilder(s).reverse().toString();
        if (p[0] == '(') {
            remove(reversed, res, 0, 0, new char[]{')', '('});
        } else {
            res.add(reversed);
        }

    }

    //Graphic and Tree
    //105. Construct Binary Tree from Preorder and Inorder Traversal
    //Definition for a binary tree node
    public class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return getRoot(preorder, inorder, 0, 0, preorder.length);
    }

    public TreeNode getRoot(int[] pre, int[] in, int lp, int li, int n) {
        if (n < 1) {
            return null;
        }
        TreeNode root = new TreeNode(pre[lp]);
        int i = li;
        for (; pre[lp] != in[i]; i++);
        root.left = getRoot(pre, in, lp + 1, li, i - li);
        root.right = getRoot(pre, in, lp + i - li + 1, i + 1, n - i - 1 + li);
        return root;
    }

    //106. Construct Binary Tree from Inorder and Postorder Traversal
    public TreeNode buildTree2(int[] inorder, int[] postorder) {
        return getRoot(postorder, inorder, inorder.length - 1, 0, inorder.length);
    }

    public TreeNode getRoot2(int[] post, int[] in, int rp, int li, int n) {
        if (n < 1) {
            return null;
        }
        TreeNode root = new TreeNode(post[rp]);
        int i = li;
        for (; post[rp] != in[i]; i++);
        root.left = getRoot(post, in, rp - (n - i + li), li, i - li);
        root.right = getRoot(post, in, rp - 1, i + 1, n - (i - li + 1));
        return root;
    }

    //124. Binary Tree Maximum Path Sum
    public int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int[] max = new int[]{Integer.MIN_VALUE};
        maxPathSumHelper(root, max);
        return max[0];
    }

    int maxPathSumHelper(TreeNode root, int[] max) {
        if (root == null) {
            return 0;
        }
        int left = maxPathSumHelper(root.left, max);
        int right = maxPathSumHelper(root.right, max);
        int res = Math.max(Math.max(left, right), 0) + root.val;
        max[0] = Math.max(Math.max(max[0], res), left + right + root.val);
        return res;
    }

    //111. Minimum Depth of Binary Tree
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        int min = 1;
        if (left > 0 && right > 0) {
            min += Math.min(left, right);
        } else if (left > 0) {
            min += left;
        } else if (right > 0) {
            min += right;
        }
        return min;
    }

    //110. Balanced Binary Tree
    public boolean isBalanced(TreeNode root) {
        boolean[] b = new boolean[1];
        findDepth(root, b);
        return !b[0];
    }

    int findDepth(TreeNode root, boolean[] b) {
        if (root == null) {
            return 0;
        }
        int left = findDepth(root.left, b);
        int right = findDepth(root.right, b);
        if (left > right + 1 || right > left + 1) {
            b[0] = true;
        }
        return Math.max(left, right) + 1;
    }

    //104. Maximum Depth of Binary Tree
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);

        return Math.max(left, right) + 1;
    }

    //100. Same Tree
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null) {
            return q == null;
        }
        if (q == null) {
            return false;
        }
        return q.val == p.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

//    //98. Validate Binary Search Tree
//    public boolean isValidBST(TreeNode root) {
//        int[] last = new int[]{0};
//        boolean[] first = new boolean[]{true};
//        return helper(root, first, last);
//    }
//
//    public boolean helper(TreeNode root, boolean[] first, int[] last) {
//        if (root == null) {
//            return true;
//        }
//        if (!helper(root.left, first, last)) {
//            return false;
//        }
//
//        if (first[0]) {
//            first[0] = false;
//        } else if (last[0] >= root.val) {
//            return false;
//        }
//
//        last[0] = root.val;
//        return helper(root.right, first, last);
//    }
    //114. Flatten Binary Tree to Linked List
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode left = root.left;
        TreeNode right = root.right;
        root.left = null;

        flatten(left);
        if (left != null) {
            root.right = left;
            while (left.right != null) {
                left = left.right;
            }
            left.right = right;
        }
        flatten(right);
    }

    //109. Convert Sorted List to Binary Search Tree
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode cur = head;
        int h = 1;
        while (cur.next != null) {
            cur = cur.next;
            h++;
        }
        return helper(new ListNode[]{head}, h);
    }

    TreeNode helper(ListNode[] h, int n) {
        if (n < 1) {
            return null;
        }
        TreeNode left = helper(h, (n - 1) / 2);
        TreeNode root = new TreeNode(h[0].val);
        root.left = left;
        h[0] = h[0].next;
        root.right = helper(h, n / 2);
        return root;
    }

    //Definition for undirected graph.
    class UndirectedGraphNode {

        int label;
        List<UndirectedGraphNode> neighbors;

        UndirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<>();
        }
    }

    //133. Clone Graph
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        Map<Integer, UndirectedGraphNode> map = new HashMap<>();
        return cloneGraphHelper(node, map);
    }

    UndirectedGraphNode cloneGraphHelper(UndirectedGraphNode n, Map<Integer, UndirectedGraphNode> map) {
        if (n == null) {
            return null;
        }
        if (map.containsKey(n.label)) {
            return map.get(n.label);
        }
        UndirectedGraphNode nc = new UndirectedGraphNode(n.label);
        map.put(nc.label, nc);
        for (int i = 0; i < n.neighbors.size(); i++) {
            nc.neighbors.add(cloneGraphHelper(n.neighbors.get(i), map));
        }
        return nc;
    }

    //O(N) Questions
    //42. Trapping Rain Water
    public int trap(int[] height) {
        int n = height.length;
        int[] left = new int[n];
        int[] right = new int[n];
        int res = 0;
        for (int i = 0, max = 0; i < n; i++) {
            left[i] = max;
            max = Math.max(max, height[i]);
        }
        for (int i = n - 1, max = 0; i >= 0; i--) {
            right[i] = max;
            max = Math.max(max, height[i]);
        }
        for (int i = 0; i < n; i++) {
            int min = Math.min(left[i], right[i]);
            res += Math.max(min - height[i], 0);
        }
        return res;
    }

    //11. Container With Most Water
    public int maxArea(int[] height) {
        int best = 0, n = height.length;
        if (n < 2) {
            return 0;
        }
        for (int i = 0, j = n - 1; i < j;) {
            int lmax = height[i], rmax = height[j];
            best = Math.max(best, Math.min(height[i], height[j]) * (j - i));
            if (height[i] < height[j]) {
                while (i < n - 1 && height[i] <= lmax) {
                    i++;
                }
            } else {
                while (j > 0 && height[j] <= rmax) {
                    j--;
                }
            }
        }
        return best;
    }

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

    //115. Distinct Subsequences
    public int numDistinct(String s, String t) {
        int n = s.length(), m = t.length();
        if (m < 1) {
            return 0;
        }
        int[] count = new int[m];

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            for (int j = Math.min(m - 1, i); j >= 0; j--) {
                if (c == t.charAt(j)) {
                    count[j] += (j < 1 ? 1 : count[j - 1]);
                }
            }
        }

        return count[m - 1];
    }

    //152. Maximum Product Subarray
    public int maxProduct(int[] nums) {
        int n = nums.length;
        if (n < 1) {
            return 0;
        }

        int max, dpmax, dpmin;
        max = dpmax = dpmin = nums[0];
        for (int i = 1; i < n; i++) {
            int tempmax = Math.max(nums[i], Math.max(dpmax * nums[i], dpmin * nums[i]));
            int tempmin = Math.min(nums[i], Math.min(dpmax * nums[i], dpmin * nums[i]));
            dpmax = tempmax;
            dpmin = tempmin;
            max = Math.max(dpmax, max);
        }

        return max;
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

//    //1. Two Sum
//    public int[] twoSum(int[] nums, int target) {
//        Map<Integer, Integer> map = new HashMap<>();
//        int[] res = new int[2];
//
//        for (int i = 0; i < nums.length; i++) {
//            int r = target - nums[i];
//            if (map.containsKey(r)) {
//                res[1] = i + 1;
//                res[0] = map.get(r) + 1;
//                break;
//            } else {
//                map.put(nums[i], i);
//            }
//        }
//        return res;
//    }
//    //5. Longest Palindromic Substring
//    public String longestPalindrome(String s) {
//        int[] res = new int[2];
//        int n = s.length();
//        for (int i = 0; i < n - 1; i++) {
//            longestPalindromeHelper(s, i, i, res);
//            longestPalindromeHelper(s, i, i + 1, res);
//        }
//        return s.substring(res[0], res[1] + 1);
//    }
//
//    public void longestPalindromeHelper(String s, int l, int r, int[] res) {
//        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
//            l--;
//            r++;
//        }
//        if (r - l - 1 > res[1] - res[0] + 1) {
//            res[0] = l + 1;
//            res[1] = r - 1;
//        }
//    }
    //Array Questions:
    //153. Find Minimum in Rotated Sorted Array
    public int findMin(int[] nums) {
        int n = nums.length;
        int l = 0, r = n - 1;
        while (l < r) {
            int mid = (l + r) / 2;
            if (nums[mid] < nums[r]) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return nums[l];
    }

    //154. Find Minimum in Rotated Sorted Array II
    public int findMinWithDup(int[] nums) {
        int n = nums.length;
        int l = 0, r = n - 1;
        while (l < r) {
            int mid = (l + r) / 2;
            if (nums[mid] < nums[r]) {
                r = mid;
            } else if (nums[mid] > nums[r]) {
                l = mid + 1;
            } else {
                r--;
            }
        }
        return nums[l];
    }

    //41. First Missing Positive
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n;) {
            if (nums[i] == i + 1) {
                i++;
            } else if (nums[i] < i + 1 || nums[i] > n || nums[i] == nums[nums[i] - 1]) {
                nums[i] = nums[--n];
            } else {
                swap(nums, i, nums[i] - 1);
            }
        }
        return n + 1;
    }

    //164. Maximum Gap
    public int maximumGap(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return 0;
        }
        int max = nums[0], min = nums[0];
        int[] bucketsMin = new int[n + 1];
        int[] bucketsMax = new int[n + 1];
        Arrays.fill(bucketsMin, Integer.MAX_VALUE);
        Arrays.fill(bucketsMax, Integer.MIN_VALUE);

        for (int i = 0; i < n; i++) {
            min = Math.min(min, nums[i]);
            max = Math.max(max, nums[i]);
        }

        //d=(max-min)/(n+1); k = (r-min)*(n+1)/(max-min)
        int gap = (int) Math.ceil((double) (max - min) / (n + 1));
        for (int i = 0; i < n; i++) {
            int k = (nums[i] == max ? n : (nums[i] - min) / gap);
            bucketsMin[k] = Math.min(bucketsMin[k], nums[i]);
            bucketsMax[k] = Math.max(bucketsMax[k], nums[i]);
        }

        gap = 0;
        for (int i = 1, pre = bucketsMax[0]; i < n + 1; i++) {
            // empty bucket
            if (bucketsMin[i] == Integer.MAX_VALUE && bucketsMax[i] == Integer.MIN_VALUE) {
                continue;
            }

            gap = Math.max(gap, bucketsMin[i] - pre);
            pre = bucketsMax[i];
        }
        return gap;
    }

    //136. Single Number
    public int singleNumber(int[] nums) {
        int n = 0;
        for (int i = 0; i < nums.length; i++) {
            n ^= nums[i];
        }
        return n;
    }

    //137. Single Number II
    //https://leetcode.com/discuss/6632/challenge-me-thx
    public int singleNumberWithThreeDup(int[] nums) {
        int one = 0, two = 0;
        for (int i = 0; i < nums.length; i++) {
            one = (one ^ nums[i]) & ~two;
            two = (two ^ nums[i]) & ~one;
        }
        return one;
    }

    //260. Single Number III
    public int[] singleNumberWithTwoUnique(int[] nums) {
        int xor = 0;
        for (int n : nums) {
            xor ^= n;
        }
        //get the Least significant bit
        xor &= -xor;

        int[] res = {0, 0};
        for (int n : nums) {
            res[(n & xor) == 0 ? 0 : 1] ^= n;
        }
        return res;
    }

    //Stack And Queue Questions:
    //239. Sliding Window Maximum
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (k == 0) {
            return new int[0];
        }
        if (k == 1) {
            return nums;
        }
        int[] result = new int[nums.length - k + 1];
        int max = -2147483648, j = 0;
        for (int i = 0; i < k; i++) {
            if (nums[i] > max) {
                max = nums[i];
            }
        }
        result[j++] = max;
        for (int i = k; i < nums.length; i++) {
            if (nums[i] >= max) {
                max = nums[i];
            } else if (max == nums[i - k]) {
                max = -2147483648;
                for (int m = i - k + 1; m <= i; m++) {
                    if (nums[m] > max) {
                        max = nums[m];
                    }
                }
            }
            result[j++] = max;
        }
        return result;
    }

    //84. Largest Rectangle in Histogram
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] s = new int[n + 1];
        int max = 0, top = 0, size = 0;
        for (int i = 0; i < n; i++) {
            while (size > 0 && heights[s[top]] >= heights[i]) {
                int h = heights[s[top--]];
                size--;
                max = Math.max(max, (i - 1 - (size == 0 ? -1 : s[top])) * h);
            }
            s[++top] = i;
            size++;
        }
        while (size > 0) {
            int h = heights[s[top--]];
            size--;
            max = Math.max(max, (n - 1 - (size == 0 ? -1 : s[top])) * h);
        }
        return max;
    }

    //LinkedList Questions:
    //92. Reverse Linked List II
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (n > m) {
            ListNode pre = null, cur = head, rev = null;
            for (int i = 1; i < m; i++) {
                pre = cur;
                cur = cur.next;
            }
            for (int i = m; i <= n; i++) {
                ListNode temp = cur.next;
                cur.next = rev;
                rev = cur;
                cur = temp;
            }
            if (pre == null) {
                head.next = cur;
                head = rev;
            } else {
                pre.next.next = cur;
                pre.next = rev;
            }
        }
        return head;
    }

    //25. Reverse Nodes in k-Group
    public ListNode reverseKGroup(ListNode head, int k) {
        if (k < 2) {
            return head;
        }
        ListNode pre = null, cur = head, rev = null, curR = head;

        for (int l = 0, r = 0; curR != null; r++) {
            curR = curR.next;

            if (r - l + 1 == k) {
                for (; l <= r; l++) {
                    ListNode temp = cur.next;
                    cur.next = rev;
                    rev = cur;
                    cur = temp;
                }
                if (pre == null) {
                    pre = head;
                    pre.next = cur;
                    head = rev;
                } else {
                    pre.next.next = cur;
                    ListNode temp = pre.next;
                    pre.next = rev;
                    pre = temp;
                }
            }
        }
        return head;
    }

    //141. Linked List Cycle
    public boolean hasCycle(ListNode head) {
        boolean res = false;
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast.val == slow.val) {
                res = true;
                break;
            }
        }
        return res;
    }

    //142. Linked List Cycle II
    public ListNode detectCycle(ListNode head) {
        ListNode meet = null, slow = head, fast = head;
        while (fast != null && fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                for (fast = head; fast != slow; fast = fast.next, slow = slow.next);
                meet = slow;
                break;
            }
        }
        return meet;
    }

//    //160. Intersection of Two Linked Lists
//    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
//        ListNode meet = null, cur = headA, slow = headB, fast = headB;
//        while (cur != null) {
//            if (cur.next == null) {
//                cur.next = headA;
//                break;
//            }
//            cur = cur.next;
//        }
//        while (fast != null && fast.next != null && fast.next.next != null) {
//            fast = fast.next.next;
//            slow = slow.next;
//            if (fast == slow) {
//                for (fast = headB; fast != slow; fast = fast.next, slow = slow.next);
//                meet = slow;
//                break;
//            }
//        }
//        if (cur != null) {
//            cur.next = null;
//        }
//        return meet;
//    }
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

    //86. Partition List
    public ListNode partition(ListNode head, int x) {
        ListNode pre = null, cur = head;
        if (head == null) {
            return head;
        }

        while (cur.next != null) {
            ListNode next = cur.next;
            if (cur.val < x) {
                if (next.val < x) {
                    pre = next;
                } else {
                    pre = cur;
                }
                cur = next;
            } else if (next.val < x) {
                cur.next = next.next;
                if (pre == null) {
                    next.next = head;
                    head = next;
                } else {
                    next.next = pre.next;
                    pre.next = next;
                }
                pre = next;
            } else {
                cur = next;
            }
        }
        return head;
    }
}

