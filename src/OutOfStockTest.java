import org.junit.Test;
import org.junit.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;

import java.util.ArrayList;

public class OutOfStockTest {

    private Customer customer;

    @Before
    public void setUp() {
        Menu.sample_values();
        customer = new Customer("test", "test");
    }

    @Test
    public void test_out_of_stock() throws CustomException {
        Assert.assertEquals(customer.add_item(1, 1000), false);
    }

    @Test
    public void test_in_stock() throws CustomException {
        Assert.assertEquals(customer.add_item(1, 10), true);
    }

    @Test
    public void test_wrong_id() throws CustomException {
        Assert.assertEquals(customer.add_item(1000, 1), false);
    }
}
