package models.book.comparators;

import models.book.Book;

import java.util.Comparator;

public class ReverseComparator implements Comparator<Book> {
    private Comparator<Book> comparator;

    public ReverseComparator(Comparator<Book> comparator) {
        this.comparator = comparator;
    }

    @Override
    public int compare(Book firstBook, Book secondBook) {
        return comparator.compare(secondBook, firstBook);
    }
}
