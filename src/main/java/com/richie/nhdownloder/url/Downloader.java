package com.richie.nhdownloder.url;

import com.richie.nhdownloder.scraper.Scraper;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 *
 * @author Richie-PC
 */
public class Downloader extends Scraper {

    private final String HOME = System.getProperty("user.home");
    private final String DOWNLOAD_HOME = HOME + "/Downloads/" + "nh_downloader/";

    public Downloader(Document d, String c) {
        super(d, c);
    }

    public void startDownload() throws IOException {
        String[] url = super.getPicturesURL();
        for (int i = 0; i <= url.length; i++) {
            this.downloadImage(url[i], "a");
        }
    }

    public void downloadImage(String url, String fileName) throws IOException {
        Response resultImageResponse = Jsoup.connect(url)
                .data("query", "Java")
                .userAgent("Mozilla")
                .cookie("auth", "token")
                .ignoreContentType(true).execute();
        this.saveFile(resultImageResponse.bodyAsBytes(), fileName);
    }

    public void saveFile(byte[] b, String fileName) {
        String dir = DOWNLOAD_HOME.concat("/" + super.code);
        File nhDir = new File(dir);
        if (!nhDir.exists()) {
            boolean created = nhDir.mkdirs();
            if (!created) {
                System.out.println("Error while creating directory for location- " + nhDir);
            }
        }
        try {
            FileOutputStream out = (new FileOutputStream(new File(dir.concat("/" + fileName))));
            out.write(b);
            out.close();
            System.out.println("Success save");
        } catch (IOException ex) {
            System.out.println("Error while saving image-" + ex);
        }

    }

    public static void main(String[] args) throws IOException {
        FetchHTML fh = new FetchHTML(new CodeValidator("371274").toString());
        Downloader dwnl = new Downloader(fh.main(), "371274");
        dwnl.downloadImage("https://i.nhentai.net/galleries/1997899/1.jpg", "test.jpg");
    }
}
