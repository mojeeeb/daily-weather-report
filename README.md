# daily-weather-report
Weather Forecast Application

This Spring Boot application retrieves and processes weather forecast data using the OpenWeatherMap API. It presents this data in a user-friendly format and includes functionality to schedule and send weather reports via email.
Features

Fetch weather data from the OpenWeatherMap API.
Process and display weather data, including min/max and average temperatures, precipitation probability, and wind speed.
Email functionality to send scheduled weather reports.

Prerequisites

Before you begin, ensure you have the following:

    Java JDK 11 or higher.
    Gradle for dependency management and build automation.
    An OpenWeatherMap API key.

Installation

Clone the repository:


    git clone https://github.com/mojeeeb/daily-weather-report.git

Navigate to the project directory:

    cd daily-weather-report

Usage

To run the application using Gradle, execute:


    ./gradlew bootRun

The application will start on the configured port (default is 8080).
Configuration

    API Key: Set your OpenWeatherMap API key, gmail sender and
    password of gmail in the application.properties file:

    openweathermap.api.key=YOUR_API_KEY
    spring.mail.username=SENDER_GAMIL
    spring.mail.password=PASSWORD_OF_THE_GMAIL
    
    Write the sender gmail also in sendEmail methode in the class EmailService
    helper.setFrom("sender email");
    
    Write the reseriver email in excel file 
    
    change the path in the method sendDailyWeatherEmail in the class WeatherScheduledTask
    List<String> emailAddresses = excelService.readEmailsFromExcel("......./daily-weather-report/excel.xlsx");

    Scheduled Tasks: Modify the cron expression in WeatherScheduledTask.java to adjust the email scheduling.

Contributing

We welcome contributions. To contribute:

1. Fork the repository.
2. Create a new branch (git checkout -b feature/YourFeature).
3. Commit your changes (git commit -am 'Add some feature').
4. Push to the branch (git push origin feature/YourFeature).
5. Open a pull request.
