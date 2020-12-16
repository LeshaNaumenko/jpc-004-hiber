package dao;

import entities.Activity;
import entities.Building;
import entities.Report;
import entities.User;
import org.hibernate.Session;
import utils.MyHibernateSessionFactoryUtil;

import java.util.ArrayList;
import java.util.List;

public class UserDao extends AbstractDao<User> {
    /*
        public User findById(int id) {
            return super.findById((session) -> session.get(User.class, id));
        }*/
    public User findById(int id) {
        Session session = MyHibernateSessionFactoryUtil.getSessionFactory().openSession();
        User user = session.get(User.class, id);
        session.close();
        return user;
    }

    public List<Activity> findAllActivitiesByUserId(int id) {
        Session session = MyHibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Activity> allActivities = new ArrayList<>();
        User user = session.get(User.class, id);
        List<Report> reports = user.getReports();
        for (Report report : reports) {
            for (Building building : report.getBuildings()) {
                allActivities.addAll(building.getActivities());
            }
        }
        session.close();
        return allActivities;
    }

    public void save(User user) {
        performUpdatesInPersistenceContext(session -> {
            if (user.getId() == null) {
                System.out.println("Persisting");
                session.persist(user);
            } else {
                System.out.println("Merging");
                session.merge(user);
            }
        });
    }

    public User remove(User user) {
        performUpdatesInPersistenceContext(session -> session.remove(user));
        return user;
    }

    public List<User> findAll() {
        return (List<User>) MyHibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From User").list();
    }
}
