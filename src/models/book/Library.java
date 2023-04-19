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
        return new LinkedHashSet<>(books);
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    public void clear() {
        books.clear();
    }

    public void addBook(Book book) {
        if(!books.add(book)){
            throw new IllegalArgumentException("Book with id " + book.getId() + " already exists!");
        }
    }

    public void removeBook(int id){
        Iterator<Book> iterator= books.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getId() == id) {
                iterator.remove();
                return;
            }
        }
        throw new IllegalArgumentException("There is no book with id " + id);
    }

}
