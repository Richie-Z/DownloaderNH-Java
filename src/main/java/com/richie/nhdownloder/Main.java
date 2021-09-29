package com.richie.nhdownloder;

import com.richie.nhdownloder.url.CodeValidator;
import com.richie.nhdownloder.url.Downloader;
import com.richie.nhdownloder.url.FetchHTML;
import java.io.IOException;

/**
 *
 * @author Richie-PC
 */
public class Main {

    public static void main(String[] args) throws IOException {
        String code = new CodeValidator("#374873").toString();
        FetchHTML fh = new FetchHTML(code);
        Downloader d = new Downloader(fh.main(), code);
        d.startDownload();
    }
}
