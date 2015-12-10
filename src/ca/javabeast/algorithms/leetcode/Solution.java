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

import java.util.HashSet;
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
                    if (stack.isEmpty()){
                        max = Math.max(max, i-left+1);
                    } else {
                        max = Math.max(max, i-stack.peek());
                    }
                }
            }
            //print("left:", left);
            //print("max:", max);

        }
        return max;
    }

    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        int max = 0;
        //record the start point
        int j=0;
        for(int i=0;i<s.length();i++){
            Character c = s.charAt(i);
            if(set.contains(c)){  //encounter a repeat character
                while(s.charAt(j)!=c){
                    set.remove(s.charAt(j));
                    j++;
                }
                j++;
            }
            set.add(c);
            max = Math.max(max,set.size());
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
}
