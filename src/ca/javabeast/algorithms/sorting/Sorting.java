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

import java.util.Arrays;
import java.util.Comparator;

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
        for (int start = 0; start < data.length - 1; start++) {
            insert(data, start, findMinimumIndex(data, start));
        }
    }

    //stable sort
    private void insert(int[] data, int start, int index) {
        if (index > start) {
            int temp = data[index];
            System.arraycopy(data, start, data, start + 1, index - start);
            data[start] = temp;
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

    public void insertionSort(int[] data) {
        for (int which = 1; which < data.length; which++) {
            int val = data[which];
            for (int i = 0; i < which; i++) {
                if (data[i] > val) {
                    System.arraycopy(data, i, data, i + 1, which - i);
                    data[i] = val;
                    break;
                }
            }
        }
    }

    public void quickSort(int[] data, int left, int right) {
        int i = left, j = right;
        int pivot = data[ ( left + right ) / 2 ];
        while (i <= j) {
            while (data[i] < pivot) {
                i++;
            }
            while (data[j] < pivot) {
                j--;
            }
            if (i <= j) {
                swap(data, i, j);
                i++;
                j--;
            }

        }
        if (left < i - 1) {
            quickSort(data, left, i - 1);
        }
        if (i < right) {
            quickSort(data, i, right);
        }
    }

    public void mergeSort(int[] data, int left, int right) {
        int range = right - left;
        if (range > 0) {
            int mid = left + range / 2;
            mergeSort(data, left, mid);
            mergeSort(data, mid, right);
            mergePart(data, left, mid, right);
        }
    }

    private void mergePart(int[] data, int left, int mid, int right) {
        int[] temp = new int[right + 1];
        System.arraycopy(data, left, temp, left, right - left + 1);
        int i = left, j = mid + 1, k = left;
        while (i <= mid && j <= right) {
            if (temp[i] <= temp[j]) {
                data[k] = temp[i++];
            } else {
                data[k] = temp[j++];
            }
            k++;
        }
        while (i <= mid) {
            data[k++] = temp[i++];
        }
    }

    
    public void sortEmployees( Employee[] employees ){
        Comparator<Employee> cp = new Comparator<Employee>() {
            @Override
            public int compare(Employee e1, Employee e2) {
                // Compare surnames
                int ret = e1.surname.compareToIgnoreCase(e2.surname);
                if (ret == 0) { //Compare givennames if surnames are the same
                    ret = e1.givenname.compareToIgnoreCase(e2.givenname);
                }
                return ret;
            }
        };

        Arrays.sort( employees, cp );
    }
    
    public void sortEmployeesStable(Employee[] data){
        for(int i=0;i<data.length;i++ ){
            data[i].sequence=i;
        }
        shakySort( data, new Comparator<Employee>() {

            @Override
            public int compare(Employee o1, Employee o2) {
                int ret = o1.surname.compareTo(o2.surname);
                if(ret==0)
                    ret = o1.givenname.compareTo(o2.givenname);
                if(ret==0)
                    ret = o1.sequence-o2.sequence;
                return ret;
            }
        } );
    }
    
    public void sort(Employee[] data) {
        Comparator<Employee> cp = new Comparator<Employee>() {
            @Override
            public int compare(Employee e1, Employee e2) {
                // Compare surnames
                int ret = e1.surname.compareToIgnoreCase(e2.surname);
                if (ret == 0) { //Compare givennames if surnames are the same
                    ret = e1.givenname.compareToIgnoreCase(e2.givenname);
                }
                return ret;
            }
        };

        quickSort(data, cp, 0, data.length - 1);
    }

    private void quickSort(Employee[] data, Comparator cp, int left, int right) {
        int i = left, j = right;
        Employee pivot = data[ ( left + right ) / 2 ];
        while (true) {
            while (cp.compare(data[i], pivot) >0) {
                i++;
            }
            while (cp.compare(data[j], pivot) <0) {
                j--;
            }
            if (i > j) {
                break;
            }

            swap(data, i, j);
            i++;
            j--;

        }
        if (left < i - 1) {
            quickSort(data, cp, left, i - 1);
        }
        if (i < right) {
            quickSort(data, cp, i, right);
        }
    }

    private void swap(Employee[] data, int index1, int index2) {
        if (index1 != index2) {
            Employee tmp = data[index1];
            data[index1] = data[index2];
            data[index2] = tmp;
        }
    }

    private void shakySort(Employee[] data, Comparator<Employee> comparator) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static class Employee {

        public String extension;
        public String givenname;
        public String surname;
        public int sequence;

        public Employee() {
        }
    }
}
