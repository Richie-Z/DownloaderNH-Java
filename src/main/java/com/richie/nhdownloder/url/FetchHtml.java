/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.richie.nhdownloder.url;

import java.net.URI;
import java.net.URISyntaxException;

/**
 *
 * @author Richie-PC
 */
public class FetchHtml {

    private static final String BASE_URL = "https://nhentai.net";
    private static String targetUrl = null;

    public FetchHtml(String targetUrl) {
        this.targetUrl = generateUrl(targetUrl);
    }

    private static boolean isValidURL(String url) {
        try {
            new URI(url).parseServerAuthority();
            return true;
        } catch (URISyntaxException e) {
            return false;
        }
    }

    private static String generateUrl(String givenString) {
        System.out.println(isValidURL(givenString));
        if (isValidURL(givenString)) {
            return givenString;
        }
        String onlyCode = checkGivenCode(givenString);
        return onlyCode;

    }

    private static String checkGivenCode(String targetUrl) {
        String onlyCode = null;
        if (targetUrl.substring(0, 1).equals("#")) {
            onlyCode = targetUrl.substring(1);
        }
        return onlyCode;
    }

    public static void main(String[] args) {
        generateUrl("fgdfgfdg");
    }

}
