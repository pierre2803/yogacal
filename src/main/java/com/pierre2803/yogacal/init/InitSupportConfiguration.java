package com.pierre2803.yogacal.init;

import com.pierre2803.yogacal.domain.YogaClasses;
import com.pierre2803.yogacal.service.WanderlustService;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableConfigurationProperties
public class InitSupportConfiguration {

    @Bean
    public WanderlustService wanderlustService() {
        return new WanderlustService();
    }

    @Bean
    public YogaClasses yogaClasses() {
        return new YogaClasses();
    }

    @Scheduled(cron = "${parser.yogacal.cron}")
    public void loadSchedule() {
        wanderlustService().loadSchedule();
    }
}
