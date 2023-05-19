package commands.implementations.books;

import models.book.Book;

import java.util.ArrayList;
import java.util.List;

public class BooksTableFormatter {
    private List<String> headerNames;
    private List<Book> books;
    private int itemsPerPage;
    private String rowFormat;
    private String border;

    public BooksTableFormatter(List<String> headerNames, List<Book> books, int itemsPerPage) {
        this.headerNames = headerNames;
        this.books = books;
        this.itemsPerPage = itemsPerPage;
        this.rowFormat = calculateRowFormat(2);
        this.border = calculateBorder(2);
    }

    public String getPage(int pageNumber) {
        int start = (pageNumber - 1) * itemsPerPage;
        int end = Math.min(pageNumber * itemsPerPage, books.size());
        List<Book> booksOnPage = books.subList(start, end);

        String headers =  String.format(rowFormat, headerNames.toArray());
        StringBuilder sb = new StringBuilder(border).append(headers).append(border);

        for (Book book : booksOnPage) {
            sb.append(String.format(rowFormat, book.getTitle(), book.getAuthor(), book.getGenre(), book.getIsbn()));
        }
        sb.append(border).append("\n");

        String centerFormat = "%" + border.length() / 2 + "s";
        sb.append(String.format(centerFormat, "PAGE " + pageNumber + " / " + getPagesCount()));

        return sb.toString();
    }
    private String calculateRowFormat(int padding) {
        List<Integer> columnWidths = calculateColumnWidths(books, padding);

        StringBuilder sb = new StringBuilder();
        for (Integer columnWidth : columnWidths) {
            sb.append("| %-").append(columnWidth - 1).append("s");
        }
        return sb.append("|%n").toString();
    }

    private String calculateBorder(int padding) {
        List<Integer> columnWidths = calculateColumnWidths(books, padding);

        StringBuilder sb = new StringBuilder();
        for (Integer columnWith : columnWidths) {
            sb.append("#").append(fillString(columnWith, '='));
        }
        return sb.append("#\n").toString();
    }

    private List<Integer> calculateColumnWidths(List<Book> books, int padding) {
        List<Integer> columnWidths = new ArrayList<>();
        for (String header : headerNames) {
            int maxWidth = header.length();

            for (Book book : books) {
                int width = switch (header) {
                    case "Title" -> book.getTitle().length();
                    case "Author" -> book.getAuthor().getFullName().length();
                    case "Genre" -> book.getGenre().length();
                    case "ISBN" -> book.getIsbn().length();
                    default -> 0;
                };
                maxWidth = Math.max(maxWidth, width);
            }
            columnWidths.add(maxWidth + padding);
        }
        return columnWidths;
    }

    private String fillString(int length, char character) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(character);
        }
        return sb.toString();
    }

    public int getPagesCount() {
        return (int)Math.ceil((double)books.size() / itemsPerPage);
    }
}
