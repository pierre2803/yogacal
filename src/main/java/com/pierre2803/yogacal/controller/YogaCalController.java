package com.pierre2803.yogacal.controller;

import com.pierre2803.yogacal.service.WanderlustService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class YogaCalController {

    @Autowired
    WanderlustService wanderlustService;

    @GetMapping("/gencal")
    public void generateCalendar() {
        wanderlustService.loadSchedule();
    }

}