package ca.javabeast.algorithms;

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

    public static void main(String[] args) {

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
}
