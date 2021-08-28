/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.richie.nhdownloder;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Richie-PC
 */
public class Main {

    public static void main(String[] args) {
        Document docs = null;
        String url = "https://nhentai.net/g/357326/";
        try {
            Document doc = Jsoup.connect(url)
                    .data("query", "Java")
                    .userAgent("Mozilla")
                    .cookie("auth", "token")
                    .get();
            docs = doc;
        } catch (IOException ex) {
            System.out.println("Error Connection");
            System.out.println(ex);
        }
        Element info = docs.getElementById("info");
        Elements titles = info.getElementsByClass("title");
        for (Element title : titles) {
            System.out.printf("%s ", title.text());
        }
        System.out.println();
        System.out.println(docs.getElementById("gallery_id").text());
        Elements tags = docs.select("section#tags");
        System.out.println(tags);
    }
}
