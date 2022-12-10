/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.julian.mora.csv2folder;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author macbook
 */
public class CSVUtils {

    public static ArrayList<ArrayList<String>> GetCSVContent(String csvPath, String cvsSplitBy) throws FileNotFoundException, IOException {
        ArrayList<ArrayList<String>> rows = new ArrayList();
        BufferedReader br = new BufferedReader(new FileReader(csvPath));
        String line;
        while ((line = br.readLine()) != null) {
            // use comma as separator
            String[] cols = line.split(cvsSplitBy);
            if (cols.length > 1) {
                ArrayList<String> columns = new ArrayList();
                for (int i = 0; i < cols.length; i++) {
                    String valueColumn = cols[i];

                    if (i < columns.size()) {
                        columns.set(i, valueColumn);
                    } else {
                        columns.add(i, valueColumn);
                    }
                }
                rows.add(columns);
            }
        }

        return rows;
    }
}
