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

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author Alpenliebe <alpseinstein@gmail.com>
 */
public class BinaryFile {
    /* the parametre is a file */  
    public static byte[] read(File file) throws IOException {  
        BufferedInputStream bf = new BufferedInputStream(new FileInputStream(  
                file));  
        try {  
            byte[] data = new byte[bf.available()];  
            bf.read(data);  
            return data;  
        } finally {  
            bf.close();  
        }  
    }  
  
    /* the param is the path of a file */  
    public static byte[] read(String file) throws IOException {  
        return read(new File(file).getAbsoluteFile());  
    }
    
    
    /* the parametre is a file and bytes */
    public static void write(File file,byte[] data){
       
        try {
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file));
            
            try {
                out.write(data);
            } finally{
                out.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    /* the param is the path of a file and bytes*/  
    public static void write(String file,byte[] data) throws IOException {  
        write(new File(file).getAbsoluteFile(),data);  
    }
}
