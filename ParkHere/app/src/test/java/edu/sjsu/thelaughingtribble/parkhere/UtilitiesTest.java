package edu.sjsu.thelaughingtribble.parkhere;

import org.junit.Test;

import edu.sjsu.thelaughingtribble.parkhere.Utils.Utilities;

import static edu.sjsu.thelaughingtribble.parkhere.Utils.Utilities.usernameFromEmail;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by jennifernghinguyen on 11/6/17.
 */
public class UtilitiesTest {

    /*
     extract username from email
     */
    @Test
    public void usernameFromEmail_isCorrect() throws Exception {
        String email = "laughingtribble@gmail.com";
        String golden = "laughingtribble";
        assertTrue(usernameFromEmail(email).equals(golden));
    }

    @Test
    public void usernameFromEmailWithSpace_isCorrect() throws Exception {
        String email = "laughingtribble   @gmail.com";
        String golden = "laughingtribble";
        assertTrue(usernameFromEmail(email).equals(golden));
    }

    @Test
    public void testInValidPhoneNumber() throws Exception{
        String phone = "4081239992";
        assertFalse(Utilities.phoneMatcher(phone));
    }

    @Test
    public void testValidPhoneNumber() throws Exception{
        String phone = "408-123-9992";
        assertTrue(Utilities.phoneMatcher(phone));
    }


}
