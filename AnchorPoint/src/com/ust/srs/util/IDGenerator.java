package com.ust.srs.util;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Utility class to generate unique IDs for Ship, Route, Schedule, and Reservation.
 */
public class IDGenerator {

    private static AtomicInteger shipCounter = new AtomicInteger(1000);
    private static AtomicInteger routeCounter = new AtomicInteger(1000);
    private static AtomicInteger scheduleCounter = new AtomicInteger(1000);
    private static AtomicInteger reservationCounter = new AtomicInteger(1000);

    // ---------- SHIP ----------
    public static String generateShipID() {
        return "SHP" + shipCounter.incrementAndGet();
    }

    // ---------- ROUTE ----------
    public static String generateRouteID() {
        return "ROU" + routeCounter.incrementAndGet();
    }

    public static String generateRouteID(String source, String destination) {
        String src = source.substring(0, 2).toUpperCase();
        String dest = destination.substring(0, 2).toUpperCase();
        return "ROU" + src + dest + routeCounter.incrementAndGet();
    }

    // ---------- SCHEDULE ----------
    public static String generateScheduleID() {
        return "SCH" + scheduleCounter.incrementAndGet();
    }

    // ✅ Overloaded method that takes source & destination
    public static String generateScheduleID(String source, String destination) {
        String src = source.substring(0, 2).toUpperCase();
        String dest = destination.substring(0, 2).toUpperCase();
        return "SCH" + src + dest + scheduleCounter.incrementAndGet();
    }

    // ---------- RESERVATION ----------
    public static String generateReservationID(String source, String destination) {
        String prefix = (source.substring(0, 2) + destination.substring(0, 2)).toUpperCase();
        return prefix + reservationCounter.incrementAndGet();
    }
}
