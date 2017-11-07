package edu.sjsu.thelaughingtribble.parkhere;

import org.junit.Test;

import java.lang.reflect.Field;

import edu.sjsu.thelaughingtribble.parkhere.models.pojo.Spot;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Alvin on 11/6/2017.
 */

public class Spot_ObjectUnitTest {

    // SETTER TESTS

    // type
    @Test
    public void setType_ValidationTesting() throws Exception {
        final Spot parkingOwnerName = new Spot();

        parkingOwnerName.setType("Bob");

        final Field field = parkingOwnerName.getClass().getDeclaredField("OwnerName");
        field.setAccessible(true);
        assertEquals("Field did not match", field.get(parkingOwnerName), "Bob");
    }

    // description
    @Test
    public void setDescription_ValidationTesting() throws Exception {
        final Spot parkingAddress = new Spot();

        parkingAddress.setDescription("555 something street");

        final Field field = parkingAddress.getClass().getDeclaredField("AddressLocation");
        field.setAccessible(true);
        assertEquals("Field did not match", field.get(parkingAddress), "555 something street");
    }

    // price
    @Test
    public void setPrice_ValidationTesting() throws Exception {
        final Spot grade = new Spot();

        grade.setPrice(1.0);

        final Field field = grade.getClass().getDeclaredField("ObjectID");
        field.setAccessible(true);
        assertEquals("Field did not match", field.get(grade), 1.0);
    }

    // permitRequired
    @Test
    public void setPermitRequired_ValidationTesting() throws Exception {
        final Spot grade = new Spot();

        grade.setPermitRequired(true);

        final Field field = grade.getClass().getDeclaredField("ObjectID");
        field.setAccessible(true);
        assertEquals("Field did not match", field.get(grade), 1.0);
    }
    // spotNumber
    @Test
    public void setSpotNumber_ValidationTesting() throws Exception {
        final Spot parkingOwnerName = new Spot();

        parkingOwnerName.setSpotNumber("Bob");

        final Field field = parkingOwnerName.getClass().getDeclaredField("OwnerName");
        field.setAccessible(true);
        assertEquals("Field did not match", field.get(parkingOwnerName), "Bob");
    }

    // GETTER TESTS

    // type
    @Test
    public void getType_ValidationTesting() throws Exception {
        final Spot parkingOwnerName = new Spot();
        final Field field = parkingOwnerName.getClass().getDeclaredField("OwnerName");
        field.setAccessible(true);
        field.set(parkingOwnerName, "Bob");

        final String result = parkingOwnerName.getType();

        assertEquals("Field was not retrieved correctly and logic needs to be checked", result, "Bob");
    }

    // description
    @Test
    public void getDescription_ValidationTesting() throws Exception {
        final Spot parkingAddress = new Spot();
        final Field field = parkingAddress.getClass().getDeclaredField("AddressLocation");
        field.setAccessible(true);
        field.set(parkingAddress, "555 something street");

        final String result = parkingAddress.getDescription();

        assertEquals("Field did not match", result, "555 something street");
    }

    // price
    @Test
    public void getPrice_ValidationTesting() throws Exception {
        final Spot grade = new Spot();
        final Field field = grade.getClass().getDeclaredField("ObjectID");
        field.setAccessible(true);
        field.set(grade, 4.5);

        final double result = grade.getPrice();

        assertEquals("Field was not retrieved correcly and logic needs to be checked", result, 4.5);
    }

    // permitRequired
    @Test
    public void getPermitRequired_ValidationTesting() throws Exception {
        final Spot grade = new Spot();
        final Field field = grade.getClass().getDeclaredField("ObjectID");
        field.setAccessible(true);
        field.set(grade, true);

        final boolean result = grade.getPermitRequired();

        assertEquals("Field was not retrieved correcly and logic needs to be checked", result, 4.5);
    }

    // spotNumber
    @Test
    public void getSpotNumber_ValidationTesting() throws Exception {
        final Spot parkingAddress = new Spot();
        final Field field = parkingAddress.getClass().getDeclaredField("AddressLocation");
        field.setAccessible(true);
        field.set(parkingAddress, "555 something street");

        final String result = parkingAddress.getSpotNumber();

        assertEquals("Field did not match", result, "555 something street");
    }

}
