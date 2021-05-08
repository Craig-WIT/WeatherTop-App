package controllers;

import java.util.List;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        Logger.info ("Removing" + reading.code);
        station.readings.remove(reading);
        station.save();
        reading.delete();
        redirect ("/stations/" + id);
    }

    public static void addReading(Long id, int code, int pressure, int windDirection, double temperature, double windSpeed, String date)
    {
        LocalDateTime timeStamp = LocalDateTime.now();
        DateTimeFormatter timeStampFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        date = timeStamp.format(timeStampFormat);
        Logger.info(date);
        Reading reading = new Reading(code,pressure,windDirection,temperature,windSpeed,date);
        Station station = Station.findById(id);
        station.readings.add(reading);
        station.save();
        redirect ("/stations/" + id);
    }
}