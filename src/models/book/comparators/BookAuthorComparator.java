package models.book.comparators;

import models.book.Book;

import java.util.Comparator;

public class BookAuthorComparator implements Comparator<Book> {
    @Override
    public int compare(Book firstBook, Book secondBook) {
        return firstBook.getAuthor().getFullName().compareTo(secondBook.getAuthor().getFullName());
    }
}
