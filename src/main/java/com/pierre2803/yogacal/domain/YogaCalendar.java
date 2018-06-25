package com.pierre2803.yogacal.domain;

import net.fortuna.ical4j.data.CalendarOutputter;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.CalScale;
import net.fortuna.ical4j.model.property.ProdId;
import net.fortuna.ical4j.model.property.Version;
import net.fortuna.ical4j.util.FixedUidGenerator;
import net.fortuna.ical4j.util.UidGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;
import java.net.SocketException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class YogaCalendar {

    private static final Logger LOGGER = LoggerFactory.getLogger(YogaCalendar.class);

    private List<YogaClasses> yogaClassesList;
    Calendar calendar = new Calendar();
    private UidGenerator uidGenerator;

    public YogaCalendar(){
        clearCalendar();
        instantiateUidGenerator();
    }

    public void clearCalendar(){
        this.yogaClassesList = new ArrayList<YogaClasses>();
        this.calendar = new Calendar();
        this.calendar.getProperties().add(new ProdId("-//Yoga Calendar//iCal4j 2.2.0//EN"));
        this.calendar.getProperties().add(Version.VERSION_2_0);
        this.calendar.getProperties().add(CalScale.GREGORIAN);
    }

    private void instantiateUidGenerator() {
        try {
            uidGenerator = new FixedUidGenerator("uidGen");
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public void addYogaClasses(YogaClasses yogaClasses){
        this.yogaClassesList.add(yogaClasses);

        for(int i=0; i<yogaClasses.size(); i++){
            this.addEvent(yogaClasses.get(i).get());
        }
    }

    public void writeToFile(String filename) {
        FileOutputStream fout;
        try {
            fout = new FileOutputStream(filename);
            new CalendarOutputter().output(this.calendar, fout);
        } catch (Exception e) {
            this.LOGGER.error("Error while writing calendar " + e.toString());
            e.printStackTrace();
        }
    }

    private void addEvent(YogaClass yogaClass) {

        LocalDateTime start = yogaClass.getStartTime();
        java.util.Calendar startCal = java.util.Calendar.getInstance();
        startCal.set(java.util.Calendar.YEAR, start.getYear());
        startCal.set(java.util.Calendar.MONTH, start.getMonth().getValue()-1);
        startCal.set(java.util.Calendar.DAY_OF_MONTH, start.getDayOfMonth());
        startCal.set(java.util.Calendar.HOUR_OF_DAY, start.getHour());
        startCal.set(java.util.Calendar.MINUTE, start.getMinute());
        startCal.clear(java.util.Calendar.SECOND);

        LocalDateTime end = yogaClass.getEndTime();
        java.util.Calendar endCal = java.util.Calendar.getInstance();
        endCal.set(java.util.Calendar.YEAR, end.getYear());
        endCal.set(java.util.Calendar.MONTH, end.getMonth().getValue()-1);
        endCal.set(java.util.Calendar.DAY_OF_MONTH, end.getDayOfMonth());
        endCal.set(java.util.Calendar.HOUR_OF_DAY, end.getHour());
        endCal.set(java.util.Calendar.MINUTE, end.getMinute());
        endCal.clear(java.util.Calendar.SECOND);

        net.fortuna.ical4j.model.DateTime dtStart = new DateTime(startCal.getTime());
        dtStart.setUtc(true);

        net.fortuna.ical4j.model.DateTime dtEnd = new DateTime (endCal.getTime());
        dtEnd.setUtc(true);

        VEvent event = new VEvent(dtStart,dtEnd, yogaClass.getClassType()+" ("+yogaClass.getInstructor()+")");

        // generate UID
        event.getProperties().add(uidGenerator.generateUid());

        this.calendar.getComponents().add(event);
    }

}
