// DataSorter.java
// CIT300 - Practical Assignment 2
// Data Sorter: Sorting Algorithm Comparison Tool
// Members: Navod, Malisha, Yasiru, Pulindu

import java.util.*;

public class DataSorter {

    // -------------------------
    // Member 1: Bubble Sort (Navod)
// -------------------------
static class BubbleSort {
    public static Result sort(int[] arr) {
        int[] a = Arrays.copyOf(arr, arr.length);
        int n = a.length;
        int operations = 0;
        long start = System.nanoTime();

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                operations++;
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }

        long end = System.nanoTime();
        double time = (end - start) / 1e6;
        return new Result("Bubble Sort", a, time, operations);
    }
}

    // -------------------------
    // Member 2: Merge Sort
    // -------------------------
    static class MergeSort {
        // Malisha will implement this
    }

    // -------------------------
    // Member 3: Quick Sort
    // -------------------------
    static class QuickSort {
        // Yasiru will implement this
    }

    // -------------------------
    // Member 4: Integration + Menu
    // -------------------------
    static class DataManager {
        // Pulindu will implement the menu, data input, and comparison
    }

    public static void main(String[] args) {
        // Will call DataManager.run();
    }
}
