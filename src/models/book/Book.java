package models.book;


import jakarta.xml.bind.annotation.*;

@XmlRootElement
@XmlType(propOrder = {"id", "isbn", "title", "author", "genre", "publishingYear", "keywords", "description", "rating"})
public class Book {
    private int id ;
    private String isbn;
    private String title;
    private Author author;
    private String genre;
    private int publishingYear;
    private String[] keywords;
    private String description;
    private Rating rating;

    private Book(Builder builder) {
        this.id = builder.id;
        this.isbn = builder.isbn;
        this.title = builder.title;
        this.author = builder.author;
        this.genre = builder.genre;
        this.publishingYear = builder.publishingYear;
        this.keywords = builder.keywords;
        this.description = builder.description;
        this.rating = builder.rating;
    }

    protected Book() {}

    public static class Builder {
        private int id ;
        private String isbn;
        private String title;
        private Author author;
        private String genre;
        private int publishingYear;
        private String[] keywords;
        private String description;
        private Rating rating;

        public Builder(int id, String isbn, String title, Author author, int publishingYear) {
            this.id = id;
            this.isbn = isbn;
            this.title = title;
            this.author = author;
            this.publishingYear = publishingYear;
            genre = "none";
            keywords = new String[]{"none"};
            description = "No description.";
            rating = Rating.ZERO_STARS;
        }

        public Builder setGenre(String genre) {
            this.genre = genre;
            return this;
        }

        public Builder setKeywords(String[] keywords) {
            this.keywords = keywords;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setRating(Rating rating) {
            this.rating = rating;
            return this;
        }

        public Book build() {
            //TODO: add validation

            return new Book(this);
        }
    }

    @Override
    public String toString() {
        return "========== BOOK INFORMATION ==========\n" +
                "id: " + id + "\n" +
                "title: " + title + "\n" +
                "author: " + author + "\n" +
                "description: " + description + "\n" +
                "isbn: " + isbn + "\n" +
                "genre: " + genre + "\n" +
                "keywords: " + String.join(", ", keywords) + "\n" +
                "year published: " + publishingYear + "\n" +
                "rating: " + rating + "\n" +
                "======================================";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book book)) return false;

        return id == book.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @XmlElement
    public int getId() {
        return id;
    }

    @XmlElement
    public String getIsbn() {
        return isbn;
    }

    @XmlElement
    public String getTitle() {
        return title;
    }

    @XmlElement
    public Author getAuthor() {
        return author;
    }

    @XmlElement
    public String getGenre() {
        return genre;
    }

    @XmlElement
    public int getPublishingYear() {
        return publishingYear;
    }

    @XmlElementWrapper(name = "keywords")
    @XmlElement(name = "keyword")
    public String[] getKeywords() {
        return keywords;
    }

    @XmlElement
    public String getDescription() {
        return description;
    }

    @XmlElement
    public Rating getRating() {
        return rating;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setPublishingYear(int publishingYear) {
        this.publishingYear = publishingYear;
    }

    public void setKeywords(String[] keywords) {
        this.keywords = keywords;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }
}
