package com.farukcuha;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String csvFile = "imdb_top_250.csv";
        List<Movie> dataList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            boolean isFirstLine = true;

            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                String[] data = line.split(";");

                Movie obj = new Movie(data[0], data[1], data[2], data[3], data[4], data[5], data[6], data[7]);

                dataList.add(obj);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String file = "poster_images.csv";
        int i = 1;
        List<String> posterLinks = new ArrayList<>();
        for (Movie obj : dataList) {
            String link = obj.imdb_link;
            try {
                Document document = Jsoup.connect(link).get();
                String posterImageUrl = document.head().select("meta[property=og:image]").attr("content");
                posterLinks.add(posterImageUrl);
                System.out.println(i + " " + posterImageUrl);
                i++;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println(posterLinks);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (String link : posterLinks) {
                writer.write(link + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}