package com.ust.srs.dao;

import java.util.*;
import javax.swing.JOptionPane;
import com.ust.srs.bean.*;
import com.ust.srs.service.Administrator;
import com.ust.srs.util.*;

public class AdministratorDao implements Administrator {

    // AD-001 Add Ship
//    @Override
//    public String addShip(ShipBean shipbean) {
//        String shipName = JOptionPane.showInputDialog("Enter Ship Name:");
//        String source = JOptionPane.showInputDialog("Enter Source:");
//        String destination = JOptionPane.showInputDialog("Enter Destination:");
//        String duration = JOptionPane.showInputDialog("Enter Travel Duration:");
//        int seatCap = Integer.parseInt(JOptionPane.showInputDialog("Enter Seating Capacity:"));
//        int resCap = Integer.parseInt(JOptionPane.showInputDialog("Enter Reservation Capacity:"));
//
////        shipbean.setShipID(IDGenerator.generateShipID(shipName));
//
//        String id = IDGenerator.generateShipID();
//        shipbean.setShipName(id);
//        shipbean.setShipName(shipName);
//        shipbean.setSeatingCapacity(seatCap);
//        shipbean.setReservationCapacity(resCap);
//
//        DataStore.ships.put(shipbean.getShipID(), shipbean);
//
//        JOptionPane.showMessageDialog(null, "Ship Added Successfully\n" + shipbean);
//        return "SUCCESS";
//    }
	
	@Override
	public String addShip(ShipBean shipbean) {
	    // Take ship details from user
	    String name = JOptionPane.showInputDialog("Enter Ship Name:");
	    String source = JOptionPane.showInputDialog("Enter Source:");
	    String destination = JOptionPane.showInputDialog("Enter Destination:");
	    int seatCap = Integer.parseInt(JOptionPane.showInputDialog("Enter Seating Capacity:"));
	    int resCap = Integer.parseInt(JOptionPane.showInputDialog("Enter Reservation Capacity:"));

	    // ✅ Generate a unique Ship ID
	    String id = IDGenerator.generateShipID();

	    // ✅ Set all data into the ShipBean object
	    shipbean.setShipID(id);
	    shipbean.setShipName(name);
	    shipbean.setSeatingCapacity(seatCap);
	    shipbean.setReservationCapacity(resCap);

	    // Optional: Store source/destination if ShipBean has those fields
	    // shipbean.setSource(source);
	    // shipbean.setDestination(destination);

	    // ✅ Save this ship into the in-memory "database"
	    DataStore.ships.put(id, shipbean);

	    // ✅ Confirm success
	    JOptionPane.showMessageDialog(null,
	        "Ship Added Successfully!\n" +
	        "Ship ID: " + id + "\n" +
	        "Name: " + name + "\n" +
	        "Seating Capacity: " + seatCap);

	    return "SUCCESS";
	}


    // Modify Reservation Capacity / WL
    @Override
    public boolean modifyShip(ShipBean shipbean) {
        String id = JOptionPane.showInputDialog("Enter Ship ID to Modify:");
        ShipBean s = DataStore.ships.get(id);
        if (s == null) {
            JOptionPane.showMessageDialog(null, "Ship not found!");
            return false;
        }
        int newRes = Integer.parseInt(JOptionPane.showInputDialog("Enter new Reservation Capacity:", s.getReservationCapacity()));
        s.setReservationCapacity(newRes);
        JOptionPane.showMessageDialog(null, "Reservation Capacity Updated!");
        return true;
    }

    // Delete Ship
    @Override
    public int removeShip(ArrayList<String> shipIds) {
        int removed = 0;
        for (String id : shipIds) {
            if (DataStore.ships.remove(id) != null) removed++;
        }
        JOptionPane.showMessageDialog(null, removed + " ship(s) removed");
        return removed;
    }

