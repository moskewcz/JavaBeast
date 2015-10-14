/*
 * Copyright 2015 Alpenliebe <alpseinstein@gmail.com>.
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
package ca.javabeast.elementary;

import java.util.StringTokenizer;

/**
 *
 * @author Alpenliebe <alpseinstein@gmail.com>
 */
public class B1_String {
    
    /**
     * 
     * @param args 
     */     
    public static void main(String[] args){
        //stringTest();
        stringUtilTest();
    }
    
    public static void stringUtilTest(){
        //length and capacity
        StringBuffer sb1 = new StringBuffer("hello");  
        System.out.println(sb1.length());
        System.out.println(sb1.capacity());
        System.out.println("");
        
        //equals
        StringBuffer sb2 = new StringBuffer("hello");  
        System.out.println(sb1.equals(sb2));
        System.out.println("");
        
        //StringTokenizer
        String s1 = "Tonight is the answer !";  
        StringTokenizer st = new StringTokenizer(s1," ");  
        int count = st.countTokens();  
        System.out.println("count:"+count);  
        while (st.hasMoreTokens()) {  
            String token = st.nextToken();  
            System.out.println(token);  
        }
    }
    
    public static void stringTest(){
        //s and s2 from stack refer to the same "hello world" in pool
        String s1 = "hello world";
        String s2 = "hello world";
        System.out.println(s1 == s2);
        
        //but str refers new "I like Java" object
        String str = "I like";
        System.out.println(System.identityHashCode(str));
        str += "Java";
        System.out.println(System.identityHashCode(str));
        
        //equals is the comparison of the objects in heap,== is the comparison of the references in stack
        String s3 = new String("hello world");
        String s4 = new String("hello world");
        String s5 = new String("hello");
        System.out.println(s3.equals(s4));//true
        System.out.println(s3.equals(s5));//false
        System.out.println("");
        
        String s6 = "hello world";  
        String s7 = new String("hello world");  
        String s8 = new String("hello world");  
        String s9 = new String("hello");  
        String s10 = "hello world";  
        System.out.println(s6.equals(s7));//true 
        System.out.println(s7.equals(s8));//true
        System.out.println(s7.equals(s9));//false
        System.out.println("------------------");  
        System.out.println(s6 == s7);//false
        System.out.println(s6 == s9);//false
        System.out.println(s6 == s10);//true
        System.out.println("");
        
        //comparaTo return v1[k]-v2[k] or len1-len2
        String s11 = "hallo";  
        String s12 = "ha";  
        String s13 = "haeeo";  
        int a = s11.compareTo(s12);  
        System.out.println("a:"+a);  
        int b = s11.compareTo(s13);  
        System.out.println("b:"+b);  
        int c = s12.compareTo(s13);  
        System.out.println("c:"+c); 
        System.out.println("");
        
        //substring(int beginIndex, int endIndex)
        String s14 = "helloworld";  
        String s15 = s14.substring(2);  
        String s16 = s14.substring(2, 7);  
        String s17 = (String) s14.subSequence(2, 7);  
        System.out.println("s15:"+s15+"\n"+"s16:"+s16+"\n"+"s17:"+s17);
        System.out.println("");
        
        //String replace(char oldChar, char newChar), String replaceAll(String regex, String replacement)
        String s18 = "hello world";  
        String s19 = s18.replace("l", "d");  
        System.out.println(s19);
        String s20 = "a78e5opx587";  
        String s21 = s20.replaceAll("[0-9]", "");//用空串替换原串里所有的0-9的数字  
        System.out.println(s21);
        System.out.println();
        
        //String[] split(String regex)
        String s22 = "hello world";  
        String s23 = "hello.worldd";  
        String[] s24 = s22.split(" ");  
        String[] s25 = s23.split("\\.");  
        for(int i=0; i<s24.length; i++){  
            System.out.print(s24[i]+" ");  
        }  
        System.out.println();
        for(int j=0; j<s25.length; j++){  
            System.out.print(s25[j]+" ");  
        } 
        System.out.println("\n");
        
        //s.intern()==t.intern() and s.equals(t)
        String s26 = new String("abc");  
        String s27 = "abc";  
        String s28 = "abc";  
        String s29 = s26.intern();  
        System.out.println(s26 == s27);//false  
        System.out.println(s26 == s28);//false  
        System.out.println(s26 == s29);//false  
        System.out.println(s27 == s29);//true
        System.out.println("");
        
        //split without the occurrence of the parttern 
        String s30 = "helloworld";  
        String[] s31 = s30.split("abc");  
        for (int i = 0; i < s31.length; i++) {  
            System.out.println(s31[i] + " " + i);  
        }
        System.out.println("");
         
        String s32 = "9"; 
        System.out.println(2+3+s32);  
        System.out.println(2+s32+3);
        System.out.println("");
        
        
        String s33 = "ab";  
        String s34 = "a";  
        String s35 = s34 + "b";  
        String s36 = "ab";   
        System.out.println(s33 == s35);//false  
        System.out.println(s35 == s36);//false  
        System.out.println(s35.hashCode() == s36.hashCode());//true  
        String s37 = "ad";  
        String s38 = "a" + "d";  
        String s39 = "ad";  
        System.out.println(s37 == s38);//true  
        System.out.println(s37 == s39);//true 
    }
}
