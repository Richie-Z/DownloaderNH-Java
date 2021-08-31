/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.richie.nhdownloder.scraper;

import com.richie.nhdownloder.url.CodeValidator;
import com.richie.nhdownloder.url.FetchHTML;
import java.io.IOException;
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
    public Scraper(Document d) {
        this.doc = d;
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

    public static void main(String[] args) throws IOException {
        FetchHTML fh = new FetchHTML(new CodeValidator("371274").toString());
        Scraper sc = new Scraper(fh.main());
        sc.getTitles();
    }

}
