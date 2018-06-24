package com.pierre2803.yogacal.init;

import com.pierre2803.yogacal.service.WanderlustService;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.util.Date;

@Configuration
@EnableConfigurationProperties
public class InitSupportConfiguration {

    @Bean
    public WanderlustService wanderlustService() {
        return new WanderlustService();
    }

    //@Scheduled(cron = "${parser.wanderlust.cron}")
    @Scheduled(fixedRate = 5000)
    public void loadSchedule() {
        wanderlustService().loadSchedule();
    }
}
