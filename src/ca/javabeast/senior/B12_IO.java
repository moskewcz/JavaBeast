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
package ca.javabeast.senior;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.URL;

/**
 *
 * @author Alpenliebe <alpseinstein@gmail.com>
 */
public class B12_IO {
    
    public static void main(String[] args) throws Exception{
        URL location = B12_IO.class.getProtectionDomain().getCodeSource().getLocation();
        System.out.println(location.getFile());
        
        String sourceFile = "./src/ca/Javabeast/senior/B12_IO.java";
        String resourceFile = "./resources/B12_IO.out";
        //System.out.println(readFromDisk(sourceFile)); 
        
        //readFromMemory(sourceFile);
        
        //writeToDisk(sourceFile,resourceFile);
        
        readStandardIO();
    }
    
    //read from disk
    public static String readFromDisk(String filename) throws FileNotFoundException, IOException{
        BufferedReader br = new BufferedReader(new FileReader(filename));
        
        StringBuffer sb = new StringBuffer();
        String line;
        while ((line = br.readLine()) !=null){
            sb.append(line+"\n");
        }
        br.close();
                
        return sb.toString(); 
    }
    
    //read from memory
    public static void readFromMemory(String filename) throws Exception{
        StringReader sr = new StringReader(readFromDisk(filename));
        int c;
        while((c=sr.read())!=-1){
            System.out.print((char)c);
        }
        sr.close();
    }
    
    //write to disk
    public static void writeToDisk(String from,String to) throws Exception{
        BufferedReader br = new BufferedReader(new StringReader(readFromDisk(from)));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(to)));
        int lineCount = 1;
        String line;
        while((line = br.readLine())!=null){
            pw.println(lineCount++ + ":"+ line );
        }
        br.close();
        pw.close();
        System.out.println(readFromDisk(to));
    }
    
    public static void readStandardIO() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //Scanner in = new Scanner(System.in);
        String s;
        while((s=br.readLine())!=null&& s.length() != 0){
            System.out.println(s);
        }
        br.close();
    }
    
    
}
