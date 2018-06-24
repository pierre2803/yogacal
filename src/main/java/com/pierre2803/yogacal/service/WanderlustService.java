package com.pierre2803.yogacal.service;

import com.pierre2803.yogacal.parser.PageParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WanderlustService {
    private static final Logger LOGGER = LoggerFactory.getLogger(WanderlustService.class);

    @Value("${parser.wanderlust.baseUrl}")
    String baseUrl;

    @Scheduled(cron = "${parser.wanderlust.cron}")
    public void loadSchedule (){
        String url = getUrl();
        LOGGER.info("Loading " + url);

        try {
            Document doc = Jsoup.connect(url).get();
            parseSchedule(doc);
        } catch (IOException e) {
            LOGGER.info("Error while loading " + url);
            e.printStackTrace();
        } catch (ParseException e) {
            LOGGER.info("Error while parsing " + url);
            e.printStackTrace();
        }
    }

    private void parseSchedule(Document doc) throws ParseException {
        /*Elements newsHeadlines = doc.select("#mp-itn b a");
            for (Element headline : newsHeadlines) {
                log("%s\n\t%s",
                        headline.attr("title"), headline.absUrl("href"));
            }*/

        //Document doc = Jsoup.parse(hmtlresponse);
        String title = doc.title();
        LOGGER.info("Loading schedule from " + title);

        Elements tableLines = doc.body().select("table").select("tr");
        for(Element element : tableLines){
            if(element.attributes().get("class").equals("schedule_header")){
                String date = element.selectFirst("span.hc_date").text();
                LOGGER.info("Parsing schedule for date = " + date);
            } else if (element.attributes().get("class").contains("DropIn")) {
                String startTime = element.selectFirst("span.hc_starttime").text();
                DateFormat formatter = new SimpleDateFormat("hh:mm a");
                Time t = new Time(formatter.parse(startTime).getTime());

                LOGGER.info("StartTime = " + startTime);
                LOGGER.info("StartTime = " + t);
                String endTime = element.selectFirst("span.hc_endtime").text();
                LOGGER.info("EndTime = " + endTime);
                String className = element.selectFirst("span.classname").text();
                LOGGER.info("className = " + className);
                String trainer = element.selectFirst("span.trainer").text();
                LOGGER.info("trainer = " + trainer);
            }
        }


    }


    private String getUrl() {
        return this.baseUrl + getDate("yyyy-MM-dd");
    }

    public String getDate(String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        Date date = new Date();
        return dateFormat.format(date);
    }
}
