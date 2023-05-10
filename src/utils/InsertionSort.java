package utils;

import java.util.Comparator;
import java.util.stream.Stream;

public class InsertionSort {
    public static <T> Stream<T> sort(Stream<T> stream, Comparator<? super T> comparator) {
        T[] arr = (T[])stream.toArray();
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            T key = arr[i];
            int j = i - 1;

            while (j >= 0 && comparator.compare(arr[j], key) > 0) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
        return Stream.of(arr);
    }
}
