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
package ca.javabeast.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author Alpenliebe <alpseinstein@gmail.com>
 */
public class B15_Atomic {

    public static void main(String[] args) {
        //startThread4();

        AtomicInteger ai = new AtomicInteger();
        System.out.println(ai);
        ai.getAndIncrement();
        System.out.println(ai);
    }

    public static void startThread4() {
        CasCounter cas = new CasCounter();
        Thread t1 = new Thread(new Thread() {
            public void run() {
                for (int i = 0; i < 20; i++) {
                    cas.increment();
                    System.out.println(Thread.currentThread().getName() + "-" + cas.getValue());
                }
            }
        });
        Thread t2 = new Thread(new Thread() {
            public void run() {
                for (int i = 0; i < 20; i++) {
                    cas.increment();
                    System.out.println(Thread.currentThread().getName() + "-" + cas.getValue());
                }
            }
        });
        t1.start();
        t2.start();
    }
}

//synchronized
class Counter {

    private int value;

    public synchronized int getValue() {
        return value;
    }

    public synchronized int increment() {
        return ++value;
    }

    public synchronized int decrement() {
        return --value;
    }
}

//Compare and swap
class SimulatedCAS {

    private int value;

    public synchronized int getValue() {
        return value;
    }

    public synchronized int compareAndSwap(int expectedValue, int newValue) {
        int oldValue = value;
        if (value == expectedValue) {
            value = newValue;
        }
        return oldValue;
    }
}

class CasCounter {

    private SimulatedCAS value;

    public CasCounter() {
        value = new SimulatedCAS();
    }

    public int getValue() {
        return value.getValue();
    }

    public int increment() {
        int oldValue = value.getValue();
        while (value.compareAndSwap(oldValue, oldValue + 1) != oldValue) {
            oldValue = value.getValue();
        }
        return oldValue + 1;
    }
}
