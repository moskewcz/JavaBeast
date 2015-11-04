package ca.javabeast.algorithms;

import java.util.Arrays;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author alpenliebe <alpseinstein@gmail.com>
 */
public class B17_Basic {

    private static int j;

    public static void main(String[] args) {
        //14,15,16
        TestLock();
    }

    public static void TestLock() {

        Lock lock = new ReentrantLock();

        for (int i = 0; i < 2; i++) {
            new Thread() {

                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    while (true) {
                        /*synchronized (ThreadTest.this) {			
                         System.out.println("j--=" + j--);
                         //exception throwed
                         }*/
                        lock.lock();
                        try {
                            System.out.println("j++=" + j++);
                        } finally {
                            lock.unlock();
                        }
                    }
                }
            }.start();
            new Thread() {
                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    while (true) {
                        /*synchronized (ThreadTest.this) {
                         System.out.println("j++=" + j++);	
                         }*/
                        lock.lock();
                        try {
                            System.out.println("j--=" + j--);
                        } finally {
                            lock.unlock();
                        }
                    }
                }
            }.start();
        }
    }

    public static void testAssert() {
        int i = 0;
        for (i = 0; i < 5; i++) {
            System.out.println(i);
        }
        --i;
        assert i == 5;
    }

    public static void multiThread() {
        new Thread(new MultiThread.Thread1()).start();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(new MultiThread.Thread2()).start();
    }

    public static void breakLoop() {
        label:
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.println(
                        "i =" + i + ",j =" + j
                );
                if (j == 5) {
                    break label;
                }
            }
        }
    }

    public static void findNum() {
        int arr[][] = {{1, 2, 3}, {4, 5, 6, 7}, {9}};

        boolean found = false;

        for (int i = 0; i < arr.length && !found; i++) {

            for (int j = 0; j < arr[i].length; j++) {

                System.out.println("i=" + i + ",j=" + j);

                if (arr[i][j] == 5) {

                    found = true;

                    break;

                }

            }

        }
    }

    public static void calculator() {
        int a = Integer.MAX_VALUE;
        int b = Integer.MAX_VALUE;
        int sum = a + b;
        System.out.println("a=" + a + ",b=" + b + ",sum=" + sum);
    }

    public static void permutation() {
        String str = "xafdvs";
        char[] arr1 = str.toCharArray();
        char[] arr2 = Arrays.copyOf(arr1, arr1.length);
        for (int i = 0; i < arr1.length - 1; i++) {
            for (int j = i + 1; j < arr2.length; j++) {
                System.out.println(arr1[i] + "," + arr2[j]);
            }
        }
    }

    public static void testString() {
        String s1 = "a";
        String s2 = s1 + "b";
        String s3 = "a" + "b";
        System.out.println(s2 == "ab");
        System.out.println(s3 == "ab");
    }

}

//BigInteger calculator
class BigInteger {

    int sign;
    byte[] val;

    public BigInteger(String s) {
        this(0, new byte[]{});
    }

    public BigInteger(int sign, byte[] val) {
        this.sign = sign;
        this.val = val;
    }

    public BigInteger add(BigInteger other) {
        return null;
    }

    public BigInteger substract(BigInteger other) {
        return null;
    }

    public BigInteger multiply(BigInteger other) {
        return null;
    }

    public BigInteger divide(BigInteger other) {
        return null;
    }

}

class VariantTest {

    public static int staticVar = 0;
    public int instanceVar = 0;

    public VariantTest() {
        staticVar++;
        instanceVar++;
        System.out.println("staticVar=" + staticVar + ",instanceVar=" + instanceVar);
    }
}

class Outer {

    static int x;

    static class Inner {

        void test() {
            System.out.println(x);
        }
    }
}

class MultiThread {

    public static class Thread1 implements Runnable {

        @Override
        public void run() {
            synchronized (MultiThread.class) {
                System.out.println("enter thread1...");
                System.out.println("thread1 is waiting");
                try {
                    MultiThread.class.wait();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block  
                    e.printStackTrace();
                }
                System.out.println("thread1 is going on...");
                System.out.println("thread1 is being over!");
            }
        }
    }

    public static class Thread2 implements Runnable {

        @Override
        public void run() {
            // TODO Auto-generated method stub  
            synchronized (MultiThread.class) {

                System.out.println("enter thread2...");

                System.out.println("thread2 notify other thread can release wait status..");
                MultiThread.class.notify();
                System.out.println("thread2 is sleeping ten millisecond...");
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block  
                    e.printStackTrace();
                }
                System.out.println("thread2 is going on...");
                System.out.println("thread2 is being over!");
            }
        }
    }
}
