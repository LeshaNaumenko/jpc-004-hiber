package services;

import dao.ActivityDao;
import entities.Activity;
import entities.Building;
import entities.User;
import org.apache.log4j.Logger;
import org.hibernate.*;
import utils.MyHibernateSessionFactoryUtil;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class ActivityService implements CommonService {

    private static final Logger logger = Logger.getLogger(ActivityService.class);

    private ActivityDao activityDao;

    public ActivityService(ActivityDao activityDao) {
        this.activityDao = activityDao;
    }

    public List<Activity> findAllActivityByBuildingId(Integer id) {
        return activityDao.findAllActivityByBuildingId(id);
    }

    public List<Activity> findAllActivityByUserIdAndBuildingId(Integer userId, Integer buildingId) {
        try (Session session = MyHibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            User user = session.get(User.class, userId);
            Building building = user.getReports().stream().flatMap(report -> report.getBuildings().stream())
                    .filter(x -> x.getId().equals(buildingId)).peek(System.out::println)
                    .findAny()
                    .orElseThrow(() -> new ObjectNotFoundException(buildingId, "Building"));
            Hibernate.initialize(building.getActivities());
            logger.info("All activity for user with id=" + userId + " and building with id=" + buildingId + " : " + building.getActivities());
            return building.getActivities();
        } catch (Exception ex) {
            throw new HibernateException(ex);
        }
    }
}
