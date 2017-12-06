package edu.sjsu.thelaughingtribble.parkhere;

import org.junit.Test;

import java.lang.reflect.Field;

import static junit.framework.Assert.assertEquals;


/**
 * Created by Alvin on 11/4/2017.
 */

public class ParkingPost_ObjectUnitTest {

    // SETTER TESTS

    // Object ID
    @Test
    public void setObjectID_ValidationTesting() throws Exception {
        final ParkingPostObject parkingObj = new ParkingPostObject();

        parkingObj.setObjectID(1);

        final Field field = parkingObj.getClass().getDeclaredField("ObjectID");
        field.setAccessible(true);
        assertEquals("Field did not match", field.get(parkingObj), 1);
    }

    // Owner Name
    @Test
    public void setOwnerName_ValidationTesting() throws Exception {
        final ParkingPostObject parkingOwnerName = new ParkingPostObject();

        parkingOwnerName.setOwnerName("Bob");

        final Field field = parkingOwnerName.getClass().getDeclaredField("OwnerName");
        field.setAccessible(true);
        assertEquals("Field did not match", field.get(parkingOwnerName), "Bob");
    }

    // Address Location
    @Test
    public void setAddressLocation_ValidationTesting() throws Exception {
        final ParkingPostObject parkingAddress = new ParkingPostObject();

        parkingAddress.setAddressLocation("555 something street");

        final Field field = parkingAddress.getClass().getDeclaredField("AddressLocation");
        field.setAccessible(true);
        assertEquals("Field did not match", field.get(parkingAddress), "555 something street");
    }

    // Description
    @Test
    public void setDescription_ValidationTesting() throws Exception {
        final ParkingPostObject parkingDescription = new ParkingPostObject();

        parkingDescription.setDescription("my description goes here");

        final Field field = parkingDescription.getClass().getDeclaredField("Description");
        field.setAccessible(true);
        assertEquals("Field did not match", field.get(parkingDescription), "my description goes here");
    }

    // GETTER TESTS

    // Object ID
    @Test
    public void getObjectID_ValidationTesting() throws Exception {
        final ParkingPostObject parkingObj = new ParkingPostObject();
        final Field field = parkingObj.getClass().getDeclaredField("ObjectID");
        field.setAccessible(true);
        field.set(parkingObj, 123);

        final int result = parkingObj.getObjectID();

        assertEquals("Field was not retrieved correcly and logic needs to be checked", result, 123);
    }

    // Owner Name
    @Test
    public void getOwnerName_ValidationTesting() throws Exception {
        final ParkingPostObject parkingOwnerName = new ParkingPostObject();
        final Field field = parkingOwnerName.getClass().getDeclaredField("OwnerName");
        field.setAccessible(true);
        field.set(parkingOwnerName, "Bob");

        final String result = parkingOwnerName.getOwnerName();

        assertEquals("Field was not retrieved correctly and logic needs to be checked", result, "Bob");
    }

    // Address Location
    @Test
    public void getAddressLocation_ValidationTesting() throws Exception {
        final ParkingPostObject parkingAddress = new ParkingPostObject();
        final Field field = parkingAddress.getClass().getDeclaredField("AddressLocation");
        field.setAccessible(true);
        field.set(parkingAddress, "555 something street");

        final String result = parkingAddress.getAddressLocation();

        assertEquals("Field did not match", result, "555 something street");
    }

    // Description
    @Test
    public void getDescription_ValidationTesting() throws Exception {
        final ParkingPostObject parkingDescription = new ParkingPostObject();
        final Field field = parkingDescription.getClass().getDeclaredField("Description");
        field.setAccessible(true);
        field.set(parkingDescription, "my description goes here");

        final String result = parkingDescription.getDescription();

        assertEquals("Field did not match", result, "my description goes here");
    }
}
