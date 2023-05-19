package models.book;


import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Author {
    private String firstName;
    private String lastName;

    private Author(Builder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
    }

    protected Author(){}

    public static class Builder {
        private String firstName;
        private String lastName;

        public Builder(String firstName) {
            this.firstName = firstName;
            lastName = "";
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Author build() {
            return new Author(this);
        }
    }

    @XmlElement
    public String getFirstName() {
        return firstName;
    }

    @XmlElement
    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return firstName + (lastName.isBlank() ? "" : " " + lastName);
    }

    private void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    private void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
       return getFullName();
    }
}
