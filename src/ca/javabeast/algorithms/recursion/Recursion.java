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

    public static int findTarget(int[] arr, int n) throws BSException {
        int left = 0, right = arr.length - 1, range = 0, i = 0;
        while (true) {
            range = right - left;
            if (range < 0) {
                throw new BSException("Limits reversed");
            } else if (range == 0 && arr[left] != n) {
                throw new BSException("no target found");
            } else if (arr[left] > arr[right]) {
                throw new BSException("Array not sorted");
            }
            i = range / 2 + left;
            if (arr[i] > n) {
                right = i - 1;
            } else if (arr[i] < n) {
                left = i + 1;
            } else {
                return i;
            }
        }
    }

    public static int findTarget2(int[] arr, int left, int right, int n) throws BSException {
        int range = right - left;
        if (range < 0) {
            throw new BSException("Limits reversed");
        } else if (range == 0 && arr[left] != n) {
            throw new BSException("no target found");
        } else if (arr[left] > arr[right]) {
            throw new BSException("Array not sorted");
        }
        int mid = range / 2 + left;
        if (arr[mid] > n) {
            return findTarget2(arr, left, mid - 1, n);
        } else if (arr[mid] < n) {
            return findTarget2(arr, mid + 1, right, n);
        } else {
            return mid;
        }
    }

    private static class BSException extends Exception {

        public BSException(String message) {
            super(message);
        }
    }

    public static void permuteString(String str) {
        boolean[] arr = new boolean[str.length()];
        StringBuilder sb = new StringBuilder();
        doPermute(arr, str, sb);
    }

    public static void doPermute(boolean[] arr, String str, StringBuilder sb) {
        if (sb.length() == arr.length) {
            System.out.println(sb.toString());
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i]) {
                continue;
            }
            sb.append(str.charAt(i));
            arr[i] = true;
            doPermute(arr, str, sb);
            arr[i] = false;
            sb.setLength(sb.length() - 1);
        }
    }

    public static void combineString(String str) {
        boolean[] arr = new boolean[str.length()];
        StringBuilder sb = new StringBuilder();
        doCombine(str, sb, 0);
    }

    public static void doCombine(String str, StringBuilder sb, int start) {

        for (int i = start; i < str.length(); i++) {
            sb.append(str.charAt(i));
            System.out.println(sb.toString());
            if (i < str.length()) {
                doCombine(str, sb, i + 1);
            }
            sb.setLength(sb.length() - 1);
        }
    }

    public static void permuteTele2(int[] arr) {
        int len = arr.length;
        char[] words = new char[len];
        for (int i = 0; i < len; i++) {
            if (arr[i] > 1 && arr[i] < 10) {
                words[i] = getCharKey(arr[i], 1);
            } else {
                words[i] = (char) (arr[i] + '0');
            }
        }
        while (true) {
            System.out.println(new String(words));
            for (int i = len - 1; i > -1; i--) {
                if (i < 0) {
                    return;
                }
                char a = getCharKey(arr[i], 1);
                char b = getCharKey(arr[i], 2);
                char c = getCharKey(arr[i], 3);
                if (words[i] == c || words[i] == '0' || words[i] == '1') {
                    words[i] = a;
                } else if (words[i] == b) {
                    words[i] = c;
                    break;
                } else {
                    words[i] = b;
                    break;
                }
            }
        }
    }

    public static void permuteTele(int[] arr) {
        StringBuilder sb = new StringBuilder();
        doPermuteTele(arr, sb, 0);
    }

    public static void doPermuteTele(int[] arr, StringBuilder sb, int start) {
        if (start == arr.length) {
            System.out.println(sb.toString());
            return;
        }

        int n = arr[start];
        if (n > 1 && n < 10) {
            for (int j = 1; j < 4; j++) {
                sb.append(getCharKey(n, j));
                doPermuteTele(arr, sb, start + 1);
                sb.setLength(sb.length() - 1);
            }

        } else {
            sb.append(n);
            doPermuteTele(arr, sb, start + 1);
            sb.setLength(sb.length() - 1);
        }

    }

    static char getCharKey(int telephoneKey, int place) {
        char c = 'a';
        if (place == 1) {
            c = 'b';
        } else if (place == 2) {
            c = 'c';
        }
        return c;
    }

    public static void main(String[] args) {
        permuteString("abcd");
        combineString("123");
        int[] a = {0, 0, 0, 1, 2, 0, 3};
        permuteTele2(a);
    }
}
