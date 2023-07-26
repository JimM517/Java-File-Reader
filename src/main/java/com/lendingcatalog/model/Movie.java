package com.lendingcatalog.model;

import com.lendingcatalog.util.FileStorageService;
import com.lendingcatalog.util.exception.FileStorageException;

import java.time.LocalDate;
import java.util.UUID;

public class Movie implements CatalogItem {

    private String id;
    private String name;
    private String director;
    private LocalDate releaseDate;


    public Movie(String name, String director, LocalDate releaseDate) {
        this.name = name;
        this.director = director;
        this.releaseDate = releaseDate;
    }

    public Movie(String name, String director, String releaseDate) {
        this(name, director, LocalDate.parse(releaseDate));
    }

    @Override
    public boolean matchesName(String searchStr) {
        return this.name.toLowerCase().contains(searchStr.toLowerCase());
    }

    @Override
    public boolean matchesCreator(String searchStr) {
        return this.director.toLowerCase().contains(searchStr.toLowerCase());
    }

    @Override
    public boolean matchesYear(int searchYear) {
        return releaseDate.getYear() == searchYear;
    }

    @Override
    public void registerItem() {
        this.id = UUID.randomUUID().toString();
        try {
            FileStorageService.writeContentsToFile(LocalDate.now().toString() + System.lineSeparator() + toString() + System.lineSeparator(), "src/main/logs/movie.txt", true);
        } catch (FileStorageException e) {
            e.getMessage();
        }
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String toString() {
        return "* MOVIE" + System.lineSeparator() + name + System.lineSeparator()
                + " - Directed By: " + director + System.lineSeparator()
                + " - Released On: " + releaseDate + System.lineSeparator()
                + " - ID: " + id;
    }
}
