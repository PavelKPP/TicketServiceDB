package com.ticketservice.dao.springresource;

import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadFromFileWithSpringResource {
    @Value("${file}")
    private static String filePath;

    public void saveDataFromFileToAnArrayList() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filePath));
        ArrayList<String> list = new ArrayList<>();
        while(scanner.hasNextLine()) {
            list.add(scanner.nextLine());
        }
        scanner.close();
    }
}
