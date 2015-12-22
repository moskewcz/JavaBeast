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
package ca.javabeast.concurrent;

import java.util.Random;

/**
 *
 * @author alpenliebe <alpseinstein@gmail.com>
 */
public class Concurrent {

    class Account {

        int userNumber;
        String userLastName;
        String userFirstName;
        double userBalance;

        public boolean deposit(double amount) {
            double newBalance;
            if (amount < 0.0) {
                return false; /* Can’t deposit negative amount */

            } else {
                synchronized (this) {
                    newBalance = userBalance + amount;
                    userBalance = newBalance;
                }
                return true;
            }
        }

        public boolean withdraw(double amount) {
            double newBalance;
            synchronized (this) {
                if (amount < 0.0 || amount > userBalance) {
                    return false; /* Negative withdrawal or insufficient funds */

                } else {
                    newBalance = userBalance - amount;
                    userBalance = newBalance;
                    return true;
                }
            }
        }
    }

    public static void main(String[] args) {

    }

    public void busyWait() {
        Object theLock = new Object();
        synchronized (theLock) {
            Thread task = new TheTask(theLock);
            task.start();
            try {
                theLock.wait();
            } catch (InterruptedException e) {
                // do something if interrupted
            }
        }
    }

    class TheTask extends Thread {

        private Object theLock;

        public TheTask(Object theLock) {
            this.theLock = theLock;
        }

        public void run() {
            synchronized (theLock) {
                // do the task
                theLock.notify();
            }
        }
    }

    public void busyWait2() {
        Thread task = new TheTask2();
        synchronized (task) {
            task.start();
            try {
                task.wait();
            } catch (InterruptedException e) {
                // do something if interrupted
            }
        }
    }

    class TheTask2 extends Thread {

        public void run() {
            synchronized (this) {
                // do the task
                this.notify();
            }
        }
    }

    class IntBuffer {

        private int index;
        private int[] buffer = new int[8];

        public synchronized void add(int num) {

            while (index == buffer.length - 1) {
                try {
                    wait();
                } catch (InterruptedException e) {
                }
            }
            buffer[index++] = num;
            notifyAll();
        }

        public synchronized int remove() {
            while (index == 0) {
                try {
                    wait();
                } catch (InterruptedException e) {
                }
            }
            int ret = buffer[--index];
            notifyAll();
            return ret;
        }
    }

    class Producer extends Thread {

        private IntBuffer buffer;

        public Producer(IntBuffer buffer) {
            this.buffer = buffer;
        }

        public void run() {
            Random r = new Random();
            while (true) {
                int num = r.nextInt();
                buffer.add(num);
                System.out.println("Produced " + num);
            }
        }
    }

    class Consumer extends Thread {

        private IntBuffer buffer;

        public Consumer(IntBuffer buffer) {
            this.buffer = buffer;
        }

        public void run() {
            while (true) {
                int num = buffer.remove();
                System.out.println("Consumed " + num);
            }
        }
    }

    class DiningPhilosophers {
        // Each "fork" is just an Object we synchronize on

        private Object[] forks;
        private Philosopher[] philosophers;
        // Prepare the forks and philosophers

        private DiningPhilosophers(int num) {
            forks = new Object[num];
            philosophers = new Philosopher[num];
            for (int i = 0; i < num; ++i) {
                forks[i] = new Object();
                int fork1 = i;
                int fork2 = (i + 1) % num;
                if ((i % 2) == 0) {
                    philosophers[i] = new Philosopher(i, fork1,fork2);
                } else {
                    philosophers[i] = new Philosopher(i, fork2,fork1);
                }
            }
        }
        // Start the eating process

        public void startEating() throws InterruptedException {
            for (int i = 0; i < philosophers.length; ++i) {
                philosophers[i].start();
            }
            // Suspend the main thread until the first philosopher
            // stops eating, which will never happen -- this keeps
            // the simulation running indefinitely
            philosophers[0].join();
        }

        // Each philosopher runs in its own thread.
        private class Philosopher extends Thread {

            private int id;
            private int fork1;
            private int fork2;

            Philosopher(int id, int fork1, int fork2) {
                this.id = id;
                this.fork1 = fork1;
                this.fork2 = fork2;
            }

            public void run() {
                status("Ready to eat using forks " + fork1
                        + " and " + fork2);
                while (true) {
                    status("Picking up fork " + fork1);
                    synchronized (forks[fork1]) {
                        status("Picking up fork " + fork2);
                        synchronized (forks[fork2]) {
                            status("Eating");
                        }
                    }
                }
            }

            private void status(String msg) {
                System.out.println("Philosopher " + id
                        + ": " + msg);
            }
        }

    }
    
    public void dine() {
        try {
            DiningPhilosophers d = new DiningPhilosophers(5);
            d.startEating();
        } catch (InterruptedException e) {
        }
    }
}
