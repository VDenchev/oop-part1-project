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

    public Book() {
    }

    public static class Builder {
        private int id ;
        private String isbn;
        private String title;
        private Author author;
        private String genre = "none";
        private int publishingYear;
        private String[] keywords = {"none"};
        private String description = "No description.";
        private Rating rating = Rating.ZERO_STARS;

        public Builder(int id, String isbn, String title, Author author, int publishingYear) {
            this.id = id;
            this.isbn = isbn;
            this.title = title;
            this.author = author;
            this.publishingYear = publishingYear;
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

    //@XmlElementWrapper(name = "keywords")@XmlElement(name = "keyword")
    @XmlList
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

}
