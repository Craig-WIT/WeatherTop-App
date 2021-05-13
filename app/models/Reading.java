package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

import java.util.Date;

@Entity
public class Reading extends Model {
    public int code;
    public int pressure;
    public int windDirection;
    public double temperature;
    public double windSpeed;
    public Date date;

    public Reading(int code, int pressure, int windDirection, double temperature, double windSpeed, Date date) {
        this.code = code;
        this.pressure = pressure;
        this.windDirection = windDirection;
        this.temperature = temperature;
        this.windSpeed = windSpeed;
        this.date = date;
    }

    public int getCode() {
        return code;
    }

    public int getWindDirection() {
        return windDirection;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public int getPressure() {
        return pressure;
    }

    public Date getDate() {
        return date;
    }
}