package com.ust.srs.dao;

import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

import com.ust.srs.bean.PassengerBean;
import com.ust.srs.bean.RouteBean;
import com.ust.srs.bean.ScheduleBean;
import com.ust.srs.bean.ShipBean;
import com.ust.srs.service.Administrator;

public class AdministratorDao implements Administrator{
	 // In-memory lists instead of a database
    private static ArrayList<ShipBean> ships = new ArrayList<>();
    private static ArrayList<RouteBean> routes = new ArrayList<>();
    private static ArrayList<ScheduleBean> schedules = new ArrayList<>();
    
    // for command-line input
    private static Scanner sc = new Scanner(System.in);

    // ---------------- SHIP ----------------

    @Override
    public String addShip(ShipBean shipbean) {
        // Option 1: take data via JOptionPane
        String name = JOptionPane.showInputDialog("Enter Ship Name:");
        int seatCap = Integer.parseInt(JOptionPane.showInputDialog("Enter Seating Capacity:"));
        int resCap = Integer.parseInt(JOptionPane.showInputDialog("Enter Reservation Capacity:"));

        // set bean data
        shipbean.setShipName(name);
        shipbean.setSeatingCapacity(seatCap);
        shipbean.setReservationCapacity(resCap);

        // generate simple ID
        String id = "SHP" + (ships.size() + 1);
        shipbean.setShipID(id);

        ships.add(shipbean);

        JOptionPane.showMessageDialog(null, "Ship Added Successfully! Ship ID: " + id);
        return "SUCCESS";
    }

    @Override
    public boolean modifyShip(ShipBean shipbean) {
        String id = JOptionPane.showInputDialog("Enter Ship ID to Modify:");
        for (ShipBean s : ships) {
            if (s.getShipID().equalsIgnoreCase(id)) {
                String newName = JOptionPane.showInputDialog("Enter New Name:", s.getShipName());
                int newCap = Integer.parseInt(JOptionPane.showInputDialog("Enter New Seating Capacity:", s.getSeatingCapacity()));

                s.setShipName(newName);
                s.setSeatingCapacity(newCap);
                s.setReservationCapacity(newCap);
                JOptionPane.showMessageDialog(null, "Ship Updated Successfully!");
                return true;
            }
        }
        JOptionPane.showMessageDialog(null, "Ship Not Found!");
        return false;
    }

    @Override
    public int removeShip(ArrayList<String> shipIds) {
        int count = 0;
        for (String id : shipIds) {
            for (ShipBean s : ships) {
                if (s.getShipID().equalsIgnoreCase(id)) {
                    ships.remove(s);
                    count++;
                    break;
                }
            }
        }
        JOptionPane.showMessageDialog(null, count + " Ship(s) Removed");
        return count;
    }

    @Override
    public ShipBean viewByShipId(String shipId) {
        for (ShipBean s : ships) {
            if (s.getShipID().equalsIgnoreCase(shipId)) {
                JOptionPane.showMessageDialog(null, s.toString());
                return s;
            }
        }
        JOptionPane.showMessageDialog(null, "Ship Not Found!");
        return null;
    }

    @Override
    public ArrayList<ShipBean> viewByAllShips() {
        StringBuilder sb = new StringBuilder("All Ships:\n");
        for (ShipBean s : ships) sb.append(s.toString()).append("\n");
        JOptionPane.showMessageDialog(null, sb.toString());
        return ships;
    }

    // ---------------- ROUTE ----------------

    @Override
    public String addRoute(RouteBean routebean) {
        String src = JOptionPane.showInputDialog("Enter Source:");
        String dest = JOptionPane.showInputDialog("Enter Destination:");
        double fare = Double.parseDouble(JOptionPane.showInputDialog("Enter Fare:"));

        routebean.setSource(src);
        routebean.setDestination(dest);
        routebean.setFare(fare);
        String id = "ROU" + (routes.size() + 1);
        routebean.setRouteID(id);
        routes.add(routebean);
        JOptionPane.showMessageDialog(null, "Route Added Successfully! Route ID: " + id);
        return "SUCCESS";
    }

