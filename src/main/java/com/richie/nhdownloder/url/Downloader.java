/*
 * Richie Zakaria
 */
package com.richie.nhdownloder.url;

import com.richie.nhdownloder.scraper.Scraper;
import java.io.FileOutputStream;
import java.io.IOException;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 *
 * @author Richie-PC
 */
public class Downloader extends Scraper {

    private final String IMAGE_HOME = "C://User/Richie-PC/Pictures/test/";

    public Downloader(Document d, String c) {
        super(d, c);
    }
    public void startDownload() throws IOException{
        String[] url = super.getPicturesURL();
        for (int i = 0; i < url.length; i++) {
            System.out.printf("%d/%d %n",i,url.length);
        }
    }
    public void downloadImage(String url, String fileName, String relativePath) throws IOException {
        Response resultImageResponse = Jsoup.connect(url)
                .data("query", "Java")
                .userAgent("Mozilla")
                .cookie("auth", "token")
                .ignoreContentType(true).execute();
        FileOutputStream out = (new FileOutputStream(new java.io.File(IMAGE_HOME + fileName)));
        out.write(resultImageResponse.bodyAsBytes());  // resultImageResponse.body() is where the image's contents are.
        out.close();
    }

    public static void main(String[] args) throws IOException {
        FetchHTML fh = new FetchHTML(new CodeValidator("371274").toString());
        Downloader dwnl = new Downloader(fh.main(), "371274");
        dwnl.startDownload();
    }
}
