package edu.sjsu.thelaughingtribble.parkhere;

import org.junit.Test;

import java.lang.reflect.Field;

import edu.sjsu.thelaughingtribble.parkhere.models.pojo.Vehicle;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Alvin on 11/6/2017.
 */

public class Vehicle_ObjectUnitTest {

    // SETTER TESTS

    // vin
    @Test
    public void setVin_ValidationTesting() throws Exception {
        final Vehicle vin = new Vehicle();

        vin.setVin("Bob");

        final Field field = vin.getClass().getDeclaredField("vin");
        field.setAccessible(true);
        assertEquals("Field did not match", field.get(vin), "Bob");
    }

    // brand
    @Test
    public void setBrand_ValidationTesting() throws Exception {
        final Vehicle brand = new Vehicle();

        brand.setBrand("555 something street");

        final Field field = brand.getClass().getDeclaredField("brand");
        field.setAccessible(true);
        assertEquals("Field did not match", field.get(brand), "555 something street");
    }

    // make
    @Test
    public void setMake_ValidationTesting() throws Exception {
        final Vehicle make = new Vehicle();

        make.setMake("555 something street");

        final Field field = make.getClass().getDeclaredField("make");
        field.setAccessible(true);
        assertEquals("Field did not match", field.get(make), "555 something street");
    }

    // year
    @Test
    public void setYear_ValidationTesting() throws Exception {
        final Vehicle year = new Vehicle();

        year.setYear("555 something street");

        final Field field = year.getClass().getDeclaredField("year");
        field.setAccessible(true);
        assertEquals("Field did not match", field.get(year), "555 something street");
    }

    // plateNumber
    @Test
    public void setPlateNumber_ValidationTesting() throws Exception {
        final Vehicle plateNumber = new Vehicle();

        plateNumber.setPlateNumber("555 something street");

        final Field field = plateNumber.getClass().getDeclaredField("plateNumber");
        field.setAccessible(true);
        assertEquals("Field did not match", field.get(plateNumber), "555 something street");
    }

    // GETTER TESTS

    // vin
    @Test
    public void getVin_ValidationTesting() throws Exception {
        final Vehicle vin = new Vehicle();
        final Field field = vin.getClass().getDeclaredField("vin");
        field.setAccessible(true);
        field.set(vin, "Bob");

        final String result = vin.getVin();

        assertEquals("Field was not retrieved correctly and logic needs to be checked", result, "Bob");
    }

    // brand
    @Test
    public void getBrand_ValidationTesting() throws Exception {
        final Vehicle brand = new Vehicle();
        final Field field = brand.getClass().getDeclaredField("brand");
        field.setAccessible(true);
        field.set(brand, "555 something street");

        final String result = brand.getBrand();

        assertEquals("Field did not match", result, "555 something street");
    }

    // make
    @Test
    public void getMake_ValidationTesting() throws Exception {
        final Vehicle make = new Vehicle();
        final Field field = make.getClass().getDeclaredField("make");
        field.setAccessible(true);
        field.set(make, "555 something street");

        final String result = make.getMake();

        assertEquals("Field did not match", result, "555 something street");
    }

    // year
    @Test
    public void getYear_ValidationTesting() throws Exception {
        final Vehicle year = new Vehicle();
        final Field field = year.getClass().getDeclaredField("year");
        field.setAccessible(true);
        field.set(year, "555 something street");

        final String result = year.getYear();

        assertEquals("Field did not match", result, "555 something street");
    }

    // plateNumber
    @Test
    public void getPlateNumber_ValidationTesting() throws Exception {
        final Vehicle plateNumber = new Vehicle();
        final Field field = plateNumber.getClass().getDeclaredField("plateNumber");
        field.setAccessible(true);
        field.set(plateNumber, "555 something street");

        final String result = plateNumber.getPlateNumber();

        assertEquals("Field did not match", result, "555 something street");
    }

}
