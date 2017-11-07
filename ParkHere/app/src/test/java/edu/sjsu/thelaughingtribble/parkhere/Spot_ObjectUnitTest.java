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
        final Spot type = new Spot();

        type.setType("Bob");

        final Field field = type.getClass().getDeclaredField("type");
        field.setAccessible(true);
        assertEquals("Field did not match", field.get(type), "Bob");
    }

    // description
    @Test
    public void setDescription_ValidationTesting() throws Exception {
        final Spot description = new Spot();

        description.setDescription("555 something street");

        final Field field = description.getClass().getDeclaredField("description");
        field.setAccessible(true);
        assertEquals("Field did not match", field.get(description), "555 something street");
    }

    // price
    @Test
    public void setPrice_ValidationTesting() throws Exception {
        final Spot price = new Spot();

        price.setPrice(1.0);

        final Field field = price.getClass().getDeclaredField("price");
        field.setAccessible(true);
        assertEquals("Field did not match", field.get(price), 1.0);
    }

    // permitRequired
    @Test
    public void setPermitRequired_ValidationTesting() throws Exception {
        final Spot permitRequired = new Spot();

        permitRequired.setPermitRequired("true");

        final Field field = permitRequired.getClass().getDeclaredField("permitRequired");
        field.setAccessible(true);
        assertEquals("Field did not match", field.get(permitRequired), "true");
    }
    // spotNumber
    @Test
    public void setSpotNumber_ValidationTesting() throws Exception {
        final Spot spotNumber = new Spot();

        spotNumber.setSpotNumber("Bob");

        final Field field = spotNumber.getClass().getDeclaredField("spotNumber");
        field.setAccessible(true);
        assertEquals("Field did not match", field.get(spotNumber), "Bob");
    }

    // GETTER TESTS

    // type
    @Test
    public void getType_ValidationTesting() throws Exception {
        final Spot type = new Spot();
        final Field field = type.getClass().getDeclaredField("type");
        field.setAccessible(true);
        field.set(type, "Bob");

        final String result = type.getType();

        assertEquals("Field was not retrieved correctly and logic needs to be checked", result, "Bob");
    }

    // description
    @Test
    public void getDescription_ValidationTesting() throws Exception {
        final Spot description = new Spot();
        final Field field = description.getClass().getDeclaredField("description");
        field.setAccessible(true);
        field.set(description, "555 something street");

        final String result = description.getDescription();

        assertEquals("Field did not match", result, "555 something street");
    }

    // price
    @Test
    public void getPrice_ValidationTesting() throws Exception {
        final Spot price = new Spot();
        final Field field = price.getClass().getDeclaredField("price");
        field.setAccessible(true);
        field.set(price, 4.5);

        final double result = price.getPrice();

        assertEquals("Field was not retrieved correctly and logic needs to be checked", result, 4.5);
    }

    // permitRequired
    @Test
    public void getPermitRequired_ValidationTesting() throws Exception {
        final Spot permitRequired = new Spot();
        final Field field = permitRequired.getClass().getDeclaredField("permitRequired");
        field.setAccessible(true);
        field.set(permitRequired, "true");

        final String result = permitRequired.getPermitRequired();

        assertEquals("Field was not retrieved correctly and logic needs to be checked", result, "true");
    }

    // description
    @Test
    public void getSpotNumber_ValidationTesting() throws Exception {
        final Spot spotNumber = new Spot();
        final Field field = spotNumber.getClass().getDeclaredField("spotNumber");
        field.setAccessible(true);
        field.set(spotNumber, "555 something street");

        final String result = spotNumber.getSpotNumber();

        assertEquals("Field did not match", result, "555 something street");
    }

}
