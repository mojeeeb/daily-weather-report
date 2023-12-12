package de.deefinity.Weather.scheduled;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import de.deefinity.Weather.service.EmailService;
import de.deefinity.Weather.service.ExcelService;
import de.deefinity.Weather.service.WeatherService;

@Component
public class WeatherScheduledTask {

    @Autowired
    private WeatherService weatherService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private ExcelService excelService;

    /**
     * This method is used to send a daily weather forecast email in the time which is specified in the cron expression
     * corresponding to the cron expression the email is sent every day at 19:56
     * "0 56 19 * * ?" = "Sekunde Minute Stunde Tag Monat Wochentag"
     */
    @Scheduled(cron = "00 56 19 * * ?")
    public void sendDailyWeatherEmail() {
        try{
            String weatherDataHtml = weatherService.getWeatherForecastAsHtml("Berlin");
            List<String> emailAddresses = excelService.readEmailsFromExcel("/home/pc/Documents/daily-weather-report/excel.xlsx");
            for (String email : emailAddresses) {
            if (excelService.isValidEmail(email)) {
                emailService.sendEmail(email, "Täglicher Wetterbericht", weatherDataHtml);
                System.out.println("Täglicher Wetterbericht wurde zu "+ email +" versendet.");
            } else {
                System.out.println("Invalid email address: " + email);
            }
        }
        
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}