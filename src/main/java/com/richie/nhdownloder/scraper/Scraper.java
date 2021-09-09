/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.richie.nhdownloder.scraper;

import com.richie.nhdownloder.url.CodeValidator;
import com.richie.nhdownloder.url.FetchHTML;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Richie-PC
 */
public class Scraper {

    private Document doc;
    private StringBuilder title = new StringBuilder();
    private List<String> info = new ArrayList<String>();
    private HashMap<String, Object> tags = new HashMap<>();
    private String code = "";

    public Scraper(Document d, String c) {
        this.doc = d;
        this.code = c;
    }

    public String getTitles() {
        Element info = this.doc.getElementById("info");
        Elements titles = info.getElementsByClass("title");

        Elements docTitle = titles.get(0).children();
        for (Element title : docTitle) {
            this.title.append(title.html().concat(" "));
        }
        System.out.println(this.title.toString());
        return this.title.toString();
    }

    public void getTag() {
        Elements tags = this.doc.getElementById("info").getElementById("tags").getElementsByClass("tag-container");
        tags.forEach((Element i) -> {
            String[] splited = i.html().split(":");
            this.tags.put(splited[0], this.tagInformation(splited[1]));
        });
    }

    public String tagInformation(String s) {
        Document dc = Jsoup.parseBodyFragment(s);
        Elements name = dc.getElementsByClass("name");
        return name.html();
    }

    public String getPictureURL() {
        if (this.tags.isEmpty()) {
            this.getTag();
        }
        int pages = Integer.parseInt(this.tags.get("Pages").toString());
        for (int i = 1; i <= pages; i++) {
            System.out.printf("https://i.nhentai.net/galleries/%s/%d.jpg %n", this.code, i);
        }
        return null;
    }

    public static void main(String[] args) throws IOException {
        FetchHTML fh = new FetchHTML(new CodeValidator("371274").toString());
        Scraper sc = new Scraper(fh.main(), "371274");
        sc.getTitles();
        sc.getPictureURL();
    }

}
