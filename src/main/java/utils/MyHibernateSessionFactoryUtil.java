package utils;

import entities.Activity;
import entities.Building;
import entities.Report;
import entities.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class MyHibernateSessionFactoryUtil {
    public static SessionFactory sessionFactory;

    public static void setSessionFactory(SessionFactory mySessionFactory) {
        sessionFactory = mySessionFactory;
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(User.class)
                        .addAnnotatedClass(Building.class)
                        .addAnnotatedClass(Report.class)
                        .addAnnotatedClass(Activity.class);
                sessionFactory = configuration.buildSessionFactory();

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return sessionFactory;
    }

    public static void closeSessionFactory() {
        if (sessionFactory != null) sessionFactory.close();
    }

}
