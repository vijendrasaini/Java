package Visibility;

public class CommonResource {
    // public static boolean isStopped = false; // Multithreading Visibility Problem
    public static volatile boolean isStopped = false; // Multithreading Visibility Problem Solved
}
