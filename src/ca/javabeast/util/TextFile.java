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
package ca.javabeast.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Alpenliebe <alpseinstein@gmail.com>
 */

public class TextFile extends ArrayList<String> {  
  
    private static final long serialVersionUID = -1942855619975438512L;  
  
    // Read a file as a String  
    public static String read(String filename) {  
        StringBuilder sb = new StringBuilder();  
        try {  
            BufferedReader in = new BufferedReader(new FileReader(new File(  
                    filename).getAbsoluteFile()));  
            String s;  
            try {  
                while ((s = in.readLine()) != null) {  
                    sb.append(s);  
                    sb.append("\n");  
                }  
            } finally {  
                in.close();  
            }  
  
        } catch (IOException e) {  
            throw new RuntimeException(e);  
        }  
        return sb.toString();  
    }  
  
    // Write a single file in one method call  
    public static void write(String fileName, String text) {  
        try {  
            PrintWriter out = new PrintWriter(  
                    new File(fileName).getAbsoluteFile());  
            try {  
                out.print(text);  
            } finally {  
                out.close();  
            }  
        } catch (IOException e) {  
            throw new RuntimeException(e);  
        }  
    }  
  
    // Read a file,spilt by any regular expression  
    public TextFile(String fileName, String splitter) {  
        super(Arrays.asList(read(fileName).split(splitter)));  
        if (get(0).equals(""))  
            remove(0);  
    }  
  
    // Normally read by lines  
    public TextFile(String fileName) {  
        this(fileName, "\n");  
    }  
  
    public void write(String fileName) {  
        try {  
            PrintWriter out = new PrintWriter(  
                    new File(fileName).getAbsoluteFile());  
            try {  
                for (String item : this)  
                    out.println(item);  
            } finally {  
                out.close();  
            }  
  
        } catch (IOException e) {  
            throw new RuntimeException(e);  
        }  
  
    }  
  
    // test,I have generated a file named data.d at the root  
    public static void main(String[] args) {  
  
        String resourceFile = "./resources/TextFile.out";
        
        /* read() test */  
        System.out.println(read(resourceFile)); // testing is OK!  
  
        /* write() test */  
        write(resourceFile, "helloworld\negg"); // testing is OK!  
  
        /* constractor test */  
        TextFile tf = new TextFile(resourceFile); // testing is OK!  
  
    }  
  
}  