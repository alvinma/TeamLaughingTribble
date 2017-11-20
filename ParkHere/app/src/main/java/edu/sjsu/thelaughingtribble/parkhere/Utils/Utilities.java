package edu.sjsu.thelaughingtribble.parkhere.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Calendar;

/**
 * Created by jennifernghinguyen on 10/31/17.
 */

public final class Utilities {

    public static String getTodayDate(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MM, yyyy HH:mm");
        return dateFormat.format(new Date());
    }

    public static String getNextDateAvailable(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MM, yyyy HH:mm");
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, 1);
        date = c.getTime();
        return dateFormat.format(date);
    }

    public static String usernameFromEmail(String email) {
        email = email.replace(" ", "");
        if (email.contains("@")) {
            return email.split("@")[0];
        } else {
            return email;
        }
    }

    public static boolean phoneMatcher(String phoneNumber){
        boolean status = false;
        Pattern pattern = Pattern.compile("\\d{3}-\\d{3}-\\d{4}");
        Matcher matcher = pattern.matcher(phoneNumber);
        if (matcher.matches()) {
           status = true;
        }
        return status;
    }
}
