package com.pierre2803.yogacal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAsync
@EnableScheduling
public class YogacalApplication {

	public static void main(String[] args) {
		SpringApplication.run(YogacalApplication.class, args);

        //getPageHTMLUnit("https://clients.mindbodyonline.com/classic/mainclass?studioid=7425&tg=&vt=&lvl=&stype=&view=&trn=0&page=&catid=&prodid=&date=4%2f14%2f2018&classid=0&prodGroupId=&sSU=&optForwardingLink=&qParam=&justloggedin=&nLgIn=&pMode=0&loc=1");

        //System.out.println(getDate("yyyy-MM-dd"));

        //getPageHTMLUnit("https://widgets.healcode.com/widgets/schedules/3345098fcf5/print?options%5Bstart_date%5D="+getDate("yyyy-MM-dd"),"wanderlust.html");
	}
}
