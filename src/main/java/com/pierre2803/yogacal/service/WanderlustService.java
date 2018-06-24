package com.pierre2803.yogacal.service;

import com.pierre2803.yogacal.domain.YogaClass;
import com.pierre2803.yogacal.domain.YogaClasses;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class WanderlustService implements StudioService{
    private static final Logger LOGGER = LoggerFactory.getLogger(WanderlustService.class);

    @Value("${parser.wanderlust.baseUrl}")
    String baseUrl;

    YogaClasses yogaClasses;

    @Override
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
        }
    }

    @Override
    public YogaClasses parseSchedule(Document doc) {
        LOGGER.info("Loading schedule from " + doc.title());
        Elements tableLines = doc.body().select("tr.DropIn");
        yogaClasses = new YogaClasses();
        for(Element element : tableLines){
            YogaClass yogaClass = new YogaClass(getDateTime(element.selectFirst("span.hc_starttime")),
                    getDateTime(element.selectFirst("span.hc_endtime")),
                    element.selectFirst("span.classname").text(),
                    getTrainerName(element.selectFirst("span.trainer").text()));
            yogaClasses.add(yogaClass);
            LOGGER.info("Found Class " + yogaClass);
        }
        return yogaClasses;
    }

    private String getTrainerName(String trainer) {
        if(trainer.contains("("))
            return trainer.substring(0,trainer.indexOf("(")).trim();
        else
            return trainer.trim();
    }

    protected LocalDateTime getDateTime(Element e) {
        String dateStr = e.attributes().get("data-datetime").replace("\"", "");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX", Locale.ENGLISH);
        return LocalDateTime.parse(dateStr, formatter);
    }

    private String getUrl() {
        return this.baseUrl + getDate("yyyy-MM-dd");
    }

    private String getDate(String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        Date date = new Date();
        return dateFormat.format(date);
    }
}
