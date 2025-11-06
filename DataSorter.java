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
// Member 3: Quick Sort (Malisha)
// -------------------------
static class QuickSort {

    public static Result sort(int[] arr) {
        int[] a = Arrays.copyOf(arr, arr.length);
        Counter counter = new Counter();
        long start = System.nanoTime();
        quickSort(a, 0, a.length - 1, counter);
        long end = System.nanoTime();
        double time = (end - start) / 1e6;
        return new Result("Quick Sort", a, time, counter.count);
    }

    private static void quickSort(int[] a, int low, int high, Counter counter) {
        if (low < high) {
            int pi = partition(a, low, high, counter);
            quickSort(a, low, pi - 1, counter);
            quickSort(a, pi + 1, high, counter);
        }
    }

    private static int partition(int[] a, int low, int high, Counter counter) {
        int pivot = a[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            counter.count++;
            if (a[j] < pivot) {
                i++;
                int temp = a[i];
                a[i] = a[j];
                a[j] = temp;
            }
        }
        int temp = a[i + 1];
        a[i + 1] = a[high];
        a[high] = temp;
        return i + 1;
    }
}

   // -------------------------
// Member 4: Integration + Menu (Yasiru)
// -------------------------
static class DataManager {

    private static int[] data;

    public static void run() {
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n--- Data Sorter: Sorting Algorithm Comparison Tool ---");
            System.out.println("1. Enter numbers manually");
            System.out.println("2. Generate random numbers");
            System.out.println("3. Perform Bubble Sort");
            System.out.println("4. Perform Merge Sort");
            System.out.println("5. Perform Quick Sort");
            System.out.println("6. Compare all algorithms");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter numbers separated by spaces: ");
                    String[] parts = sc.nextLine().trim().split("\\s+");
                    data = new int[parts.length];
                    for (int i = 0; i < parts.length; i++)
                        data[i] = Integer.parseInt(parts[i]);
                    System.out.println("✅ Data set stored.");
                    break;

                case "2":
                    System.out.print("Enter number of elements: ");
                    int n = Integer.parseInt(sc.nextLine());
                    data = new Random().ints(n, 1, 100).toArray();
                    System.out.println("✅ Random data generated.");
                    break;

                case "3":
                    if (checkData()) System.out.println(BubbleSort.sort(data));
                    break;

                case "4":
                    if (checkData()) System.out.println(MergeSort.sort(data));
                    break;

                case "5":
                    if (checkData()) System.out.println(QuickSort.sort(data));
                    break;

                case "6":
                    if (checkData()) compareAll();
                    break;

                case "0":
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
        sc.close();
        System.out.println("👋 Program terminated.");
    }

    private static boolean checkData() {
        if (data == null || data.length == 0) {
            System.out.println("⚠ Please enter or generate data first!");
            return false;
        }
        return true;
    }

    private static void compareAll() {
        Result b = BubbleSort.sort(data);
        Result m = MergeSort.sort(data);
        Result q = QuickSort.sort(data);

        System.out.println("\n--- Algorithm Comparison ---");
        System.out.printf("%-15s %-15s %-15s%n", "Algorithm", "Time (ms)", "Operations");
        System.out.println("---------------------------------------------");
        System.out.printf("%-15s %-15.3f %-15d%n", b.name, b.time, b.operations);
        System.out.printf("%-15s %-15.3f %-15d%n", m.name, m.time, m.operations);
        System.out.printf("%-15s %-15.3f %-15d%n", q.name, q.time, q.operations);
    }
}
