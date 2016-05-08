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
package ca.javabeast.algorithms.hackerrank;

import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author alpenliebe <alpseinstein@gmail.com>
 */
public class Solution {

    public static void main(String[] args) {
        String[][] arr = {{"a", "a"}, {"a", "b", "a"}};
        System.out.println(Arrays.toString(findCommonElement(arr)));
        System.out.println(getPercentageOfStrings(new String[]{"a", "a"}, new String[]{"a", "b", "a"}));
        System.out.println(Arrays.toString(renameFilenames(new String[]{"doc", "doc", "image", "doc(1)", "doc"})));
//        Scanner in = new Scanner(System.in);
//        int t = in.nextInt();//# of test case 
//        for (int i = 0; i < t; i++) {
//            int n = in.nextInt();
//            int[] ar = new int[n];
//            for (int j = 0; j < n; j++) {
//                ar[j] = in.nextInt();
//            }
//            System.out.println(maximumSub(ar, n));
//        }
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
    public static String[] renameFilenames(String[] names) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < names.length; i++) {
            String name = names[i];
            Integer count = map.get(name);
            if (count == null) {
                count = 0;
            }
            map.put(name, count + 1);

            if (count == 0) {
                continue;
            }

            while (map.get(name + "(" + count + ")") != null) {
                count++;
            }
            names[i] = name + "(" + count + ")";
            map.put(names[i], 1);
        }
        return names;
    }
    
}
