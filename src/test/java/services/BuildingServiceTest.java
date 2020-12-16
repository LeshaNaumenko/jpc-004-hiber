package services;

import dao.ActivityDao;
import dao.BuildingDao;
import dao.UserDao;
import entities.Building;
import entities.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BuildingServiceTest extends BaseTestClass {

    @Test
    void setNonActiveStatePriceOfAllActivitiesIsMoreThanSpecifiedValue() {
        //populate
        UserDao userDao = new UserDao();
        UserService userService = new UserService(userDao);
        BuildingService buildingService = new BuildingService(new BuildingDao());
        User user = populate();

        userService.save(user);

        //do
        buildingService.recalculateTheTotalPrice(5000, 4);

        //assert
        Building building = buildingService.findById(4);
        assertFalse(building.isActive());
    }

    @Test
    void setActiveStatePriceOfAllActivitiesIsLessThanSpecifiedValue() {

        //populate
        UserDao userDao = new UserDao();
        UserService userService = new UserService(userDao);
        BuildingService buildingService = new BuildingService(new BuildingDao());
        User user = populate();

        userService.save(user);

        //do
        buildingService.recalculateTheTotalPrice(6000, 4);

        //assert
        Building building = buildingService.findById(4);
        assertTrue(building.isActive());
    }

    @Test
    void getTheSumOfActivityPricesForBuilding(){
        UserDao userDao = new UserDao();
        UserService userService = new UserService(userDao);

        User user = populate();
        userService.save(user);
        BuildingService buildingService = new BuildingService(new BuildingDao(), new ActivityDao());
        double buildingTheSumOfActivityPrices = buildingService.getTheSumOfActivityPrices(4);
        assertEquals(5300.0, buildingTheSumOfActivityPrices);
    }
}