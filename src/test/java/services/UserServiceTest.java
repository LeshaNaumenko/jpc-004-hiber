package services;

import dao.UserDao;
import entities.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest extends BaseTestClass{

    @Test
    void getTheSumOfActivityPricesForUser(){
        UserDao userDao = new UserDao();
        UserService userService = new UserService(userDao);

        User user = populate();
        userService.save(user);

        //do
        double theSumOfActivityPrices = userService.getTheSumOfActivityPrices(1);

        //assert
        assertEquals(9230.0, theSumOfActivityPrices);
    }

}