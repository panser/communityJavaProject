package ua.org.gostroy.communityJavaProject.general;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Panov Sergey on 10/1/2014.
 */
public class RegexTest {
    @Test
    public void testWordsSeparatedBySpacesCorrect(){

        String inputStringCorrect = "word wod2";

//		Pattern pattern = Pattern.compile("\\p{L}\\s+");
        Pattern pattern = Pattern.compile("[\\w+|\\s]");
        Matcher matcher = pattern.matcher(inputStringCorrect);

        boolean isValid = matcher.matches();

        assertFalse(isValid);
    }

    @Test
    public void testWordsSeparatedBySpacesFalse(){

        String inputStringFalse = "word words $; würzel";

//		Pattern pattern = Pattern.compile("\\p{L}\\s+");
        Pattern pattern = Pattern.compile("\\w+");
        Matcher matcher = pattern.matcher(inputStringFalse);

        boolean isValid = matcher.matches();

        assertFalse(isValid);
    }

    @Test
    public void testStringSplitFunction(){
        String[] parts = "this is a test; another test;halleluia".split(";");
        assertTrue(parts[0].equals("this is a test"));
        assertTrue(parts[1].equals(" another test"));
        assertTrue(parts[2].equals("halleluia"));
    }
}
