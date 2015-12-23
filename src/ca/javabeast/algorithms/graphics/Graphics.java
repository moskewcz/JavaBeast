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
package ca.javabeast.algorithms.graphics;

/**
 *
 * @author alpenliebe <alpseinstein@gmail.com>
 */
public class Graphics {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }

    public void drawEighthOfCircle(int radius) {
        int x = 0, y = radius;
        while (x <= y) {
            y = (int) (Math.sqrt(radius * radius - x * x) + 0.5);
            setPixel(x, y);
            x++;
        }
    }

    private void setPixel(int x, int y) {

    }

    boolean overlap(Rect a, Rect b) {
        return (a.ul.x <= b.lr.x
                && a.ul.y >= b.lr.y
                && a.lr.x >= b.ul.x
                && a.lr.y <= b.ul.y);

    }

    class Point {

        public int x;
        public int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    class Rect {

        public Point ul;
        public Point lr;

        public Rect(Point ul, Point lr) {
            this.ul = ul;
            this.lr = lr;

        }
    }

}
