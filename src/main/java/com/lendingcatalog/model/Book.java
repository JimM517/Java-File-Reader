package com.lendingcatalog.model;

import com.lendingcatalog.util.FileStorageService;
import com.lendingcatalog.util.exception.FileStorageException;

import java.time.LocalDate;
import java.util.UUID;

public class Book implements CatalogItem {

    private String id;
    private String title;
    private String author;
    private LocalDate publishDate;


    public Book(String title, String author, LocalDate publishDate) {
        this.title = title;
        this.author = author;
        this.publishDate = publishDate;
    }

    public Book(String title, String author, String publishDate) {
        this(title, author, LocalDate.parse(publishDate));
    }

    @Override
    public boolean matchesName(String searchStr) {
        return this.title.equalsIgnoreCase(searchStr);
    }

    @Override
    public boolean matchesCreator(String searchStr) {
        return this.author.equalsIgnoreCase(searchStr);
    }

    @Override
    public boolean matchesYear(int searchYear) {
        return publishDate.getYear() == searchYear;
    }

    @Override
    public void registerItem() {
        this.id = UUID.randomUUID().toString();
        try {
            FileStorageService.writeContentsToFile(LocalDate.now().toString() + System.lineSeparator() + toString() + System.lineSeparator(), "src/main/resources/logs/book.txt", true);
        } catch (FileStorageException e) {
            e.getMessage();
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }

    @Override
    public String toString() {
        return "* BOOK" + System.lineSeparator() + title + System.lineSeparator()
                + " -written by: " + author + System.lineSeparator()
                + " -Published: " + publishDate + System.lineSeparator()
                + " -Id: " + id;
    }
}
