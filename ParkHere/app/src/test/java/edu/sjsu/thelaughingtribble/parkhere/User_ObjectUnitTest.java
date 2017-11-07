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

        userID.setUserID("999");

        final Field field = userID.getClass().getDeclaredField("userID");
        field.setAccessible(true);
        assertEquals("Field did not match", field.get(userID), "999");
    }

    // firstName
    @Test
    public void setFirstName_ValidationTesting() throws Exception {
        final User firstName = new User();

        firstName.setFirstName("Bob");

        final Field field = firstName.getClass().getDeclaredField("firstName");
        field.setAccessible(true);
        assertEquals("Field did not match", field.get(firstName), "Bob");
    }

    // lastName
    @Test
    public void setLastName_ValidationTesting() throws Exception {
        final User lastName = new User();

        lastName.setLastName("Jackson");

        final Field field = lastName.getClass().getDeclaredField("lastName");
        field.setAccessible(true);
        assertEquals("Field did not match", field.get(lastName), "Jackson");
    }

    // email
    @Test
    public void setEmail_ValidationTesting() throws Exception {
        final User email = new User();

        email.setEmail("park@here.com");

        final Field field = email.getClass().getDeclaredField("email");
        field.setAccessible(true);
        assertEquals("Field did not match", field.get(email), "park@here.com");
    }

    // cellphone
    @Test
    public void setCellphone_ValidationTesting() throws Exception {
        final User cellphone = new User();

        cellphone.setCellphone("555-555-9999");

        final Field field = cellphone.getClass().getDeclaredField("cellphone");
        field.setAccessible(true);
        assertEquals("Field did not match", field.get(cellphone), "555-555-9999");
    }

    // GETTER TESTS

    // userID
    @Test
    public void getUserID_ValidationTesting() throws Exception {
        final User userID = new User();
        final Field field = userID.getClass().getDeclaredField("uesrID");
        field.setAccessible(true);
        field.set(userID, "9");

        final String result = userID.getUserID();

        assertEquals("Field was not retrieved correctly and logic needs to be checked", result, "9");
    }

    // firstName
    @Test
    public void getFirstName_ValidationTesting() throws Exception {
        final User firstName = new User();
        final Field field = firstName.getClass().getDeclaredField("firstName");
        field.setAccessible(true);
        field.set(firstName, "Hello");

        final String result = firstName.getFirstName();

        assertEquals("Field did not match", result, "Hello");
    }

    // lastName
    @Test
    public void getLastName_ValidationTesting() throws Exception {
        final User lastName = new User();
        final Field field = lastName.getClass().getDeclaredField("lastName");
        field.setAccessible(true);
        field.set(lastName, "World");

        final String result = lastName.getLastName();

        assertEquals("Field did not match", result, "World");
    }

    // email
    @Test
    public void getEmail_ValidationTesting() throws Exception {
        final User email = new User();
        final Field field = email.getClass().getDeclaredField("email");
        field.setAccessible(true);
        field.set(email, "e@mail.com");

        final String result = email.getEmail();

        assertEquals("Field did not match", result, "e@mail.com");
    }

    // cellphone
    @Test
    public void getCellphone_ValidationTesting() throws Exception {
        final User cellphone = new User();
        final Field field = cellphone.getClass().getDeclaredField("cellphone");
        field.setAccessible(true);
        field.set(cellphone, "555-555-9999");

        final String result = cellphone.getCellphone();

        assertEquals("Field did not match", result, "555-555-9999");
    }

}
