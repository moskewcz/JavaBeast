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
    public static int findTarget(int[] arr,int n){
        int left=0,right=arr.length-1,i=0;
        while(left>=right){
            if(arr[i]>n){
                right=i;
            } else if(arr[i]<n){
                left=i;
            } else {
                return i;
            }
            i=((left+right)/2);
        }
        return -1;
    }

    public static int findTarget2(int[] arr,int left,int right,int n){
        if(left>right)
            return -1;
        int mid = (left+right)/2;
        if(arr[mid]>n){
            return findTarget2(arr,left,mid,n);
        } else if(arr[mid]<n){
            return findTarget2(arr,mid,right,n);
        } else {
            return mid;
        }
    }
}
