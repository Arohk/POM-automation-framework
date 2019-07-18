package tests;

import org.testng.annotations.Test;

public class OrdersTestSuite extends BaseTest {

    @Test
    public void orderTest() throws InterruptedException {
        objHomePage.findRestaurant();
        objRestaurantPage.orderFromRestaurant();
        objAddressPage.completeOrder();
    }

}
