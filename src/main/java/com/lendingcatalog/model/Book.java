package com.lendingcatalog.model;

import java.time.LocalDate;

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

    //TODO create register item method with UUID
    @Override
    public void registerItem() {

    }

    @Override
    public String toString() {
        return " BOOK" + System.lineSeparator() + title + System.lineSeparator()
                + " -written by: " + author + System.lineSeparator()
                + " -Published: " + publishDate + System.lineSeparator()
                + " -Id: " + id;
    }
}
