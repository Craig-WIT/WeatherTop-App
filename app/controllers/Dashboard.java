package controllers;

import models.Member;
import models.Station;
import play.Logger;
import play.mvc.Controller;

import java.util.Comparator;
import java.util.List;

public class Dashboard extends Controller {
    public static void index() {
        Logger.info("Rendering Admin");
        Member member = Accounts.getLoggedInMember();
        List<Station> stations = member.stations;
        stations.sort(Comparator.comparing(Station::getName, String.CASE_INSENSITIVE_ORDER));
        render("dashboard.html", stations);
    }

    public static void deleteStation(Long id) {
        Logger.info("Deleting a Station");
        Member member = Accounts.getLoggedInMember();
        Station station = Station.findById(id);
        member.stations.remove(station);
        member.save();
        station.delete();
        redirect("/dashboard");
    }

    public static void addStation(String name, double lat, double lng) {
        Logger.info("Adding a Station");
        Member member = Accounts.getLoggedInMember();
        List<Station> stations = member.stations;
        Station station = new Station(name, lat, lng);
        if ((name.isEmpty()) || (lat == 0.0) || (lng == 0.0)) {
            String fail = "No details can be left blank";
            stations.sort(Comparator.comparing(Station::getName, String.CASE_INSENSITIVE_ORDER));
            render("dashboardfail.html", station, stations, fail);
        } else {
            member.stations.add(station);
            member.save();
            redirect("/dashboard");
        }
    }
}
