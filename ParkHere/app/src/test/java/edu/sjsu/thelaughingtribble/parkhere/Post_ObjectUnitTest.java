package edu.sjsu.thelaughingtribble.parkhere;

import org.junit.Test;

import java.lang.reflect.Field;

import edu.sjsu.thelaughingtribble.parkhere.models.pojo.Post;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Alvin on 11/6/2017.
 */

public class Post_ObjectUnitTest {

    // SETTER TESTS

    // totalGrade
    @Test
    public void setTotalGrade_ValidationTesting() throws Exception {
        final Post totalGrade = new Post();

        totalGrade.setTotalGrade(5.0);

        final Field field = totalGrade.getClass().getDeclaredField("totalGrade");
        field.setAccessible(true);
        assertEquals("Field did not match", field.get(totalGrade), 5.0);
    }

    // datePosted
    @Test
    public void setDatePosted_ValidationTesting() throws Exception {
        final Post datePosted = new Post();

        datePosted.setDatePosted("April 5, 2017");

        final Field field = datePosted.getClass().getDeclaredField("datePosted");
        field.setAccessible(true);
        assertEquals("Field did not match", field.get(datePosted), "April 5, 2017");
    }

    // title
    @Test
    public void setTitle_ValidationTesting() throws Exception {
        final Post title = new Post();

        title.setTitle("my parking spot");

        final Field field = title.getClass().getDeclaredField("title");
        field.setAccessible(true);
        assertEquals("Field did not match", field.get(title), "my parking spot");
    }


    // GETTER TEST

    // totalGrade
    @Test
    public void getTotalGrade_ValidationTesting() throws Exception {
        final Post totalGrade = new Post();
        final Field field = totalGrade.getClass().getDeclaredField("totalGrade");
        field.setAccessible(true);
        field.set(totalGrade, 4.5);

        final double result = totalGrade.getTotalGrade();

        assertEquals("Field was not retrieved correctly and logic needs to be checked", result, 4.5);
    }

    // datePosted
    @Test
    public void getDatePosted_ValidationTesting() throws Exception {
        final Post datePosted = new Post();
        final Field field = datePosted.getClass().getDeclaredField("datePosted");
        field.setAccessible(true);
        field.set(datePosted, "April 5, 2017");

        final String result = datePosted.getDatePosted();

        assertEquals("Field was not retrieved correctly and logic needs to be checked", result, "April 5, 2017");
    }

    // title
    @Test
    public void getTitle_ValidationTesting() throws Exception {
        final Post title = new Post();
        final Field field = title.getClass().getDeclaredField("title");
        field.setAccessible(true);
        field.set(title, "title");

        final String result = title.getTitle();

        assertEquals("Field did not match", result, "title");
    }

}
