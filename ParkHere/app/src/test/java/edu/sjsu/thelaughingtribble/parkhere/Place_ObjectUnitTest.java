package edu.sjsu.thelaughingtribble.parkhere;

import org.junit.Test;

import java.lang.reflect.Field;

import edu.sjsu.thelaughingtribble.parkhere.models.pojo.Place;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Alvin on 11/6/2017.
 */

public class Place_ObjectUnitTest {

    // SETTER TEST

    // address
    @Test
    public void setAddress_ValidationTesting() throws Exception {
        final Place parkingAddress = new Place();

        parkingAddress.setAddress("555 something street");

        final Field field = parkingAddress.getClass().getDeclaredField("address");
        field.setAccessible(true);
        assertEquals("Field did not match", field.get(parkingAddress), "555 something street");
    }

    // GETTER TEST

    // address
    @Test
    public void getAddress_ValidationTesting() throws Exception {
        final Place parkingAddress = new Place();
        final Field field = parkingAddress.getClass().getDeclaredField("address");
        field.setAccessible(true);
        field.set(parkingAddress, "555 something street");

        final String result = parkingAddress.getAddress();

        assertEquals("Field was not retrieved correctly and logic needs to be checked", result, "555 something street");
    }

}
