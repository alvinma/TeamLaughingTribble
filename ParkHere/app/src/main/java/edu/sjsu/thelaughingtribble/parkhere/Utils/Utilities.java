package edu.sjsu.thelaughingtribble.parkhere.Utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jennifernghinguyen on 10/31/17.
 */

public final class Utilities {

    public static String getTodayDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MM, yyyy HH:mm");
        return dateFormat.format(new Date());
    }

    //Converts the date from "StringFormat" to "Date" objects
    public static Date convertStringDate(String dateString){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MM, yyyy HH:mm");
        Date date;
        try {
            date = dateFormat.parse(dateString);
        }catch (Exception ex){
            date = new Date();
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            date = c.getTime();
            System.out.print("Handled EXCEPTION");
        }
        return date;
    }
    public static String getNextDateAvailable() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MM, yyyy HH:mm");
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, 1);
        date = c.getTime();
        return dateFormat.format(date);
    }
    public static String setDateExpired(int days) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MM, yyyy HH:mm");
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, days);
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

    public static boolean phoneMatcher(String phoneNumber) {
        boolean status = false;
        Pattern pattern = Pattern.compile("\\d{3}-\\d{3}-\\d{4}");
        Matcher matcher = pattern.matcher(phoneNumber);
        if (matcher.matches()) {
            status = true;
        }
        return status;
    }
}
