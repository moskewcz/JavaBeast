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
package ca.javabeast.algorithms.designpattern;

/**
 *
 * @author alpenliebe <alpseinstein@gmail.com>
 */
public class Design {

}

// Implements a simple logging class using a singleton.
class Logger {
    // Create and store the singleton.


    // Prevent anyone else from creating this class.



    private Logger() {
    }
        // Return the singleton instance.

    // Inner class initializes instance on load, won't be loaded
    // until referenced by getInstance()
    private static class LoggerHolder {

        public static final Logger instance = new Logger();
    }
    // Return the singleton instance.

    public static Logger getInstance() {
        return LoggerHolder.instance;
    }
        // Log a string to the console.
    //
    //
    //example: Logger.getInstance().log("this is a test");
    //

    public void log(String msg) {
        System.out.println(System.currentTimeMillis() + ": " + msg);
    }
    
    @Override
    public Logger clone() throws CloneNotSupportedException{
        throw new CloneNotSupportedException();
    }
}
