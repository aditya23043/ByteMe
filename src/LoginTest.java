import org.junit.Test;
import org.junit.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;

import java.util.ArrayList;

public class LoginTest {

    private Credentials credentials;

    @Before
    public void setUp() {
        credentials = new Credentials();
    }

    @Test
    public void testInvalidUsername() {
        Assert.assertEquals(credentials.authenticate("some_user", "something"), false);
    }

    @Test
    public void testInvalidPassword() {
        Assert.assertEquals(credentials.authenticate("adi", "some_password"), false);
    }

    @Test
    public void testNonExistentUser() {
        Assert.assertEquals(credentials.authenticate("someone", "idk"), false);
    }

    @Test
    public void testEmptyPassword() {
        Assert.assertEquals(credentials.authenticate("adi", ""), false);
    }

    @Test
    public void testEmptyUsername() {
        Assert.assertEquals(credentials.authenticate("", "something"), false);
    }

    @Test
    public void testBothEmpty() {
        Assert.assertEquals(credentials.authenticate("", ""), false);
    }
}
