/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hwhm;

/**
 *
 * @author lenfer
 */
public class HWHM {

    public static void main(String[] args) {
        MyHashMap my = new MyHashMap();
        for (int i = 0; i < 100; i++) {
            my.put(i, "a "+i);
        }
        System.out.println(my.get(0));
        my.remove(0);
        System.out.println(my.get(0));
        System.out.println(my.get(44));
        my.put(44, "ff");
        System.out.println(my.get(44));
        System.out.println(my.get(17));
    }
    
}
