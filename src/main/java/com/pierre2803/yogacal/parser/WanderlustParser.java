package com.pierre2803.yogacal.parser;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WanderlustParser implements PageParser {

    private String url;

    @Override
    public void loadPage() {
        System.out.println(url);
        try {

            Document doc = Jsoup.connect(url).get();
            /*Elements newsHeadlines = doc.select("#mp-itn b a");
            for (Element headline : newsHeadlines) {
                log("%s\n\t%s",
                        headline.attr("title"), headline.absUrl("href"));
            }*/

            //Document doc = Jsoup.parse(hmtlresponse);
            String title = doc.title();
            String body = doc.body().text();

            doc.getElementsByTag("table").get(0).getElementsByTag("TR");


            System.out.printf("Title: %s%n", title);
            System.out.printf("Body: %s", body);


        } catch (IOException e) {
            System.out.println("ERROR");
            e.printStackTrace();
        }

    }

    @Override
    public void processPage() {

    }

    public static String getDate(String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        Date date = new Date();
        return dateFormat.format(date);
    }
}
