package services;

import dao.ActivityDao;
import dao.BuildingDao;
import entities.Activity;
import entities.Building;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.MyHibernateSessionFactoryUtil;

import java.util.List;

public class BuildingService implements CommonService {

    private static final Logger logger = Logger.getLogger(BuildingService.class);
    private static final String ACTIVE = "active";
    private static final String NON_ACTIVE = "non-active";

    private BuildingDao buildingDao;
    private ActivityDao activityDao;

    public BuildingService(BuildingDao buildingDao) {
        this.buildingDao = buildingDao;
    }

    public BuildingService(BuildingDao buildingDao, ActivityDao activityDao) {
        this.buildingDao = buildingDao;
        this.activityDao = activityDao;
    }

    public Building findById(int id) {
        return buildingDao.performFetchingInPersistenceContext((session) -> session.get(Building.class, id));
    }

    public void save(Building building) {
        buildingDao.save(building);
    }

    public double recalculateTheTotalPrice(double specifiedPrice, Integer buildingId) {
        double sum = -1;
        boolean active = false;
        Transaction transaction = null;
        try (Session session = MyHibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Building building = session.get(Building.class, buildingId);
            sum = CommonService.getTheSumOfPrices(building.getActivities());
            active = isActive(specifiedPrice, sum);
            building.setActive(active);
            save(building);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        String state = active ? ACTIVE :NON_ACTIVE;
        logger.info("State for Building with id" + buildingId + ": " + state );
        return sum;
    }

    public double getTheSumOfActivityPrices(int id) {
        List<Activity> allActivityByUserId = activityDao.findAllActivityByBuildingId(id);
        double theSumOfPrices = CommonService.getTheSumOfPrices(allActivityByUserId);
        logger.info("Getting the sum of activity prices for Building with id : " + id + ". Sum:" + theSumOfPrices);
        return theSumOfPrices;
    }

    private boolean isActive(double specifiedPrice, double sum) {
        return sum < specifiedPrice;
    }
}
