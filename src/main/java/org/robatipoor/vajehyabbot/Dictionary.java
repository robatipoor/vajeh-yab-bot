package org.robatipoor.vajehyabbot;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Dictionary
 */
public class Dictionary {

    public static String search(String word) throws IOException {
        String url = "https://www.vajehyab.com/?q=" + word;
        Document doc = Jsoup.connect(url).userAgent("Mozilla").get();
        Elements secs = doc.select("div.sections").last().select("section");
        StringBuilder sb = new StringBuilder();
        for (Element sec : secs) {
            sb.append(sec.select("header").first().text());
            sb.append(" : ");
            sb.append(sec.select("p").first().text());
            sb.append("\n");
        }
        return sb.toString();
    }
}
