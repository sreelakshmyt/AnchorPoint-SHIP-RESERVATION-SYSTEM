package com.ust.srs.ui;
	import com.ust.srs.dao.AdministratorDao;
	import com.ust.srs.bean.ShipBean;

	public class Main {
	    public static void main(String[] args) {
	        AdministratorDao admin = new AdministratorDao();
	        admin.addShip(new ShipBean());      // add new ship
	        admin.viewByAllShips();             // view all
	    }
	

}
