package edu.sjsu.thelaughingtribble.parkhere.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jennifernghinguyen on 10/31/17.
 */

public final class Utilities {

    public static String getTodayDate(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MM, yyyy HH:mm");
        return dateFormat.format(new Date());
    }

    public static String usernameFromEmail(String email) {
        email = email.replace(" ", "");
        if (email.contains("@")) {
            return email.split("@")[0];
        } else {
            return email;
        }
    }
}
