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
package ca.javabeast.algorithms.bit;

/**
 *
 * @author alpenliebe <alpseinstein@gmail.com>
 */
public class BitManipulation {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }

    int numOnesInBinary(int number) {
        int numOnes = 0;
        while (number != 0) {
            if ((number & 1) == 1) {
                numOnes++;
            }
            number = number >>> 1;
        }
        return numOnes;
    }

    int numOnesInBinary2(int number) {
        int numOnes = 0;
        while (number != 0) {
            number = number & (number-1);
            numOnes++;
        }
        return numOnes;
    }
    
    int numOnesInBinary3(int number) {
        int numOnes = 0;
        while (number != 0) {
            if(number<0){
                numOnes++;
            }
            number=number<<1;
        }
        return numOnes;
    }

}
