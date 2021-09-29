package com.richie.nhdownloder.url;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;

/**
 *
 * @author Richie-PC
 */
public class FetchHTML {

    private String param;
    private final String BASE_URL = "https://nhentai.net/g/";
    private Response res;

    public FetchHTML(String param) throws IOException {
        this.param = param;
        this.checkParameter();
    }

    public void checkParameter() throws IOException {
        this.isValidURL(this.isUrl(this.param) ? this.param : BASE_URL + this.param);
    }

    public Boolean isUrl(String url) {
        try {
            new URL(url).toURI();
            return true;
        } catch (MalformedURLException | URISyntaxException e) {
            return false;
        }
    }

    public int isValidURL(String url) {
        try {
            this.res = Jsoup.connect(url)
                    .data("query", "Java")
                    .userAgent("Mozilla")
                    .cookie("auth", "token")
                    .execute();
        } catch (IOException ex) {
            Logger.getLogger(FetchHTML.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.res.statusCode();
    }

    public Document main() throws IOException {
        return this.res.parse();
    }

    public static void main(String[] args) throws IOException {
        FetchHTML fh = new FetchHTML("https://nhentai.net/g/1");
        System.out.println(fh.main());
    }
}
