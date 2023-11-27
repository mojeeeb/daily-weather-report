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

    cd [project-directory]

Usage

To run the application using Gradle, execute:


./gradlew bootRun

The application will start on the configured port (default is 8080).
Configuration

    API Key: Set your OpenWeatherMap API key in the application.properties file:

    properties

    openweathermap.api.key=YOUR_API_KEY

    Email Settings: Configure email settings in application.properties for email functionality.
    Scheduled Tasks: Modify the cron expression in WeatherScheduledTask.java to adjust the email scheduling.

Contributing

We welcome contributions. To contribute:

1. Fork the repository.
2. Create a new branch (git checkout -b feature/YourFeature).
3. Commit your changes (git commit -am 'Add some feature').
4. Push to the branch (git push origin feature/YourFeature).
5. Open a pull request.
