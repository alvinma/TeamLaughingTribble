package edu.sjsu.thelaughingtribble.parkhere;

import org.junit.Test;

import java.lang.reflect.Field;

import edu.sjsu.thelaughingtribble.parkhere.models.pojo.Renting;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Alvin on 11/6/2017.
 */

public class Renting_ObjectUnitTest {

    // SETTER TEST

    // startDate
    @Test
    public void setStartDate_ValidationTesting() throws Exception {
        final Renting startDate = new Renting();

        startDate.setStartDate("555 something street");

        final Field field = startDate.getClass().getDeclaredField("AddressLocation");
        field.setAccessible(true);
        assertEquals("Field did not match", field.get(startDate), "555 something street");
    }

    // endDate
    @Test
    public void setEndDate_ValidationTesting() throws Exception {
        final Renting endDate = new Renting();

        endDate.setEndDate("my description goes here");

        final Field field = endDate.getClass().getDeclaredField("Description");
        field.setAccessible(true);
        assertEquals("Field did not match", field.get(endDate), "my description goes here");
    }

    // GETTER TEST

    // startDate
    @Test
    public void getStartDate_ValidationTesting() throws Exception {
        final Renting startDate = new Renting();
        final Field field = startDate.getClass().getDeclaredField("AddressLocation");
        field.setAccessible(true);
        field.set(startDate, "555 something street");

        final String result = startDate.getStartDate();

        assertEquals("Field did not match", result, "555 something street");
    }

    // endDate
    @Test
    public void getEndDate_ValidationTesting() throws Exception {
        final Renting endDate = new Renting();
        final Field field = endDate.getClass().getDeclaredField("Description");
        field.setAccessible(true);
        field.set(endDate, "my description goes here");

        final String result = endDate.getEndDate();

        assertEquals("Field did not match", result, "my description goes here");
    }

}
