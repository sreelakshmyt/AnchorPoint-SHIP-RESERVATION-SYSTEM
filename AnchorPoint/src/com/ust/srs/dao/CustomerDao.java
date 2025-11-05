package com.ust.srs.dao;

import java.util.ArrayList;
import java.util.Map;

import com.ust.srs.bean.PassengerBean;
import com.ust.srs.bean.ReservationBean;
import com.ust.srs.bean.ScheduleBean;
import com.ust.srs.service.Customer;

public class CustomerDao implements Customer {

	@Override
	public ArrayList<ScheduleBean> viewScheduleByRoute(String source, String destination, String date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String reserveTicket(ReservationBean reservationBean, ArrayList<PassengerBean> passengerBean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean cancelTicket(String reservationId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Map<ReservationBean, ArrayList<PassengerBean>> viewTicket(String reservationId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<ReservationBean, ArrayList<PassengerBean>> printTicket(String reservationId) {
		// TODO Auto-generated method stub
		return null;
	}

}
