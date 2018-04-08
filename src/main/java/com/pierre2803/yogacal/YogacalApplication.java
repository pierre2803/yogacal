package com.pierre2803.yogacal;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import org.apache.commons.io.FileUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
public class YogacalApplication {

	public static void getPage(String urllink) {
        System.out.println(urllink);
		URL url;
		InputStream is = null;
		BufferedReader br;
		String line;

		try {
			url = new URL(urllink);
			is = url.openStream();  // throws an IOException
			br = new BufferedReader(new InputStreamReader(is));

			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
		} catch (MalformedURLException mue) {
			mue.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				if (is != null) is.close();
			} catch (IOException ioe) {
				// nothing to see here
			}
		}
	}

    public static void getPageHTMLUnit(String url, String filename) {
        System.out.println(url);
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        webClient.getOptions().setJavaScriptEnabled(false);
        try {
            Page page = webClient.getPage(url);
            System.out.println("SUCCESS");
            System.out.println(page.getWebResponse().getContentAsString());

            InputStream initialStream = page.getWebResponse().getContentAsStream();
            String hmtlresponse = page.getWebResponse().getContentAsString(Charset.defaultCharset());

            File targetFile = new File("src/main/resources/"+filename);
            FileUtils.writeStringToFile(targetFile, hmtlresponse, "UTF8");
            FileUtils.copyInputStreamToFile(initialStream, targetFile);

        } catch (IOException e) {
            System.out.println("ERROR");
            e.printStackTrace();
        }

    }

    public static String getDate(String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        Date date = new Date();
        return dateFormat.format(date);
    }

	public static void main(String[] args) {
		SpringApplication.run(YogacalApplication.class, args);

        //getPageHTMLUnit("https://clients.mindbodyonline.com/classic/mainclass?studioid=7425&tg=&vt=&lvl=&stype=&view=&trn=0&page=&catid=&prodid=&date=4%2f14%2f2018&classid=0&prodGroupId=&sSU=&optForwardingLink=&qParam=&justloggedin=&nLgIn=&pMode=0&loc=1");

        System.out.println(getDate("yyyy-MM-dd"));

        getPageHTMLUnit("https://widgets.healcode.com/widgets/schedules/3345098fcf5/print?options%5Bstart_date%5D="+getDate("yyyy-MM-dd"),"wanderlust.html");
	}
}
