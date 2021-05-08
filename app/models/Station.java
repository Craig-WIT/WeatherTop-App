package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.swing.*;

import play.Logger;
import play.db.jpa.Model;
import java.lang.Math;

@Entity
public class Station extends Model
{
    public String name;
    @OneToMany(cascade = CascadeType.ALL)
    public List<Reading> readings = new ArrayList<Reading>();
    public Reading lastReading;
    public double lat;
    public double lng;
    public int beaufortScale;
    public String windCompass;
    public double windChill;
    public double maxWind;
    public double minWind;
    public String icon;
    private String weatherCode;
    public double tempCelsius;
    public double tempFahrenheit;
    public double maxTemp;
    public double minTemp;
    public int pressure;
    public int maxPressure;
    public int minPressure;
    public String windTrend;
    public String tempTrend;
    public String pressureTrend;

    public Station(String name, double lat,double lng)
    {
        this.name = name;
        this.lat = lat;
        this.lng = lng;
    }

    public Station(String name) {
        this.name = name;
    }

    public String getWeatherCode(){
        this.weatherCode = weatherCode();
        return weatherCode;
    }

    public double getTempCelsius() {
        this.tempCelsius = tempCelsius();
        return tempCelsius;
    }

    public double getTempFahrenheit() {
        this.tempFahrenheit = celsiusToFahrenheit();
        return tempFahrenheit;
    }

    public double getMaxTemp() {
        this.maxTemp = maxTemp();
        return maxTemp;
    }

    public double getMinTemp() {
        this.minTemp = minTemp();
        return minTemp;
    }

    public int getBeaufortScale(){
        this.beaufortScale = beaufortScale();
        return beaufortScale;
    }

    public String getWindCompass(){
        this.windCompass = windCompass();
        return windCompass;
    }

    public double getWindChill() {
        this.windChill = windChill();
        return windChill;
    }

    public double getMaxWind() {
        this.maxWind = maxWind();
        return maxWind;
    }

    public double getMinWind() {
        this.minWind = minWind();
        return minWind;
    }

    public int getPressure() {
        this.pressure = pressure();
        return pressure;
    }

    public int getMaxPressure(){
        this.maxPressure = maxPressure();
        return maxPressure;
    }

    public int getMinPressure(){
        this.minPressure = minPressure();
        return minPressure;
    }

    public String getWindTrend(){
        this.windTrend = windTrend();
        return windTrend;
    }

    public String getTempTrend(){
        this.tempTrend = tempTrend();
        return tempTrend;
    }

    public String getPressureTrend(){
        this.pressureTrend = pressureTrend();
        return pressureTrend;
    }

    public Reading getLastReading(){
        if(readings.size() != 0){
        int latestReadingId = readings.size() - 1;
        lastReading = readings.get(latestReadingId);}
        return lastReading;
    }

    public String weatherCode() {
        String weatherCode = "Enter valid weather code";
        icon = "/public/images/na.png";
        if(readings.size() != 0) {
            Reading reading = getLastReading();
            int code = reading.code;
            switch (code) {
                case 100:
                    weatherCode = "Clear";
                    icon = "/public/images/clear.png";
                    break;
                case 200:
                    weatherCode = "Partial clouds";
                    icon = "/public/images/partialcloud.png";
                    break;
                case 300:
                    weatherCode = "Cloudy";
                    icon = "/public/images/heavycloud.png";
                    break;
                case 400:
                    weatherCode = "Light Showers";
                    icon = "/public/images/lightrain.png";
                    break;
                case 500:
                    weatherCode = "Heavy Showers";
                    icon = "/public/images/rain.png";
                    break;
                case 600:
                    weatherCode = "Rain";
                    icon = "/public/images/heavyrain.png";
                    break;
                case 700:
                    weatherCode = "Snow";
                    icon = "/public/images/snow.png";
                    break;
                case 800:
                    weatherCode = "Thunder";
                    icon = "/public/images/thunder.png";
                    break;
                default:
                    weatherCode = "Enter valid weather code";
                    icon = "/public/images/na.png";
                    break;
            }
        }
        return weatherCode;
    }

    public String getIcon(){
        return icon;
    }

    public double tempCelsius(){
        if(readings.size() != 0) {
            Reading reading = getLastReading();
            double tempCelsius = reading.getTemperature();
            return tempCelsius;
        }
        return 0.0;
    }

    public double celsiusToFahrenheit(){
        if(readings.size() != 0) {
            Reading reading = getLastReading();
            double temperature = reading.temperature;
            double toFahrenheit = temperature * 9 / 5 + 32;
            return toTwoDecimalPlaces(toFahrenheit);
        }
        return 0.0;
    }

