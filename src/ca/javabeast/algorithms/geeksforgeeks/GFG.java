package ca.javabeast.algorithms.geeksforgeeks;

/*
 * Copyright 2016 alpenliebe <alpseinstein@gmail.com>.
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
/**
 *
 * @author alpenliebe <alpseinstein@gmail.com>
 */
import java.util.*;
import java.lang.*;
import java.io.*;

class GFG {

    public static void main(String[] args) {

    }

    //http://www.practice.geeksforgeeks.org/problem-page.php?pid=364
    static void coinChangeMain() {
        //code
        Scanner sc = null;
        GFG gfg = new GFG();
        try {
            sc = new Scanner(System.in);
            int t = sc.nextInt();
            while (t > 0) {
                //String[] line = sc.nextLine().split(" ");
                //int amount = Integer.parseInt(line[0]);
                int amount = sc.nextInt();
                //int[] nums = new int[Integer.parseInt(line[1])];
                int[] nums = new int[sc.nextInt()];
                //line = sc.nextLine().split(" ");
                for (int i = 0; i < nums.length; i++) {
                    //nums[i] = Integer.parseInt(line[i]);
                    nums[i] = sc.nextInt();
                }
                t--;
                //System.out.println(Arrays.toString(nums));
                System.out.println(gfg.coinChange(nums, amount));
            }

            /*while(scanner.hasNextLine()){
             System.out.println(scanner.nextLine());
             }*/
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sc != null) {
                sc.close();
            }
        }

    }

    int minCount = Integer.MAX_VALUE;

    int coinChange(int[] coins, int amount) {
        Arrays.sort(coins);
        count(amount, coins.length - 1, coins, 0);
        return minCount == Integer.MAX_VALUE ? -1 : minCount;
    }

    void count(int amount, int index, int[] coins, int count) {
        if (index < 0 || count + 2 > minCount) {
            return;
        }
        for (int i = amount / coins[index]; i >= 0; i--) {
            int newAmount = amount - i * coins[index];
            int newCount = count + i;
            if (newAmount > 0 && newCount + 1 < minCount) {
                //if(newCount==16)System.out.println(amount+"-"+coins[index]+",-"+newCount+"-"+i);
                count(newAmount, index - 1, coins, newCount);

            } else {
                if (newAmount == 0 && newCount < minCount) {
                    minCount = newCount;

                }

                break;
            }
        }
    }
}
