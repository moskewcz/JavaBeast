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
package ca.javabeast.elementary;

import java.util.Arrays;
import java.util.Comparator;

/**
 *
 * @author Alpenliebe <alpseinstein@gmail.com>
 */
public class B3_Compare {

    public static void main(String[] args) {

        String arr[] = new String[]{"ab", "bc", "ac"};
        Arrays.sort(arr, new sampleComparator());
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
        
        User[] users = new User[]{new User("a",30),new User("b",20)};
        Arrays.sort(users);
        for(int i=0;i<users.length;i++){
            System.out.println(users[i]);
        }
    }
}

//Comparator
class sampleComparator implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        return toInt(o1) - toInt(o2);
    }

    private int toInt(Object o) {
        String str = (String) o;
        str = str.replaceAll("a", "1")
                .replaceAll("b", "2")
                .replaceAll("c", "3");
        return Integer.parseInt(str);
    }
}

//Comparable
class User implements Comparable{

    private String id;
    private int age;

    public User(String id, int age) {
        this.id = id;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int compareTo(Object o) {
        return this.age - ((User) o).getAge();
    }

    public String toString(){
        return this.getId() + " " + this.getAge();
    }
}