    public int beaufortScale() {
        Reading reading = getLastReading();
        int beaufortScale = 0;
        if(readings.size() != 0) {
            double windSpeed = reading.windSpeed;
            if ((windSpeed >= 0) && (windSpeed <= 1)) {
                beaufortScale = 0;
                return beaufortScale;
            } else if (windSpeed <= 5) {
                beaufortScale = 1;
                return beaufortScale;
            } else if (windSpeed <= 11) {
                beaufortScale = 2;
                return beaufortScale;
            } else if (windSpeed <= 19) {
                beaufortScale = 3;
                return beaufortScale;
            } else if (windSpeed <= 28) {
                beaufortScale = 4;
                return beaufortScale;
            } else if (windSpeed <= 38) {
                beaufortScale = 5;
                return beaufortScale;
            } else if (windSpeed <= 49) {
                beaufortScale = 6;
                return beaufortScale;
            } else if (windSpeed <= 61) {
                beaufortScale = 7;
                return beaufortScale;
            } else if (windSpeed <= 74) {
                beaufortScale = 8;
                return beaufortScale;
            } else if (windSpeed <= 88) {
                beaufortScale = 9;
                return beaufortScale;
            } else if (windSpeed <= 102) {
                beaufortScale = 10;
                return beaufortScale;
            } else if (windSpeed >= 102) {
                beaufortScale = 11;
                return beaufortScale;
            } else if (windSpeed < 0) {
                return 0;
            }
        }
            return 0;
        }

    public String windCompass() {
        Reading reading = getLastReading();
        String windCompass = "";
        if(readings.size() != 0) {
            double windDirection = reading.windDirection;
            if ((windDirection >= 348.75) && (windDirection <= 360)) {
                windCompass = "North";
                return windCompass;
            }
            else if ((windDirection >= 0) && (windDirection <= 11.25)) {
                windCompass = "North";
                return windCompass;
            }
            else if ((windDirection >= 11.25) && (windDirection <= 33.75)) {
                windCompass = "North NE";
                return windCompass;
            } else if ((windDirection >= 33.75) && (windDirection <= 56.25)) {
                windCompass = "North East";
                return windCompass;
            } else if ((windDirection >= 56.25) && (windDirection <= 78.75)) {
                windCompass = "East NE";
                return windCompass;
            } else if ((windDirection >= 78.75) && (windDirection <= 101.25)) {
                windCompass = "East";
                return windCompass;
            } else if ((windDirection >= 101.25) && (windDirection <= 123.75)) {
                windCompass = "East SE";
                return windCompass;
            } else if ((windDirection >= 123.75) && (windDirection <= 146.25)) {
                windCompass = "South East";
                return windCompass;
            } else if ((windDirection >= 146.25) && (windDirection <= 168.75)) {
                windCompass = "South SE";
                return windCompass;
            } else if ((windDirection >= 168.75) && (windDirection <= 191.25)) {
                windCompass = "South";
                return windCompass;
            } else if ((windDirection >= 191.25) && (windDirection <= 213.75)) {
                windCompass = "South SW";
                return windCompass;
            } else if ((windDirection >= 213.75) && (windDirection <= 236.25)) {
                windCompass = "South West";
                return windCompass;
            } else if ((windDirection >= 236.25) && (windDirection <= 258.75)) {
                windCompass = "West SW";
                return windCompass;
            } else if ((windDirection >= 258.75) && (windDirection <= 281.25)) {
                windCompass = "West";
                return windCompass;
            } else if ((windDirection >= 281.25) && (windDirection <= 303.75)) {
                windCompass = "West NW";
                return windCompass;
            } else if ((windDirection >= 303.75) && (windDirection <= 326.25)) {
                windCompass = "North West";
                return windCompass;
            } else if ((windDirection >= 326.25) && (windDirection <= 348.75)) {
                windCompass = "North NW";
                return windCompass;
            } else if((windDirection < 0) || (windDirection >360)){
                windCompass = "Enter wind direction";
                return windCompass;
            }
        }
        return "Enter wind direction";
    }

    public double windChill(){
        Reading reading = getLastReading();
        if(readings.size() != 0) {
            double temp = reading.temperature;
            double windSpeed = reading.windSpeed;
            double windChill = 13.12 + 0.6215 * (temp) - 11.37 * (Math.pow(windSpeed, 0.16)) + 0.3965 * (temp) * (Math.pow(windSpeed, 0.16));
            return toTwoDecimalPlaces(windChill);
        }
        return 0.0;
    }

    private double toTwoDecimalPlaces(double num){
        return (int) (num *100) / 100.0;
    }

    public int pressure(){
        if(readings.size() != 0) {
            Reading reading = getLastReading();
            int pressure = reading.pressure;
            return pressure;
        }
        return 0;
    }

