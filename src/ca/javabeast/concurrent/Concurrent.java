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
}
