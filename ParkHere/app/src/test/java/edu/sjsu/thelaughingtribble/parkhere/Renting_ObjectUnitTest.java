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

        startDate.setStartDate("April 5, 2017");

        final Field field = startDate.getClass().getDeclaredField("startDate");
        field.setAccessible(true);
        assertEquals("Field did not match", field.get(startDate), "April 5, 2017");
    }

    // endDate
    @Test
    public void setEndDate_ValidationTesting() throws Exception {
        final Renting endDate = new Renting();

        endDate.setEndDate("April 6, 2017");

        final Field field = endDate.getClass().getDeclaredField("endDate");
        field.setAccessible(true);
        assertEquals("Field did not match", field.get(endDate), "April 6, 2017");
    }

    // GETTER TEST

    // startDate
    @Test
    public void getStartDate_ValidationTesting() throws Exception {
        final Renting startDate = new Renting();
        final Field field = startDate.getClass().getDeclaredField("startDate");
        field.setAccessible(true);
        field.set(startDate, "April 7, 2017");

        final String result = startDate.getStartDate();

        assertEquals("Field did not match", result, "April 7, 2017");
    }

    // endDate
    @Test
    public void getEndDate_ValidationTesting() throws Exception {
        final Renting endDate = new Renting();
        final Field field = endDate.getClass().getDeclaredField("endDate");
        field.setAccessible(true);
        field.set(endDate, "July 5, 2017");

        final String result = endDate.getEndDate();

        assertEquals("Field did not match", result, "July 5, 2017");
    }

}