    public double maxTemp(){
        if (readings.size() != 0) {
            Reading maxReading = readings.get(0);
            double maxTemp = maxReading.getTemperature();
            for (Reading reading: readings) {
                if (reading.getTemperature() > maxReading.getTemperature())
                    maxTemp = reading.getTemperature();
            }
            return maxTemp;
        } else
            return 0.0;
    }

    public double minTemp(){
        if (readings.size() != 0) {
            Reading minReading = readings.get(0);
            double minTemp = minReading.getTemperature();
            for (Reading reading: readings) {
                if (reading.getTemperature() < minReading.getTemperature())
                    minTemp = reading.getTemperature();
            }
            return minTemp;
        } else
            return 0.0;
    }

    public double maxWind(){
        if (readings.size() != 0) {
            Reading maxReading = readings.get(0);
            double maxWind = maxReading.getWindSpeed();
            for (Reading reading: readings) {
                if (reading.getWindSpeed() > maxReading.getWindSpeed())
                    maxWind = reading.getWindSpeed();
            }
            return maxWind;
        } else
            return 0.0;
    }

    public double minWind(){
        if (readings.size() != 0) {
            Reading minReading = readings.get(0);
            double minWind = minReading.getWindSpeed();
            for (Reading reading: readings) {
                if (reading.getWindSpeed() < minReading.getWindSpeed())
                    minWind = reading.getWindSpeed();
            }
            return minWind;
        } else
            return 0.0;
    }

    public int maxPressure(){
        if (readings.size() != 0) {
            Reading maxReading = readings.get(0);
            int maxPressure = maxReading.getPressure();
            for (Reading reading: readings) {
                if (reading.getPressure() > maxReading.getPressure())
                    maxPressure = reading.getPressure();
            }
            return maxPressure;
        } else
            return 0;
    }

    public int minPressure(){
        if (readings.size() != 0) {
            Reading minReading = readings.get(0);
            int minPressure = minReading.getPressure();
            for (Reading reading: readings) {
                if (reading.getPressure() < minReading.getPressure())
                    minPressure = reading.getPressure();
            }
            return minPressure;
        } else
            return 0;
    }

    public String windTrend() {
        String windTrend = "";
        if (readings.size() != 0) {
            windTrend = "/public/images/noTrend.png";
            double latestWindTrend = 0;
            double priorWindTrend = 0;
            if (readings.size() > 3) {
                for (int i = 0; i < 3; i++) {
                    Reading reading = readings.get(readings.size() - i - 1);
                    latestWindTrend += reading.windSpeed;
                }
                for (int i = 0; i < 3; i++) {
                    Reading reading = readings.get(readings.size() - i - 2);
                    priorWindTrend += reading.windSpeed;
                }
                if (latestWindTrend > priorWindTrend) {
                    windTrend = "/public/images/trendUp.png";
                }
                else if(latestWindTrend < priorWindTrend){
                    windTrend = "/public/images/trendDown.png";
                }
                else{
                    windTrend = "/public/images/noTrend.png";
                }
            }
        }
        return windTrend;
    }

    public String tempTrend() {
        String tempTrend = "";
        if (readings.size() != 0) {
            tempTrend = "/public/images/noTrend.png";
            double latestTempTrend = 0;
            double priorTempTrend = 0;
            if (readings.size() > 3) {
                for (int i = 0; i < 3; i++) {
                    Reading reading = readings.get(readings.size() - i - 1);
                    latestTempTrend += reading.temperature;
                }
                for (int i = 0; i < 3; i++) {
                    Reading reading = readings.get(readings.size() - i - 2);
                    priorTempTrend += reading.temperature;
                }
                if (latestTempTrend > priorTempTrend) {
                    tempTrend = "/public/images/trendUp.png";
                }
                else if (latestTempTrend < priorTempTrend){
                    tempTrend = "/public/images/trendDown.png";
                }
                else{
                    tempTrend = "/public/images/noTrend.png";
                }
            }
        }
        return tempTrend;
    }

    public String pressureTrend() {
        String pressureTrend = "";
        if (readings.size() != 0) {
            pressureTrend = "/public/images/noTrend.png";
            double latestPressureTrend = 0;
            double priorPressureTrend = 0;
            if (readings.size() > 3) {
                for (int i = 0; i < 3; i++) {
                    Reading reading = readings.get(readings.size() - i - 1);
                    latestPressureTrend += reading.pressure;
                }
                for (int i = 0; i < 3; i++) {
                    Reading reading = readings.get(readings.size() - i - 2);
                    priorPressureTrend += reading.pressure;
                }
                if (latestPressureTrend > priorPressureTrend) {
                    pressureTrend = "/public/images/trendUp.png";
                }
                else if(latestPressureTrend < priorPressureTrend){
                    pressureTrend = "/public/images/trendDown.png";
                }
                else{
                    pressureTrend = "/public/images/noTrend.png";
                }
            }
        }
        return pressureTrend;
    }
}
