package de.deefinity.Weather.service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.deefinity.Weather.modell.WeatherData;
import de.deefinity.Weather.modell.WeatherForecast;
@Service
public class WeatherService {

    @Value("${openweathermap.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    @Autowired
    public WeatherService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    /**
     * This method is used to get the weather data from the api
     * @param city
     * @return
     * @throws JsonProcessingException
     */
    public WeatherForecast getWeatherForecast(String city) throws JsonProcessingException {
        String url = "http://api.openweathermap.org/data/2.5/forecast?q=" + city + "&appid=" + apiKey;
        String response = restTemplate.getForObject(url, String.class);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(response, WeatherForecast.class);
    }

    /**
     * This method is used to format the weather data from json to html with css
     * @param forecast
     * @return htmlContent
     */
    private String formatWeatherDataAsHtml(WeatherForecast forecast) {
        Map<String, List<WeatherData>> groupedData = new HashMap<>();
    
        // Gruppieren der Daten nach Datum
        for (WeatherData data : forecast.getList()) {
            String date = data.getDt();
            groupedData.computeIfAbsent(date, k -> new ArrayList<>()).add(data);
        }
    
        StringBuilder htmlContent = new StringBuilder();
        htmlContent.append("<html><head>")
                   .append("<style>")
                   .append("body { font-family: Arial, sans-serif; background-color: #e6f7ff; }")
                   .append("h1 { color: #005073; }")
                   .append("table { width: 100%; border-collapse: collapse; margin-top: 20px; }")
                   .append("th, td { border: 1px solid #b8daff; padding: 8px; text-align: left; }")
                   .append("th { background-color: #b8daff; color: #005073; }")
                   .append("tr:nth-child(even) { background-color: #d9edf7; }")
                   .append("</style>")
                   .append("</head><body>")
                   .append("<h1>Wettervorhersage</h1>")
                   .append("<table>")
                   .append("<tr><th>Date</th><th>Min/Max temperatur</th><th>avg temperatur</th><th>weather description</th><th>chance of precipitation</th><th>wind velocity</th></tr>");
    
        for (String date : groupedData.keySet()) {
            List<WeatherData> dailyData = groupedData.get(date);
            double sumTemp = 0, minTemp = Double.MAX_VALUE, maxTemp = Double.MIN_VALUE;
            double sumWindSpeed = 0, maxChanceOfPrecipitation = 0;
            Map<String, Integer> weatherDescriptions = new HashMap<>();

            for (WeatherData data : dailyData) {
                double currentTemp = data.getMain().getTemp() - 273.15;
                sumTemp += currentTemp;
                minTemp = Math.min(minTemp, data.getMain().getTemp_min() - 273.15);
                maxTemp = Math.max(maxTemp, data.getMain().getTemp_max() - 273.15);
                sumWindSpeed += data.getWind().getSpeed();
                maxChanceOfPrecipitation = Math.max(maxChanceOfPrecipitation, data.getPop() * 100);
                String weatherDescription = data.getWeather().get(0).getDescription();
                weatherDescriptions.put(weatherDescription, weatherDescriptions.getOrDefault(weatherDescription, 0) + 1);
            }
    
            double avgTemp = sumTemp / dailyData.size();
            double avgWindSpeed = sumWindSpeed / dailyData.size();
            String mostCommonWeather = Collections.max(weatherDescriptions.entrySet(), Map.Entry.comparingByValue()).getKey();
    
            htmlContent.append("<tr>")
                       .append("<td>").append(date).append("</td>")
                       .append("<td>").append(String.format("%.2f°C - %.2f°C", minTemp, maxTemp)).append("</td>")
                       .append("<td>").append(String.format("%.2f°C", avgTemp)).append("</td>")
                       .append("<td>").append(mostCommonWeather).append("</td>")
                       .append("<td>").append(String.format("%.2f%%", maxChanceOfPrecipitation)).append("</td>")
                       .append("<td>").append(String.format("%.2f m/s", avgWindSpeed)).append("</td>")
                       .append("</tr>");
        }
    
        htmlContent.append("</table></body></html>");
        return htmlContent.toString();
    }
    
    /**
     * Diese methode used to get the weather data as html
     * @param city
     * @return the weather data as html in String format
     * @throws JsonProcessingException
     */
    public String getWeatherForecastAsHtml(String city) throws JsonProcessingException {
        WeatherForecast forecast = getWeatherForecast(city); 
        return formatWeatherDataAsHtml(forecast); 
    }
}