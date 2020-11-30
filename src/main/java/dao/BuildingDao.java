package dao;

import entities.Building;
import entities.Report;
import utils.MyHibernateSessionFactoryUtil;

import java.util.List;

public class BuildingDao extends AbstractDao<Building> {

    public Building findById(int id) {
        return performFetchingInPersistenceContext((session) -> session.get(Building.class, id));
    }

    public void save(Building building) {
        performUpdatesInPersistenceContext(session -> {
            if (building.getId() == null) {
                System.out.println("Persisting");
                session.persist(building);
            } else {
                System.out.println("Merging");
                session.merge(building);
            }
        });
    }

    public Building remove(Building building) {
        performUpdatesInPersistenceContext(session -> session.remove(building));
        return building;
    }

    public List<Building> findAll() {
        return (List<Building>) MyHibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Building").list();
    }
}
