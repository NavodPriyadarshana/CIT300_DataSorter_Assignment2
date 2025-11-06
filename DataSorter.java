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
// Member 2: Merge Sort (Pulindu)
// -------------------------
static class MergeSort {

    public static Result sort(int[] arr) {
        int[] a = Arrays.copyOf(arr, arr.length);
        Counter counter = new Counter();
        long start = System.nanoTime();
        mergeSort(a, 0, a.length - 1, counter);
        long end = System.nanoTime();
        double time = (end - start) / 1e6;
        return new Result("Merge Sort", a, time, counter.count);
    }

    private static void mergeSort(int[] a, int left, int right, Counter counter) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(a, left, mid, counter);
            mergeSort(a, mid + 1, right, counter);
            merge(a, left, mid, right, counter);
        }
    }

    private static void merge(int[] a, int left, int mid, int right, Counter counter) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        int[] L = new int[n1];
        int[] R = new int[n2];
        for (int i = 0; i < n1; i++) L[i] = a[left + i];
        for (int j = 0; j < n2; j++) R[j] = a[mid + 1 + j];

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            counter.count++;
            if (L[i] <= R[j]) a[k++] = L[i++];
            else a[k++] = R[j++];
        }
        while (i < n1) a[k++] = L[i++];
        while (j < n2) a[k++] = R[j++];
    }
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
