package com.ust.srs.dao;

import java.util.*;
import javax.swing.JOptionPane;
import com.ust.srs.bean.*;
import com.ust.srs.service.Customer;
import com.ust.srs.util.*;

public class CustomerDao implements Customer {

    // ---------------- CU-001: View Schedule By Route ----------------
    @Override
    public ArrayList<ScheduleBean> viewScheduleByRoute(String source, String destination, String date) {
        ArrayList<ScheduleBean> result = new ArrayList<>();

        for (ScheduleBean schedule : DataStore.schedules.values()) {
            RouteBean route = DataStore.routes.get(schedule.getRouteID());
            if (route == null) continue;

            // Compare route source/destination ignoring case
            if (route.getSource().equalsIgnoreCase(source)
                    && route.getDestination().equalsIgnoreCase(destination)) {
                result.add(schedule);
            }
        }

        if (result.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No schedules found for " + source + " → " + destination);
        } else {
            StringBuilder sb = new StringBuilder("Available Schedules:\n");
            for (ScheduleBean s : result) {
                sb.append("Schedule ID: ").append(s.getScheduleID())
                        .append(", Ship: ").append(s.getShipID())
                        .append(", Route: ").append(s.getRouteID()).append("\n");
            }
            JOptionPane.showMessageDialog(null, sb.toString());
        }
        return result;
    }

    // ---------------- CU-002: Reserve Ticket ----------------
    @Override
    public String reserveTicket(ReservationBean reservationBean, ArrayList<PassengerBean> passengerBeanList) {
        try {
            // Get schedule and related route/ship
            ScheduleBean schedule = DataStore.schedules.get(reservationBean.getScheduleID());
            if (schedule == null) {
                JOptionPane.showMessageDialog(null, "Invalid Schedule ID!");
                return "FAIL";
            }
            RouteBean route = DataStore.routes.get(schedule.getRouteID());
            ShipBean ship = DataStore.ships.get(schedule.getShipID());

            // Capacity check
            int booked = 0;
            for (ReservationBean r : DataStore.reservations.values()) {
                if (r.getScheduleID().equals(schedule.getScheduleID())
                        && !"CANCELLED".equalsIgnoreCase(r.getBookingStatus())) {
                    booked += r.getNoOfSeats();
                }
            }
            int requested = passengerBeanList.size();
            if (booked + requested > ship.getReservationCapacity()) {
                JOptionPane.showMessageDialog(null, "Not enough seats available!");
                return "FAIL";
            }

            // Generate reservation ID
            String resID = IDGenerator.generateReservationID(route.getSource(), route.getDestination());
            reservationBean.setReservationID(resID);
            reservationBean.setBookingDate(new Date());
            reservationBean.setBookingStatus("CONFIRMED");
            reservationBean.setNoOfSeats(requested);
            reservationBean.setTotalFare(route.getFare() * requested);

            // Link passengers to reservation
            for (PassengerBean p : passengerBeanList) {
                p.setReservationID(resID);
                p.setScheduleID(reservationBean.getScheduleID());
            }

            // Save to DataStore
            DataStore.reservations.put(resID, reservationBean);
            DataStore.passengers.put(resID, passengerBeanList);

            JOptionPane.showMessageDialog(null,
                    "Reservation Successful!\nReservation ID: " + resID +
                    "\nTotal Fare: " + reservationBean.getTotalFare());
            return "SUCCESS";
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Reservation failed: " + e.getMessage());
            return "FAIL";
        }
    }

    // ---------------- CU-003: Cancel Ticket ----------------
    @Override
    public boolean cancelTicket(String reservationId) {
        ReservationBean r = DataStore.reservations.get(reservationId);
        if (r == null) {
            JOptionPane.showMessageDialog(null, "Reservation not found!");
            return false;
        }
        r.setBookingStatus("CANCELLED");
        JOptionPane.showMessageDialog(null, "Reservation cancelled successfully!");
        return true;
    }

    // ---------------- CU-004: View Ticket ----------------
    @Override
    public Map<ReservationBean, ArrayList<PassengerBean>> viewTicket(String reservationId) {
        Map<ReservationBean, ArrayList<PassengerBean>> map = new HashMap<>();
        ReservationBean r = DataStore.reservations.get(reservationId);
        if (r == null) {
            JOptionPane.showMessageDialog(null, "No ticket found for ID: " + reservationId);
            return map;
        }
        ArrayList<PassengerBean> plist = new ArrayList<>(DataStore.passengers.get(reservationId));
        map.put(r, plist);

        // Display ticket
        StringBuilder sb = new StringBuilder("Reservation Details:\n");
        sb.append("Reservation ID: ").append(r.getReservationID())
          .append("\nSchedule ID: ").append(r.getScheduleID())
          .append("\nSeats: ").append(r.getNoOfSeats())
          .append("\nTotal Fare: ").append(r.getTotalFare())
          .append("\nStatus: ").append(r.getBookingStatus())
          .append("\n\nPassengers:\n");
        for (PassengerBean p : plist) {
            sb.append(p.getName()).append(", Age: ").append(p.getAge())
              .append(", Gender: ").append(p.getGender()).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());

        return map;
    }

    // ---------------- CU-005: Print Ticket ----------------
    @Override
    public Map<ReservationBean, ArrayList<PassengerBean>> printTicket(String reservationId) {
        // For now, same as viewTicket (could later print to file)
        return viewTicket(reservationId);
    }
}

