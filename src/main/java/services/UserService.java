package services;

import dao.ActivityDao;
import dao.ReportDao;
import dao.UserDao;
import entities.Activity;
import entities.Report;
import entities.User;
import utils.MyHibernateSessionFactoryUtil;

import java.util.List;

public class UserService implements CommonService{

    private UserDao userDao;
    private ActivityDao activityDao;
    private ReportDao reportDao;

    public UserService(UserDao userDao, ActivityDao activityDao, ReportDao reportDao) {
        this.userDao = userDao;
        this.activityDao = activityDao;
        this.reportDao = reportDao;
    }

    public User findById(int id) {
        return userDao.findById(id);
    }

    public void save(User user) {
        userDao.save(user);
    }

    public User remove(User user){
        return userDao.remove(user);
    }

    public List<User> findAll(){
        return userDao.findAll();
    }

    public List<Report> findAllReportsByUserId(Integer id) {
        return reportDao.findAllReportForUser(id);
    }

    public double getTheSumOfActivityPrices(int id){
        List<Activity> allActivityByUserId = userDao.findAllActivitiesByUserId(id);
        System.out.println(allActivityByUserId);
        return CommonService.getTheSumOfPrices(allActivityByUserId);
    }

}
