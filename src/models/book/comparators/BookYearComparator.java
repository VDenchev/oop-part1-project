package models.book.comparators;

import models.book.Book;

import java.util.Comparator;

public class BookYearComparator implements Comparator<Book> {
    @Override
    public int compare(Book firstBook, Book secondBook) {
        return Integer.compare(firstBook.getPublishingYear(), secondBook.getPublishingYear());
    }
}
