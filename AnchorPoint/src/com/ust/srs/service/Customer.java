package com.ust.srs.service;

import java.util.ArrayList;
import java.util.Map;

import com.ust.srs.bean.PassengerBean;
import com.ust.srs.bean.ReservationBean;
import com.ust.srs.bean.ScheduleBean;

public interface Customer {
    ArrayList<ScheduleBean> viewScheduleByRoute(String source, String destination, String date);
    String reserveTicket(ReservationBean reservationBean, ArrayList<PassengerBean> passengerBean); // "SUCCESS"/"FAIL"
    boolean cancelTicket(String reservationId);
    Map<ReservationBean, ArrayList<PassengerBean>> viewTicket(String reservationId);
    Map<ReservationBean, ArrayList<PassengerBean>> printTicket(String reservationId);
}

}
