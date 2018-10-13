package java;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * You are given a class Solution and an inner class Inner.Private.
 * The main method of class Solution takes an integer  as input.
 * The powerof2 in class Inner.Private checks whether a number is a power of 2.
 * You have to call the method powerof2 of the class Inner.Private from the main method of the class Solution.
 */
public class Reflection {

    public static void main(String[] args) throws Exception {
        int num = 16;
        Object o;

        Class<?> innerClass = Reflection.Inner.class;
        Object innerInstance = innerClass.newInstance();

        Class<?> privateClass = Reflection.Inner.Private.class;
        Constructor<?> privateConstructor = privateClass.getDeclaredConstructors()[0];
        privateConstructor.setAccessible(true);
        o = privateConstructor.newInstance(innerInstance);

        Method method = privateClass.getDeclaredMethod("powerof2", int.class);
        method.setAccessible(true);

        System.out.println("" + num + " is " + method.invoke(o, num));

        System.out.println("An instance of class: " + o.getClass().getCanonicalName() + " has been created");
    }

    static class Inner {

        private class Private {

            private String powerof2(int num) {
                return ((num & num - 1) == 0) ? "power of 2" : "not a power of 2";
            }
        }
    }
}
