package com.pierre2803.yogacal.domain;

import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Date;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.CalScale;
import net.fortuna.ical4j.model.property.ProdId;
import net.fortuna.ical4j.model.property.Version;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class YogaCalendar {

    private List<YogaClasses> yogaClassesList;
    Calendar calendar = new Calendar();

    public YogaCalendar(){
        clearCalendar();
    }

    public void addYogaClasses(YogaClasses yogaClasses){
        this.yogaClassesList.add(yogaClasses);

        for(int i=0; i<yogaClasses.size(); i++){
            this.addEvent(yogaClasses.get(i).get());
        }
    }

    private void addEvent(YogaClass yogaClass) {
        //...
    }

    public void clearCalendar(){
        this.yogaClassesList = new ArrayList<YogaClasses>();
        this.calendar = new Calendar();
        this.calendar.getProperties().add(new ProdId("-//Yoga Calendar//iCal4j 1.0//EN"));
        this.calendar.getProperties().add(Version.VERSION_2_0);
        this.calendar.getProperties().add(CalScale.GREGORIAN);
    }

}
