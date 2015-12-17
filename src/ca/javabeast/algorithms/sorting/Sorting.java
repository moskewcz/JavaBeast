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
package ca.javabeast.algorithms.sorting;

/**
 *
 * @author alpenliebe <alpseinstein@gmail.com>
 */
public class Sorting {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }

    public void selectionSortRecursive(int[] data) {
        selectionSortRecursive(data, 0);
    }

    // Sort a subset of the array starting at the given index.
    private void selectionSortRecursive(int[] data, int start) {
        if (start > data.length - 1) {
            swap(data, start, findMinimumIndex(data, start));
            selectionSortRecursive(data, start + 1);
        }
    }

    private int findMinimumIndex(int[] data, int cur) {
        for (int i = cur + 1; i < data.length; i++) {
            if (data[i] < data[cur]) {
                cur = i;
            }
        }
        return cur;
    }

    private void swap(int[] data, int index1, int index2) {
        if (index1 != index2) {
            int tmp = data[index1];
            data[index1] = data[index2];
            data[index2] = tmp;
        }
    }
    
    public void insertionSort(int[] data){
        for(int which=1;which<data.length;which++){
            int val = data[which];
            for(int i = 0; i < which;i++){
                if(data[i]>val){
                    System.arraycopy(data, i, data, i+1, which-i);
                    data[i]=val;
                    break;
                }
            }
        }
    }
}
