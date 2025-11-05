package com.ust.srs.service;

import java.util.ArrayList;

import com.ust.srs.bean.PassengerBean;
import com.ust.srs.bean.RouteBean;
import com.ust.srs.bean.ScheduleBean;
import com.ust.srs.bean.ShipBean;

public interface Administrator {
	 String addShip(ShipBean shipbean); // "SUCCESS"/"FAIL"/"ERROR"
	    boolean modifyShip(ShipBean shipbean);
	    int removeShip(ArrayList<String> shipIds);

	    String addSchedule(ScheduleBean schedulebean); // "SUCCESS"/"FAIL"/"ERROR"
	    boolean modifySchedule(ScheduleBean schedulebean);
	    int removeSchedule(ArrayList<String> scheduleIds);

	    String addRoute(RouteBean routebean); // "SUCCESS"/"FAIL"/"ERROR"
	    boolean modifyRoute(RouteBean routebean);
	    int removeRoute(String routeid);

	    ShipBean viewByShipId(String shipId);
	    RouteBean viewByRouteId(String routeid);
	    ArrayList<ShipBean> viewByAllShips();
	    ArrayList<RouteBean> viewByAllRoute();
	    ArrayList<ScheduleBean> viewByAllSchedule();
	    ScheduleBean viewByScheduleId(String scheduleid);
	    ArrayList<PassengerBean> viewPasengersByShip(String scheduleid);
}
