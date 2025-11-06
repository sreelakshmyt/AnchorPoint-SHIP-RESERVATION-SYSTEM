package com.ust.srs.ui;

import javax.swing.JOptionPane;
import java.util.*;
import com.ust.srs.util.*;
import com.ust.srs.dao.*;
import com.ust.srs.bean.*;

public class Main {
    public static void main(String[] args) {
        while (true) {
            String user = JOptionPane.showInputDialog("Enter UserID:");
            String pass = JOptionPane.showInputDialog("Enter Password:");

            String role = AuthUtil.login(user, pass);
            if (role.equals("A")) {
                adminMenu();
            } else if (role.equals("C")) {
                customerMenu(user);
            } else {
                JOptionPane.showMessageDialog(null, "Invalid Credentials!");
            }
        }
    }

    private static void adminMenu() {
        AdministratorDao dao = new AdministratorDao();
        while (true) {
            String choice = JOptionPane.showInputDialog(
                    "Admin Menu:\n1. Add Ship\n2. Modify Ship\n3. Add Route\n4. Modify Route\n5. Add Schedule\n6. View Ships\n7. View Routes\n8. View Passengers\n9. Logout");
            switch (choice) {
                case "1": dao.addShip(new ShipBean()); break;
                case "2": dao.modifyShip(new ShipBean()); break;
                case "3": dao.addRoute(new RouteBean()); break;
                case "4": dao.modifyRoute(new RouteBean()); break;
                case "5": dao.addSchedule(new ScheduleBean()); break;
                case "6": JOptionPane.showMessageDialog(null, dao.viewByAllShips()); break;
                case "7": JOptionPane.showMessageDialog(null, dao.viewByAllRoute()); break;
                case "8":
                    String schId = JOptionPane.showInputDialog("Enter Schedule ID:");
                    JOptionPane.showMessageDialog(null, dao.viewPasengersByShip(schId));
                    break;
                case "9": return;
                default: JOptionPane.showMessageDialog(null, "Invalid Option!");
            }
        }
    }

    private static void customerMenu(String userId) {
        CustomerDao dao = new CustomerDao();

        while (true) {
            String choice = JOptionPane.showInputDialog(
                    "Customer Menu:\n1. View Schedule\n2. Book Ticket\n3. View Ticket\n4. Cancel Ticket\n5. Logout");
            switch (choice) {
                case "1":
                    String src = JOptionPane.showInputDialog("Enter Source:");
                    String dest = JOptionPane.showInputDialog("Enter Destination:");
                    dao.viewScheduleByRoute(src, dest, null);
                    break;

                case "2":
                    String scheduleId = JOptionPane.showInputDialog("Enter Schedule ID:");
                    int numPassengers = Integer.parseInt(JOptionPane.showInputDialog("How many passengers?"));
                    ArrayList<PassengerBean> plist = new ArrayList<>();
                    for (int i = 0; i < numPassengers; i++) {
                        PassengerBean p = new PassengerBean();
                        p.setName(JOptionPane.showInputDialog("Passenger " + (i + 1) + " Name:"));
                        p.setAge(Integer.parseInt(JOptionPane.showInputDialog("Age:")));
                        p.setGender(JOptionPane.showInputDialog("Gender:"));
                        plist.add(p);
                    }
                    ReservationBean res = new ReservationBean();
                    res.setUserID(userId);
                    res.setScheduleID(scheduleId);
                    dao.reserveTicket(res, plist);
                    break;

                case "3":
                    String rid = JOptionPane.showInputDialog("Enter Reservation ID:");
                    dao.viewTicket(rid);
                    break;

                case "4":
                    String rid2 = JOptionPane.showInputDialog("Enter Reservation ID to cancel:");
                    dao.cancelTicket(rid2);
                    break;

                case "5":
                    return;

                default:
                    JOptionPane.showMessageDialog(null, "Invalid choice!");
            }
        }
    }

}


