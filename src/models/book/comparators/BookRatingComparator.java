package models.book.comparators;

import models.book.Book;

import java.util.Comparator;

public class BookRatingComparator implements Comparator<Book> {
    @Override
    public int compare(Book firstBook, Book secondBook) {
        return firstBook.getRating().compareTo(secondBook.getRating());
    }
}
