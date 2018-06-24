package com.pierre2803.yogacal.service;

import com.pierre2803.yogacal.domain.YogaClasses;
import org.jsoup.nodes.Document;

public interface StudioService {

    void loadSchedule ();

    YogaClasses parseSchedule(Document doc);
}
