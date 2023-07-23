package com.lendingcatalog.util;

import com.lendingcatalog.util.exception.FileStorageException;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileStorageService {

    // Requirement: File I/O
    public static void writeContentsToFile(String contents, String filename, boolean appendFile) throws FileStorageException {
        //open file output and write to file
        try (FileOutputStream fileOutputStream = new FileOutputStream(filename, appendFile);
            PrintWriter writer = new PrintWriter(fileOutputStream)) {
                writer.println(contents);
        } catch (FileNotFoundException e) {
            throw new FileStorageException("File " + filename + " was not found.");
        } catch (Exception e) {
            throw new FileStorageException("Unable to write to file");
        }
    }

    public static List<String> readContentsOfFile(String filename) throws FileStorageException {
        List<String> results = new ArrayList<>();
        //Try to scan the file first
        try (Scanner scanner = new Scanner(filename)) {
            while(scanner.hasNextLine()) {
                // add each line to the list
                results.add(scanner.nextLine());
            }
            //Throw file storage exception
        } catch (Exception e) {
            throw new FileStorageException("File " + filename + " was not found");
        }
        return results;
    }

}
