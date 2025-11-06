package com.ust.srs.util;

import com.ust.srs.bean.CredentialsBean;

public class AuthUtil {
    public static String login(String userID, String password) {
        CredentialsBean user = DataStore.credentials.get(userID);
        if (user != null && user.getPassword().equals(password)) {
            return user.getUserType(); // "A" = admin, "C" = customer
        }
        return "INVALID";
    }
}
