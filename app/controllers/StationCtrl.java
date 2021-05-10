package controllers;

import java.util.Calendar;

import org.apache.commons.lang.time.DateUtils;
import java.util.Date;

import models.Reading;
import models.Station;
import play.Logger;
import play.mvc.Controller;

public class StationCtrl extends Controller
{
    public static void index(Long id)
    {
        Station station = Station.findById(id);
        Logger.info ("Station id = " + id);
        render("station.html", station);
    }

    public static void deleteReading (Long id, Long readingid)
    {
        Station station = Station.findById(id);
        Reading reading = Reading.findById(readingid);
        Logger.info ("Removing" + reading.getCode());
        station.readings.remove(reading);
        station.save();
        reading.delete();
        redirect ("/stations/" + id);
    }

    public static void addReading(Long id, int code, int pressure, int windDirection, double temperature, double windSpeed, Date roundDate)
    {
        Date date = new Date();
        roundDate = DateUtils.round(date, Calendar.SECOND);
        Reading reading = new Reading(code,pressure,windDirection,temperature,windSpeed,roundDate);
        Station station = Station.findById(id);
        if((code == 0) || (pressure == 0) || (windDirection == 0) || (temperature == 0.0) || (windSpeed == 0.0)){
            render("stationfail.html", station);
        }
        else {
            station.readings.add(reading);
            station.save();
            redirect("/stations/" + id);
        }
    }
}