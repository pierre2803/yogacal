package com.pierre2803.yogacal.service;

import org.jsoup.nodes.Document;

public interface StudioService {

    void loadSchedule ();

    void parseSchedule(Document doc);
}
