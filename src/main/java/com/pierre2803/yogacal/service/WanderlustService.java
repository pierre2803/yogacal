package com.pierre2803.yogacal.service;

import com.pierre2803.yogacal.domain.YogaCalendar;
import com.pierre2803.yogacal.domain.YogaClass;
import com.pierre2803.yogacal.domain.YogaClasses;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

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
    private String baseUrl;

    @Value("${parser.wanderlust.icsfilename}")
    private String icsfilename;

    @Override
    public void loadSchedule (){
        String url = getUrl();
        LOGGER.info("Loading " + url);
        try {
            Document htmlDocument = Jsoup.connect(url).get();
            YogaClasses yogaClasses = parseSchedule(htmlDocument);
            writeCalendar(yogaClasses);

        } catch (IOException e) {
            LOGGER.info("Error while loading " + url);
            e.printStackTrace();
        }
    }

    @Override
    public YogaClasses parseSchedule(Document htmlDocument) {
        LOGGER.info("Loading schedule from " + htmlDocument.title());
        Elements tableLines = htmlDocument.body().select("tr.DropIn");
        YogaClasses yogaClasses = new YogaClasses();
        for(Element element : tableLines){
            YogaClass yogaClass = new YogaClass.Builder()
                    .withStartTime(getDateTime(element.selectFirst("span.hc_starttime")))
                    .withEndTime(getDateTime(element.selectFirst("span.hc_endtime")))
                    .withClassType(element.selectFirst("span.classname").text())
                    .withInstructor(getTrainerName(element.selectFirst("span.trainer").text()))
                    .build();
            LOGGER.info("Found Class " + yogaClass);
            yogaClasses.add(yogaClass);
        }
        return yogaClasses;
    }

    @Override
    public void writeCalendar(YogaClasses yogaClasses) {
        YogaCalendar cal = new YogaCalendar();
        cal.addYogaClasses(yogaClasses);
        cal.writeToFile(icsfilename);
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
