package com.ust.srs.bean;

import java.util.Date;

/**
 * Bean class to store Reservation details.
 */
public class ReservationBean {

    private String reservationID;
    private String scheduleID;
    private String userID;
    private Date bookingDate;     // ✅ this is the missing field
    private int noOfSeats;
    private double totalFare;
    private String bookingStatus; // e.g., CONFIRMED, CANCELLED

    // --- Getters and Setters ---
    public String getReservationID() {
        return reservationID;
    }

    public void setReservationID(String reservationID) {
        this.reservationID = reservationID;
    }

    public String getScheduleID() {
        return scheduleID;
    }

    public void setScheduleID(String scheduleID) {
        this.scheduleID = scheduleID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) { // ✅ the missing method
        this.bookingDate = bookingDate;
    }

    public int getNoOfSeats() {
        return noOfSeats;
    }

    public void setNoOfSeats(int noOfSeats) {
        this.noOfSeats = noOfSeats;
    }

    public double getTotalFare() {
        return totalFare;
    }

    public void setTotalFare(double totalFare) {
        this.totalFare = totalFare;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    // --- Optional: For debugging/display ---
    @Override
    public String toString() {
        return "Reservation ID: " + reservationID +
               ", Schedule ID: " + scheduleID +
               ", User ID: " + userID +
               ", Booking Date: " + bookingDate +
               ", Seats: " + noOfSeats +
               ", Fare: " + totalFare +
               ", Status: " + bookingStatus;
    }
}

