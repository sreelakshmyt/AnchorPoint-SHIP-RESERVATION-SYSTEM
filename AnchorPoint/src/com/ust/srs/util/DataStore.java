package com.ust.srs.util;

import java.util.*;
import com.ust.srs.bean.*;

public class DataStore {
    public static Map<String, CredentialsBean> credentials = new HashMap<>();
    public static Map<String, ShipBean> ships = new HashMap<>();
    public static Map<String, RouteBean> routes = new HashMap<>();
    public static Map<String, ScheduleBean> schedules = new HashMap<>();
    public static Map<String, ReservationBean> reservations = new HashMap<>();
    public static Map<String, List<PassengerBean>> passengers = new HashMap<>();

    static {
        // Two default users
        credentials.put("admin", new CredentialsBean("admin", "admin123", "A", 0));
        credentials.put("cust", new CredentialsBean("cust", "cust123", "C", 0));

        // Preloaded ship and route data
        ShipBean ship = new ShipBean();
        ship.setShipID("SHP1001");
        ship.setShipName("Blue Whale");
        ship.setSeatingCapacity(200);
        ship.setReservationCapacity(180);
        ships.put(ship.getShipID(), ship);

        RouteBean route = new RouteBean();
        route.setRouteID("ROU1001");
        route.setSource("Kochi");
        route.setDestination("Goa");
        route.setTravelDuration("10hr");
        route.setFare(1200.0);
        routes.put(route.getRouteID(), route);
    }
}
