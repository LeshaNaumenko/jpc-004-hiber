package services;

import dao.ActivityDao;
import entities.Activity;

import java.util.List;

public class ActivityService implements CommonService{

    private ActivityDao activityDao;

    public ActivityService(ActivityDao activityDao) {
        this.activityDao = activityDao;
    }

/*    public List<Activity> findAllActivityByUserId(Integer id) {
        return activityDao.findAllActivityByUserId(id);
    }*/

    public List<Activity> findAllActivityByBuildingId(Integer id) {
        return activityDao.findAllActivityByBuildingId(id);
    }
}
