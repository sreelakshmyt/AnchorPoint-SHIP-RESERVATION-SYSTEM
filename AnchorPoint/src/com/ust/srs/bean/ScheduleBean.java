package com.ust.srs.bean;

public class ScheduleBean {
	 private String scheduleID;
	    private String shipID;
	    private String routeID;
	    private String startDate;
		public String getScheduleID() {
			return scheduleID;
		}
		public void setScheduleID(String scheduleID) {
			this.scheduleID = scheduleID;
		}
		public String getShipID() {
			return shipID;
		}
		public void setShipID(String shipID) {
			this.shipID = shipID;
		}
		public String getRouteID() {
			return routeID;
		}
		public void setRouteID(String routeID) {
			this.routeID = routeID;
		}
		public String getStartDate() {
			return startDate;
		}
		public void setStartDate(String startDate) {
			this.startDate = startDate;
		}
		@Override
		public String toString() {
			return "ScheduleBean [scheduleID=" + scheduleID + ", shipID=" + shipID + ", routeID=" + routeID
					+ ", startDate=" + startDate + "]";
		} 

}
