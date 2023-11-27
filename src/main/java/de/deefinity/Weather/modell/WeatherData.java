package de.deefinity.Weather.modell;

import java.text.SimpleDateFormat;
import java.util.*;
import java.text.ParseException;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherData {
    private Main main;
    private List<Weather> weather;
    private Wind wind;
    private Double pop;
    private String dt_txt;


    public Main getMain() {
        return main;
    }
    public void setMain(Main main) {
        this.main = main;
    }
    public List<Weather> getWeather() {
        return weather;
    }
    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }
    public Wind getWind() {
        return wind;
    }
    public void setWind(Wind wind) {
        this.wind = wind;
    }
    public Double getPop() {
        return pop;
    }
    public void setPop(Double pop) {
        this.pop = pop;
    }
    public String getDt_txt() {
        return dt_txt;
    }
    public void setDt_txt(String dt_txt) {
        this.dt_txt = dt_txt;
    }

    /**
     * This method is used to get the date from the dt_txt
     * @return date in format yyyy-MM-dd
     */
    public String getDt() {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = inputFormat.parse(dt_txt);
            return outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

}