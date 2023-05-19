package utils;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InsertionSort {
    private InsertionSort() {
    }

    public static <T> Stream<T> sort(Stream<T> stream, Comparator<? super T> comparator) {
        List<T> arr = stream.collect(Collectors.toList());
        int n = arr.size();
        for (int i = 1; i < n; i++) {
            T key = arr.get(i);
            int j = i - 1;

            while (j >= 0 && comparator.compare(arr.get(j), key) > 0) {
                arr.set(j + 1, arr.get(j));
                j = j - 1;
            }
            arr.set(j + 1, key);
        }
        return arr.stream();
    }
}