    // AD-005 Add Route
    @Override
    public String addRoute(RouteBean routebean) {
        String source = JOptionPane.showInputDialog("Enter Source:");
        String dest = JOptionPane.showInputDialog("Enter Destination:");
        double distance = Double.parseDouble(JOptionPane.showInputDialog("Enter Distance (km):"));
        double costPerKm = Double.parseDouble(JOptionPane.showInputDialog("Enter Cost/km:"));

        routebean.setRouteID(IDGenerator.generateRouteID(source, dest));
        routebean.setSource(source);
        routebean.setDestination(dest);
        routebean.setTravelDuration(distance + "km");
        routebean.setFare(distance * costPerKm);

        DataStore.routes.put(routebean.getRouteID(), routebean);
        JOptionPane.showMessageDialog(null, "Route Added Successfully\n" + routebean);
        return "SUCCESS";
    }

    // Modify Route
    @Override
    public boolean modifyRoute(RouteBean routebean) {
        String id = JOptionPane.showInputDialog("Enter Route ID:");
        RouteBean r = DataStore.routes.get(id);
        if (r == null) return false;
        double newFare = Double.parseDouble(JOptionPane.showInputDialog("Enter New Fare:", r.getFare()));
        r.setFare(newFare);
        JOptionPane.showMessageDialog(null, "Route Fare Updated!");
        return true;
    }

    // Delete Route
    @Override
    public int removeRoute(String routeid) {
        return (DataStore.routes.remove(routeid) != null) ? 1 : 0;
    }

    // AD-009 Add Schedule
    @Override
    public String addSchedule(ScheduleBean schedulebean) {
        String shipId = JOptionPane.showInputDialog("Enter Ship ID:");
        String routeId = JOptionPane.showInputDialog("Enter Route ID:");
        String day = JOptionPane.showInputDialog("Enter Schedule Days (Mon,Wed,Fri):");

        schedulebean.setScheduleID(IDGenerator.generateScheduleID(shipId, routeId));
        schedulebean.setShipID(shipId);
        schedulebean.setRouteID(routeId);

        DataStore.schedules.put(schedulebean.getScheduleID(), schedulebean);
        JOptionPane.showMessageDialog(null, "Schedule Added: " + schedulebean.getScheduleID() + " for " + day);
        return "SUCCESS";
    }

    @Override
    public boolean modifySchedule(ScheduleBean schedulebean) {
        String id = JOptionPane.showInputDialog("Enter Schedule ID:");
        ScheduleBean s = DataStore.schedules.get(id);
        if (s == null) return false;
        String newRoute = JOptionPane.showInputDialog("Enter New Route ID:", s.getRouteID());
        s.setRouteID(newRoute);
        JOptionPane.showMessageDialog(null, "Schedule Modified!");
        return true;
    }

    @Override
    public int removeSchedule(ArrayList<String> scheduleIds) {
        int count = 0;
        for (String id : scheduleIds) {
            if (DataStore.schedules.remove(id) != null) count++;
        }
        JOptionPane.showMessageDialog(null, count + " schedule(s) deleted");
        return count;
    }

    // AD-013 View Passenger / Booking Details
    @Override
    public ArrayList<PassengerBean> viewPasengersByShip(String scheduleid) {
        ArrayList<PassengerBean> list = new ArrayList<>();
        for (List<PassengerBean> plist : DataStore.passengers.values()) {
            for (PassengerBean p : plist) {
                if (scheduleid.equals(p.getScheduleID())) list.add(p);
            }
        }
        JOptionPane.showMessageDialog(null, list.size() + " passengers found for Schedule " + scheduleid);
        return list;
    }

    // View Methods
    @Override
    public ShipBean viewByShipId(String shipId) { return DataStore.ships.get(shipId); }
    @Override
    public RouteBean viewByRouteId(String routeid) { return DataStore.routes.get(routeid); }
    @Override
    public ArrayList<ShipBean> viewByAllShips() { return new ArrayList<>(DataStore.ships.values()); }
    @Override
    public ArrayList<RouteBean> viewByAllRoute() { return new ArrayList<>(DataStore.routes.values()); }
    @Override
    public ArrayList<ScheduleBean> viewByAllSchedule() { return new ArrayList<>(DataStore.schedules.values()); }
    @Override
    public ScheduleBean viewByScheduleId(String scheduleid) { return DataStore.schedules.get(scheduleid); }
}

