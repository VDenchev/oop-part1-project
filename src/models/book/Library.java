package models.book;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

@XmlRootElement
public class Library {
    private Set<Book> books;

    public Library() {
        this.books = new LinkedHashSet<>();
    }

    @XmlElement(name = "book")
    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    public void clear() {
        books.clear();
    }

    public void addBook(Book book) {
        if(!books.add(book)) {
            throw new IllegalArgumentException("Book with id " + book.getId() + " already exists!");
        }
    }

    public void removeBookById(int id) {
        Iterator<Book> iterator = books.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getId() == id) {
                iterator.remove();
                return;
            }
        }
        throw new IllegalArgumentException("There is no book with id " + id);
    }

    public Book getBookByIsbn(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().toUpperCase().contains(isbn.toUpperCase())) {
                return book;
            }
        }
        return null;
    }

    public Set<Book> getBooksByTitle(String title) {
        Set<Book> matchingBooks = new LinkedHashSet<>();

        for (Book book : books) {
            if (book.getTitle().toUpperCase().contains(title.toUpperCase())) {
                matchingBooks.add(book);
            }
        }
        return matchingBooks;
    }

    public Set<Book> getBooksByAuthorFullName(String fullName) {
        Set<Book> matchingBooks = new LinkedHashSet<>();

        for (Book book : books) {
            if (book.getAuthor().getFullName().toUpperCase().contains(fullName.toUpperCase())) {
                matchingBooks.add(book);
            }
        }
        return matchingBooks;
    }

    public Set<Book> getBooksByKeyword(String keyword) {
        Set<Book> matchingBooks = new LinkedHashSet<>();

        for (Book book : books) {
            String[] keywords = book.getKeywords();
            for (String currKeyword : keywords) {
                if (currKeyword.equalsIgnoreCase(keyword)) {
                    matchingBooks.add(book);
                }
            }
        }
        return matchingBooks;
    }
}