    @Override
    public boolean modifyRoute(RouteBean routebean) {
        String id = JOptionPane.showInputDialog("Enter Route ID to Modify:");
        for (RouteBean r : routes) {
            if (r.getRouteID().equalsIgnoreCase(id)) {
                String newSrc = JOptionPane.showInputDialog("Enter New Source:", r.getSource());
                String newDest = JOptionPane.showInputDialog("Enter New Destination:", r.getDestination());
                double newFare = Double.parseDouble(JOptionPane.showInputDialog("Enter New Fare:", r.getFare()));

                r.setSource(newSrc);
                r.setDestination(newDest);
                r.setFare(newFare);
                JOptionPane.showMessageDialog(null, "Route Updated!");
                return true;
            }
        }
        JOptionPane.showMessageDialog(null, "Route Not Found!");
        return false;
    }

    @Override
    public int removeRoute(String routeid) {
        for (RouteBean r : routes) {
            if (r.getRouteID().equalsIgnoreCase(routeid)) {
                routes.remove(r);
                JOptionPane.showMessageDialog(null, "Route Removed!");
                return 1;
            }
        }
        JOptionPane.showMessageDialog(null, "Route Not Found!");
        return 0;
    }

    @Override
    public RouteBean viewByRouteId(String routeid) {
        for (RouteBean r : routes) {
            if (r.getRouteID().equalsIgnoreCase(routeid)) {
                JOptionPane.showMessageDialog(null, r.toString());
                return r;
            }
        }
        JOptionPane.showMessageDialog(null, "Route Not Found!");
        return null;
    }

    @Override
    public ArrayList<RouteBean> viewByAllRoute() {
        StringBuilder sb = new StringBuilder("All Routes:\n");
        for (RouteBean r : routes) sb.append(r.toString()).append("\n");
        JOptionPane.showMessageDialog(null, sb.toString());
        return routes;
    }

    // ---------------- SCHEDULE ----------------

    @Override
    public String addSchedule(ScheduleBean schedulebean) {
        String shipId = JOptionPane.showInputDialog("Enter Ship ID:");
        String routeId = JOptionPane.showInputDialog("Enter Route ID:");
        String date = JOptionPane.showInputDialog("Enter Start Date (dd-mm-yyyy):");

        schedulebean.setShipID(shipId);
        schedulebean.setRouteID(routeId);
        // just store date string for simplicity
        schedulebean.setScheduleID("SCH" + (schedules.size() + 1));

        schedules.add(schedulebean);
        JOptionPane.showMessageDialog(null, "Schedule Added! Schedule ID: " + schedulebean.getScheduleID());
        return "SUCCESS";
    }

    @Override
    public boolean modifySchedule(ScheduleBean schedulebean) {
        String id = JOptionPane.showInputDialog("Enter Schedule ID to Modify:");
        for (ScheduleBean s : schedules) {
            if (s.getScheduleID().equalsIgnoreCase(id)) {
                String newShipId = JOptionPane.showInputDialog("Enter New Ship ID:", s.getShipID());
                String newRouteId = JOptionPane.showInputDialog("Enter New Route ID:", s.getRouteID());

                s.setShipID(newShipId);
                s.setRouteID(newRouteId);
                JOptionPane.showMessageDialog(null, "Schedule Updated!");
                return true;
            }
        }
        JOptionPane.showMessageDialog(null, "Schedule Not Found!");
        return false;
    }

    @Override
    public int removeSchedule(ArrayList<String> scheduleIds) {
        int count = 0;
        for (String id : scheduleIds) {
            for (ScheduleBean s : schedules) {
                if (s.getScheduleID().equalsIgnoreCase(id)) {
                    schedules.remove(s);
                    count++;
                    break;
                }
            }
        }
        JOptionPane.showMessageDialog(null, count + " Schedule(s) Removed");
        return count;
    }

    @Override
    public ScheduleBean viewByScheduleId(String scheduleid) {
        for (ScheduleBean s : schedules) {
            if (s.getScheduleID().equalsIgnoreCase(scheduleid)) {
                JOptionPane.showMessageDialog(null, s.toString());
                return s;
            }
        }
        JOptionPane.showMessageDialog(null, "Schedule Not Found!");
        return null;
    }

    @Override
    public ArrayList<ScheduleBean> viewByAllSchedule() {
        StringBuilder sb = new StringBuilder("All Schedules:\n");
        for (ScheduleBean s : schedules) sb.append(s.toString()).append("\n");
        JOptionPane.showMessageDialog(null, sb.toString());
        return schedules;
    }

    // ---------------- PASSENGERS ----------------

    @Override
    public ArrayList<PassengerBean> viewPasengersByShip(String scheduleid) {
        JOptionPane.showMessageDialog(null, "Passenger list feature not implemented yet.");
        return new ArrayList<>();
    }
}
