package services;

import dao.ActivityDao;
import dao.UserDao;
import entities.Activity;
import entities.Building;
import entities.Report;
import entities.User;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ActivityServiceTest extends BaseTestClass {

    @Test
    void findAllActivityByBuildingIdAndUserId() {

        UserDao userDao = new UserDao();
        UserService userService = new UserService(userDao);

        User user = populate();
        userService.save(user);

        //do
        ActivityService activityService = new ActivityService(new ActivityDao());
        List<Activity> allActivityByBuildingIdAndUserId = activityService.findAllActivityByUserIdAndBuildingId(1, 4);

        //expectedActivityNameList has no IDs, so only compare names
        List<String> expectedActivityNameList = user.getReports().get(1)
                .getBuildings().get(1)
                .getActivities().stream()
                .map(Activity::getWorkName)
                .collect(Collectors.toList());
        List<String> actualActivityNameList = allActivityByBuildingIdAndUserId.stream().map(Activity::getWorkName).collect(Collectors.toList());

        //assert
        assertTrue(actualActivityNameList.size() == expectedActivityNameList.size() && actualActivityNameList.containsAll(expectedActivityNameList) && expectedActivityNameList.containsAll(actualActivityNameList));
    }


}