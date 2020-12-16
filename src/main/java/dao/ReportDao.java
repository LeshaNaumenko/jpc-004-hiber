package dao;

import entities.Activity;
import entities.Building;
import entities.Report;
import entities.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utils.MyHibernateSessionFactoryUtil;

import java.util.ArrayList;
import java.util.List;

public class ReportDao extends AbstractDao<Report> {

    public Report findById(int id) {
        return performFetchingInPersistenceContext((session) -> session.get(Report.class, id));
    }

    public void save(Report report) {
        performUpdatesInPersistenceContext(session -> {
            if (report.getId() == null) {
                System.out.println("Persisting");
                session.persist(report);
            } else {
                System.out.println("Merging");
                session.merge(report);
            }
        });
    }

    public Report remove(Report report) {
        performUpdatesInPersistenceContext(session -> session.remove(report));
        return report;
    }

    public List<Report> findAll() {
        return (List<Report>) MyHibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Report").list();
    }

    public List<Activity> findAllActivitiesByReportId(int id) {
        Session session = MyHibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Activity> allActivities = new ArrayList<>();
        Report report = session.get(Report.class, id);
        for (Building building : report.getBuildings()) {
            allActivities.addAll(building.getActivities());
        }
        session.close();
        return allActivities;
    }
}
