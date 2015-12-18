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
    
    public void selectionSort(int[] data) {
        for(int start=0;start<data.length-1;start++){
            insert(data, start, findMinimumIndex(data, start));
        }
    }
    //stable sort
    private void insert(int[] data, int start, int index) {
        if(index>start){
            int temp=data[index];
            System.arraycopy(data, start, data, start+1, index-start);
            data[start]=temp;
        }
    }

    public void selectionSortRecursive(int[] data) {
        selectionSortRecursive(data, 0);
    }

    // Sort a subset of the array starting at the given index.
    private void selectionSortRecursive(int[] data, int start) {
        if (start < data.length - 1) {
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
    
    public void quickSort(int[] data,int left,int right){
        int range = right-left;
        int i = left, j = right;
        int pivot = data[left+range/2];
        while (i <= j) {
            while (data[i]<pivot){
                i++;
            }
            while (data[j]<pivot){
                j--;
            }
            if (i <= j) {
                  swap(data,i,j);
                  i++;
                  j--;
            }
            
        }
        if (left < i - 1)
            quickSort(data,left,i-1);
        if (i < right)
            quickSort(data,i,right);
    } 
   
    public void mergeSort(int[] data,int left,int right){
        int range = right-left;
        if(range>0){
            int mid = left+range/2;
            mergeSort(data,left,mid);
            mergeSort(data,mid,right);
            mergePart(data,left,mid,right);
        }
    } 

    private void mergePart(int[] data, int left, int mid, int right) {
        int[] temp = new int[right+1];
        System.arraycopy(data, left, temp, left, right-left+1);
        int i=left,j=mid + 1,k=left;
        while(i<=mid&&j<=right){
            if(temp[i]<=temp[j]){
                data[k]=temp[i++];
            } else {
                data[k]=temp[j++];
            }
            k++;
        }
        while(i<=mid){
            data[k++]=temp[i++];
        }
    }

}
