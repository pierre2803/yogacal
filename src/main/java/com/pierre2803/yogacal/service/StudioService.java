package com.pierre2803.yogacal.service;

import org.jsoup.nodes.Document;

import java.text.ParseException;

public interface StudioService {

    void loadSchedule ();

    void parseSchedule(Document doc) throws ParseException;
}
