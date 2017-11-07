package edu.sjsu.thelaughingtribble.parkhere;

import org.junit.Test;

import java.lang.reflect.Field;

import edu.sjsu.thelaughingtribble.parkhere.models.pojo.User;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Alvin on 11/6/2017.
 */

public class User_ObjectUnitTest {

    // SETTER TESTS

    // userID
    @Test
    public void setUserID_ValidationTesting() throws Exception {
        final User userID = new User();

        userID.setUserID("Bob");

        final Field field = userID.getClass().getDeclaredField("userID");
        field.setAccessible(true);
        assertEquals("Field did not match", field.get(userID), "Bob");
    }

    // firstName
    @Test
    public void setFirstName_ValidationTesting() throws Exception {
        final User firstName = new User();

        firstName.setFirstName("555 something street");

        final Field field = firstName.getClass().getDeclaredField("firstName");
        field.setAccessible(true);
        assertEquals("Field did not match", field.get(firstName), "555 something street");
    }

    // lastName
    @Test
    public void setLastName_ValidationTesting() throws Exception {
        final User lastName = new User();

        lastName.setLastName("555 something street");

        final Field field = lastName.getClass().getDeclaredField("lastName");
        field.setAccessible(true);
        assertEquals("Field did not match", field.get(lastName), "555 something street");
    }

    // email
    @Test
    public void setEmail_ValidationTesting() throws Exception {
        final User email = new User();

        email.setEmail("555 something street");

        final Field field = email.getClass().getDeclaredField("email");
        field.setAccessible(true);
        assertEquals("Field did not match", field.get(email), "555 something street");
    }

    // cellphone
    @Test
    public void setCellphone_ValidationTesting() throws Exception {
        final User cellphone = new User();

        cellphone.setCellphone("555 something street");

        final Field field = cellphone.getClass().getDeclaredField("cellphone");
        field.setAccessible(true);
        assertEquals("Field did not match", field.get(cellphone), "555 something street");
    }

    // GETTER TESTS

    // userID
    @Test
    public void getUserID_ValidationTesting() throws Exception {
        final User userID = new User();
        final Field field = userID.getClass().getDeclaredField("uesrID");
        field.setAccessible(true);
        field.set(userID, "Bob");

        final String result = userID.getUserID();

        assertEquals("Field was not retrieved correctly and logic needs to be checked", result, "Bob");
    }

    // firstName
    @Test
    public void getFirstName_ValidationTesting() throws Exception {
        final User firstName = new User();
        final Field field = firstName.getClass().getDeclaredField("firstName");
        field.setAccessible(true);
        field.set(firstName, "555 something street");

        final String result = firstName.getFirstName();

        assertEquals("Field did not match", result, "555 something street");
    }

    // lastName
    @Test
    public void getLastName_ValidationTesting() throws Exception {
        final User lastName = new User();
        final Field field = lastName.getClass().getDeclaredField("lastName");
        field.setAccessible(true);
        field.set(lastName, "555 something street");

        final String result = lastName.getLastName();

        assertEquals("Field did not match", result, "555 something street");
    }

    // email
    @Test
    public void getEmail_ValidationTesting() throws Exception {
        final User email = new User();
        final Field field = email.getClass().getDeclaredField("email");
        field.setAccessible(true);
        field.set(email, "555 something street");

        final String result = email.getEmail();

        assertEquals("Field did not match", result, "555 something street");
    }

    // cellphone
    @Test
    public void getCellphone_ValidationTesting() throws Exception {
        final User cellphone = new User();
        final Field field = cellphone.getClass().getDeclaredField("cellphone");
        field.setAccessible(true);
        field.set(cellphone, "555 something street");

        final String result = cellphone.getCellphone();

        assertEquals("Field did not match", result, "555 something street");
    }

}
