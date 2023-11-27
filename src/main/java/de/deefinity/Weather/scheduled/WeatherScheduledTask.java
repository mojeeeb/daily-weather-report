package de.deefinity.Weather.scheduled;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import de.deefinity.Weather.service.EmailService;
import de.deefinity.Weather.service.WeatherService;

@Component
public class WeatherScheduledTask {

    @Autowired
    private WeatherService weatherService;

    @Autowired
    private EmailService emailService;


    /**
     * This method is used to send a daily weather forecast email in the time which is specified in the cron expression
     * corresponding to the cron expression the email is sent every day at 19:56
     * "0 56 19 * * ?" = "Sekunde Minute Stunde Tag Monat Wochentag"
     */
    @Scheduled(cron = "0 56 19 * * ?")
    public void sendDailyWeatherEmail() {
        try {
            String weatherDataHtml = weatherService.getWeatherForecastAsHtml("Berlin");
            emailService.sendEmail("Reseriver email", "Täglicher Wetterbericht", weatherDataHtml);
            System.out.println("Täglicher Wetterbericht wurde versendet.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}