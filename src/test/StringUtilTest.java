package src.test;

import com.google.appengine.repackaged.com.google.common.base.StringUtil;
import junit.framework.TestCase;

/**
 * Created by Zigzag on 09.07.2016.
 */
public class StringUtilTest extends TestCase {

    public void testIsEmptyOrWhitespace() {
        String s1 = "";
        String s2 = null;
        String s3 = " ";
        String s4 = "   ";//tab
        String s5 = "Hello";

        assertEquals(true, StringUtil.isEmptyOrWhitespace(s1));
        assertEquals(true, StringUtil.isEmptyOrWhitespace(s2));
        assertEquals(true, StringUtil.isEmptyOrWhitespace(s3));
        assertEquals(true, StringUtil.isEmptyOrWhitespace(s4));
        assertEquals(false, StringUtil.isEmptyOrWhitespace(s5));
    }
}
