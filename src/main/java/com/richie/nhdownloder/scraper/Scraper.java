package com.richie.nhdownloder.scraper;

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
    protected String code = "", ext = "";
    private final String GALLERY_URL = "https://i.nhentai.net/galleries/%s/%d.jpg";

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

    private String tagInformation(String s) {
        Document dc = Jsoup.parseBodyFragment(s);
        Elements name = dc.getElementsByClass("name");
        return name.html();
    }

    public String[] getPicturesURL() throws IOException {
        if (this.tags.isEmpty()) {
            this.getTag();
        }
        String imgSrc = this.getPictureURL(String.format("%s/%d", this.code, 1)),
                idImages = this.getIdPicture(imgSrc);
        this.ext = this.getExtPicture(imgSrc);
        int pages = Integer.parseInt(this.tags.get("Pages").toString());
        String[] url = new String[pages];
        for (int i = 0; i < pages; i++) {
            String uri = String.format(GALLERY_URL, idImages, (i + 1));
            url[i] = uri;
        }
        return url;
    }

    protected String getPictureURL(String html) throws IOException {
        if (html == null) {
            return null;
        }
        FetchHTML fh = new FetchHTML(html);
        Document dc = fh.main();
        Element img = dc.getElementById("image-container");
        return img.child(0).child(0).absUrl("src");
    }

    private String getIdPicture(String img) {
        String[] arrayString = img.split("[, ?/]+");
        return arrayString[3];
    }

    private String getExtPicture(String img) {
        String arr = img.replaceAll(".*\\.", "");
        return arr;
    }
}
