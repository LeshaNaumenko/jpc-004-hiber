package services;

import dao.AbstractDao;
import dao.ActivityDao;
import dao.BuildingDao;
import entities.Activity;
import entities.Building;
import entities.Report;

import java.util.List;

public class BuildingService  implements CommonService {

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

    public double recalculateTheTotalPrice(double specifiedPrice, Integer buildingId){
        Building building = findById(buildingId);
        double sum = CommonService.getTheSumOfPrices(building.getActivities());
        boolean active = isActive(specifiedPrice, sum);
        System.out.println(active);
        building.setActive(active);
        save(building);
        return sum;
    }
    public double getTheSumOfActivityPrices(int id){
        List<Activity> allActivityByUserId = activityDao.findAllActivityByBuildingId(id);
        System.out.println(allActivityByUserId);
        return CommonService.getTheSumOfPrices(allActivityByUserId);
    }

    private boolean isActive(double specifiedPrice, double sum) {
        boolean active = false;
        if (sum < specifiedPrice){
            active = true;
        }
        return active;
    }


}
