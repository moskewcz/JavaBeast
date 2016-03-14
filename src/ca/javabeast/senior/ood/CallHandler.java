/*
 * Copyright 2016 alpenliebe <alpseinstein@gmail.com>.
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
package ca.javabeast.senior.ood;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 *
 * @author alpenliebe <alpseinstein@gmail.com>
 */
public class CallHandler {

    static final int LEVELS = 3;
    static final int NUM_RESP = 5;
    private static CallHandler instance;
    private final List<Employee>[] employeesLevel;
    private final Deque<Call>[] callsLevel;

    private CallHandler() {
        employeesLevel = new ArrayList[LEVELS];
        callsLevel = new ArrayDeque[LEVELS];

        for (int i = 0; i < LEVELS; i++) {
            employeesLevel[i] = new ArrayList<>();
        }

        for (int i = 0; i < NUM_RESP; i++) {
            employeesLevel[0].add(new Respondent());
        }
        employeesLevel[1].add(new Manager());
        employeesLevel[2].add(new Director());
    }

    public static CallHandler getInstance() {
        if (instance == null) {
            instance = new CallHandler();
        }
        return instance;
    }

    private Employee getCallHandler(Call call) {
        List<Employee> emps = employeesLevel[call.level()];

        for (Employee emp : emps) {
            if (emp.isFree()) {
                return emp;
            }
        }
        if (call.level() == 1) {
            call.levelUp();
            return getCallHandler(call);
        }
        return null;
    }

    public void dispatchCall(Call call) {
        Employee emp = getCallHandler(call);
        if (emp == null) {
            call.reply("please wait for a next avaliable representative");
            callsLevel[call.level()].add(call);
        } else {
            emp.receiveCall(call);
        }
    }

    public void getNextCall(Employee emp) {
        Call call = callsLevel[emp.level()].poll();
        if (call != null) {
            emp.receiveCall(call);
        }
    }

}

class Respondent extends Employee {

    public Respondent() {
        super(0);
    }
}

class Manager extends Employee {

    public Manager() {
        super(1);
    }
}

class Director extends Employee {

    public Director() {
        super(2);
    }
}

abstract class Employee {

    private final int level;
    private final CallHandler ch;
    private boolean free;

    public Employee(int l) {
        level = l;
        free = true;
        ch = CallHandler.getInstance();
    }

    public boolean isFree() {
        return free;
    }

    public int level() {
        return level;
    }

    public void receiveCall(Call call) {
        free = false;
        //logics
    }

    public void handled(Call call) {
        call.disconnect();
        free = true;
        ch.getNextCall(this);
    }

    public void notHandled(Call call) {
        free = true;
        call.levelUp();
        ch.dispatchCall(call);
        ch.getNextCall(this);
    }

}

class Call {

    private int level;

    public Call() {
        level = 0;
    }

    void levelUp() {
        level++;
    }

    public int level() {
        return level;
    }

    public void disconnect() {
        reply("thanks for calling");
    }

    public void reply(String s) {

    }
}
