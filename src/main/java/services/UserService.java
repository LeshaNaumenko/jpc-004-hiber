package services;

import dao.ActivityDao;
import dao.ReportDao;
import dao.UserDao;
import entities.Activity;
import entities.User;
import org.apache.log4j.Logger;

import java.util.List;

public class UserService implements CommonService {

    private static final Logger logger = Logger.getLogger(UserService.class);

    private UserDao userDao;
    private ActivityDao activityDao;
    private ReportDao reportDao;

    public UserService(UserDao userDao, ActivityDao activityDao, ReportDao reportDao) {
        this.userDao = userDao;
        this.activityDao = activityDao;
        this.reportDao = reportDao;
    }

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User findById(int id) {
        return userDao.findById(id);
    }

    public void save(User user) {
        userDao.save(user);
    }

    public User remove(User user) {
        return userDao.remove(user);
    }

    public List<User> findAll() {
        return userDao.findAll();
    }

    public double getTheSumOfActivityPrices(int id) {
        List<Activity> allActivityByUserId = userDao.findAllActivitiesByUserId(id);
        double theSumOfPrices = CommonService.getTheSumOfPrices(allActivityByUserId);
        logger.info("Getting the sum of activity prices for user with id : " + id + ". Sum:" + theSumOfPrices);
        return theSumOfPrices;
    }

}
