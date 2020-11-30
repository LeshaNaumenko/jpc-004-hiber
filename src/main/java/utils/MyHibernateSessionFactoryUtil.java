package utils;

import entities.Activity;
import entities.Building;
import entities.Report;
import entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.function.Consumer;

public class MyHibernateSessionFactoryUtil {
    public static SessionFactory sessionFactory;

    public MyHibernateSessionFactoryUtil() {
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
    public static void closeSessionFactory(){
        if (sessionFactory!=null) sessionFactory.close();
    }


}
