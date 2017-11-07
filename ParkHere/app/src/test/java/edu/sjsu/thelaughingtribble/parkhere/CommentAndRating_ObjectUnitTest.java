package edu.sjsu.thelaughingtribble.parkhere;

import org.junit.Test;

import java.lang.reflect.Field;

import edu.sjsu.thelaughingtribble.parkhere.models.pojo.CommentAndRating;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Alvin on 11/4/2017.
 */

public class CommentAndRating_ObjectUnitTest {
    
    // Cannot do Spot spot setter and getter unit test as it calls for the use of the Spot object.
    // Not an independent unit test.
    
    
    // SETTER TESTS

    // grade
    @Test
    public void setGrade_ValidationTesting() throws Exception {
        final CommentAndRating grade = new CommentAndRating();

        grade.setGrade(1.0);

        final Field field = grade.getClass().getDeclaredField("grade");
        field.setAccessible(true);
        assertEquals("Field did not match", field.get(grade), 1.0);
    }

    // renter
    @Test
    public void setRenter_ValidationTesting() throws Exception {
        final CommentAndRating parkingOwnerName = new CommentAndRating();

        parkingOwnerName.setRenter("Bob");

        final Field field = parkingOwnerName.getClass().getDeclaredField("renter");
        field.setAccessible(true);
        assertEquals("Field did not match", field.get(parkingOwnerName), "Bob");
    }

    // comment
    @Test
    public void setComment_ValidationTesting() throws Exception {
        final CommentAndRating parkingAddress = new CommentAndRating();

        parkingAddress.setComment("555 something street");

        final Field field = parkingAddress.getClass().getDeclaredField("comment");
        field.setAccessible(true);
        assertEquals("Field did not match", field.get(parkingAddress), "555 something street");
    }

    // date
    @Test
    public void setDate_ValidationTesting() throws Exception {
        final CommentAndRating parkingDescription = new CommentAndRating();

        parkingDescription.setDate("my description goes here");

        final Field field = parkingDescription.getClass().getDeclaredField("date");
        field.setAccessible(true);
        assertEquals("Field did not match", field.get(parkingDescription), "my description goes here");
    }

    // GETTER TEST

    // grade
    @Test
    public void getGrade_ValidationTesting() throws Exception {
        final CommentAndRating grade = new CommentAndRating();
        final Field field = grade.getClass().getDeclaredField("grade");
        field.setAccessible(true);
        field.set(grade, 4.5);

        final double result = grade.getGrade();

        assertEquals("Field was not retrieved correcly and logic needs to be checked", result, 4.5);
    }

    // renter
    @Test
    public void getRenter_ValidationTesting() throws Exception {
        final CommentAndRating parkingOwnerName = new CommentAndRating();
        final Field field = parkingOwnerName.getClass().getDeclaredField("renter");
        field.setAccessible(true);
        field.set(parkingOwnerName, "Bob");

        final String result = parkingOwnerName.getRenter();

        assertEquals("Field was not retrieved correctly and logic needs to be checked", result, "Bob");
    }

    // comment
    @Test
    public void getComment_ValidationTesting() throws Exception {
        final CommentAndRating parkingAddress = new CommentAndRating();
        final Field field = parkingAddress.getClass().getDeclaredField("comment");
        field.setAccessible(true);
        field.set(parkingAddress, "555 something street");

        final String result = parkingAddress.getComment();

        assertEquals("Field did not match", result, "555 something street");
    }

    // date
    @Test
    public void getDate_ValidationTesting() throws Exception {
        final CommentAndRating parkingDescription = new CommentAndRating();
        final Field field = parkingDescription.getClass().getDeclaredField("date");
        field.setAccessible(true);
        field.set(parkingDescription, "my description goes here");

        final String result = parkingDescription.getDate();

        assertEquals("Field did not match", result, "my description goes here");
    }
    
}
