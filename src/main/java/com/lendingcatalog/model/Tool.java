package com.lendingcatalog.model;

import com.lendingcatalog.util.FileStorageService;
import com.lendingcatalog.util.exception.FileStorageException;

import java.time.LocalDate;
import java.util.UUID;

public class Tool implements CatalogItem{

    private String id;
    private String type;
    private String manufacturer;
    int count;


    public Tool(String type, String manufacturer, int count) {
        this.type = type;
        this.manufacturer = manufacturer;
        this.count = count;
    }

    public Tool(String type, String manufacturer, String count) {
        this(type, manufacturer, Integer.parseInt(count));
    }

    @Override
    public boolean matchesName(String searchStr) {
        return this.type.toLowerCase().contains(searchStr.toLowerCase());
    }

    @Override
    public boolean matchesCreator(String searchStr) {
        return this.manufacturer.toLowerCase().contains(searchStr.toLowerCase());
    }

    @Override
    public boolean matchesYear(int searchYear) {
        return false;
    }

    @Override
    public void registerItem() {
        this.id = UUID.randomUUID().toString();
        try {
            FileStorageService.writeContentsToFile(LocalDate.now().toString() + System.lineSeparator() + toString() + System.lineSeparator(), "src/main/resources/logs/tool.txt", true);
        } catch (FileStorageException e) {
            e.getMessage();
        }
    }

    public String toString() {
        return "* TOOL" + System.lineSeparator() + type + System.lineSeparator()
                + " - Manufactured By: " + manufacturer + System.lineSeparator()
                + " - Owns: " + count + System.lineSeparator()
                + " - ID: " + id;
    }
}
