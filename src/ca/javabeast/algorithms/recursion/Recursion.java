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

package ca.javabeast.algorithms.recursion;

/**
 *
 * @author alpenliebe <alpseinstein@gmail.com>
 */
public class Recursion {
    public static int findTarget (int[] arr,int n)throws BSException{
        int left=0,right=arr.length-1,range=0,i=0;
        while(true){
            range = right-left;
            if(range<0)
                throw new BSException("Limits reversed");
            else if(range==0 && arr[left] != n)
                throw new BSException("no target found");
            else if( arr[left] > arr[right] ){
                throw new BSException("Array not sorted");
            }
            i = range/2+left;
            if(arr[i]>n){
                right=i-1;
            } else if(arr[i]<n){
                left=i+1;
            } else {
                return i;
            }
        }
    }

    public static int findTarget2(int[] arr,int left,int right,int n) throws BSException{
        int range = right-left;
        if(range<0)
            throw new BSException("Limits reversed");
        else if(range == 0 && arr[left] != n)
            throw new BSException("no target found");
        else if( arr[left] > arr[right] ){
            throw new BSException("Array not sorted");
        }
        int mid = range/2+left;
        if(arr[mid]>n){
            return findTarget2(arr,left,mid-1,n);
        } else if(arr[mid]<n){
            return findTarget2(arr,mid+1,right,n);
        } else {
            return mid;
        }
    }

    private static class BSException extends Exception {

        public BSException(String message) {
            super(message);
        }
    }
}
