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
package ca.javabeast.algorithms.character;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author alpenliebe <alpseinstein@gmail.com>
 */
public class CharacterProcessor {
    
    public static String findFirstNonrepeatedCharacter(String str) {
        HashMap<Integer, Object> charHash = new HashMap<>();

        int i;
        Object once=new Object(),multiple=new Object();
        // Scan str, building hash table
        for (i = 0; i < str.length(); ) {
            final int cp = str.codePointAt(i);
            i+=Character.charCount(cp);
            if (charHash.containsKey(cp)) {
        // Increment count corresponding to c
                charHash.put(cp, multiple);
            } else {
                charHash.put(cp, once);
            }
        }
        // Search hash table in order of str
        for (i = 0; i < str.length();) {
            final int cp = str.codePointAt(i);
            i+=Character.charCount(cp);
            if (charHash.get(cp) ==once) {
                return new String(Character.toChars(cp));
            }
        }
        return null;
    }
    
    public static Character findFirstNonrepeatedCharacter2(String str) {
        HashMap<Character, Integer> charHash = new HashMap<Character, Integer>();

        int i;
        Character c;
        // Scan str, building hash table
        for (i = 0; i < str.length(); i++) {
            c = str.charAt(i);
            if (charHash.containsKey(c)) {
        // Increment count corresponding to c
                charHash.put(c, charHash.get(c) + 1);
            } else {
                charHash.put(c, 1);
            }
        }
        // Search hash table in order of str
        for (i = 0; i < str.length(); i++) {
            c = str.charAt(i);
            if (charHash.get(c) == 1) {
                return c;
            }
        }
        return null;
    }

    public static Character findFirstNonrepeatedCharacter3(String s) {
        Map<Integer, Character> map = new HashMap<>();
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (set.contains(c)) {
                map.remove(i);
            } else {
                map.put(i, c);
            }
            set.add(c);
        }

        int min = s.length();
        for (Integer key : map.keySet()) {
            if (key < min) {
                min = key;
            }
        }
        return min == s.length() ? null : s.charAt(min);
    }
    
    
    public static String RemoveSpecifiedCharacters( String str, String remove ){
        if(remove==null||"".equals(remove))
            return str;
        char[] s = str.toCharArray();
        // flags automatically initialized to false, size of 128 assumes ASCII
        boolean[] flags = new boolean[128];
        int cur,left=0;
        
        // Set flags for characters to be removed
        for(cur=0;cur<remove.length();cur++){
            int c = remove.charAt(cur);
            flags[c]=true;
        }
        for(cur=0;cur<s.length;cur++){
            if(!flags[s[cur]]){
                s[left++] = s[cur];
            }
        }
        return new String(s,0,left);
        
    }
    
    public static String reverseWords(String str){
        char[] arr = str.toCharArray();
        int cur=0,left,right,slen;
        slen = str.length();
        reverseCharacters(arr,0,slen-1);
        for(left=0;cur<slen;cur++){
            if(arr[cur]==' '){
                right=cur-1;
                reverseCharacters(arr,left,right);
                left=cur+1;
            } 
        }
        str = String.valueOf(arr);
        return  str;
    }
    
    public static void reverseCharacters(char[] arr,int start,int end){
        for(char temp;start<=end;start++,end--){
            temp = arr[start];
            arr[start] = arr[end];
            arr[end]=temp;
        }
    }
    
    public static String reverseWords2(String str){
        int cur,start,end,slen,writePos=0;
        slen=str.length();
        cur=slen-1;
        end=slen;
        char[] buffer = new char[slen];
        while(cur>=0){
            if(str.charAt(cur)==' '){
                buffer[writePos++]=str.charAt(cur--);
            } else {
                end = cur;
                while(cur>=0&&str.charAt(cur)!=' ')
                    cur--;
                for(start=cur+1;start<=end;start++,writePos++){
                    buffer[writePos]=str.charAt(start);
                }
            }
        }
        str = String.valueOf(buffer);
        return  str;
    }
    
    public static int parseInt(String str){
        int n=0;
        for(int i=0;i<str.length();i++){
            int d = str.charAt(i)-'0';
            if(d>0){
                n*=10;
                n+=d;
            }
        }
        if(str.charAt(0)=='-'){
            n=~(n-1);
        }
        return n;
    }
    public static final int MAX_DIGITS = 10;
    public static String parseString(int n){
        /* Buffer big enough for largest int and - sign */
        char[] temp = new char[ MAX_DIGITS + 1 ];
        boolean isNeg=false;
        int i =0;
        if(n<0){
            n=~n+1;
            isNeg=true;
        }
        
        do{
            char c = (char) (n%10 + '0');
            temp[i]=c;
            n=n/10;
            i++;
        }while(n>0);
        //concanate the char to string
        StringBuilder sb = new StringBuilder();
        if(isNeg){
            sb.append('-');
        }
        while( i > 0 ){
            sb.append( temp[--i] );
        }
        return sb.toString();
    }
    public static void main(String[] args){
        System.out.println(parseString(parseInt("-3244")));
    }
}
