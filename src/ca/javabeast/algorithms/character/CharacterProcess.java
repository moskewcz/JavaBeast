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
package ca.javabeast.algorithms.character;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author alpenliebe <alpseinstein@gmail.com>
 */
public class CharacterProcess {

    public static Character findFirstNonrepeatedCharacter(String str) {
        HashMap<Character, Integer> charHash = new HashMap<Character, Integer>();

        int i;
        Character c;
        // Scan str, building hash table
        for (i = 0; i < str.length(); i++) {
            c = str.charAt(i);
            if (charHash.containsKey(c)) {
        // Increment count corresponding to c
                charHash.put(c, charHash.get(c) + 1);
            } else {
                charHash.put(c, 1);
            }
        }
        // Search hash table in order of str
        for (i = 0; i < str.length(); i++) {
            c = str.charAt(i);
            if (charHash.get(c) == 1) {
                return c;
            }
        }
        return null;
    }

    public static Character findFirstNonrepeatedCharacter2(String s) {
        Map<Integer, Character> map = new HashMap<>();
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (set.contains(c)) {
                map.remove(i);
            } else {
                map.put(i, c);
            }
            set.add(c);
        }

        int min = s.length();
        for (Integer key : map.keySet()) {
            if (key < min) {
                min = key;
            }
        }
        return min == s.length() ? null : s.charAt(min);
    }
}
