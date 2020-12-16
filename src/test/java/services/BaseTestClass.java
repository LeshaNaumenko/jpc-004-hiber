package services;

import entities.Activity;
import entities.Building;
import entities.Report;
import entities.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import utils.MyHibernateSessionFactoryUtil;

import java.util.Date;

public class BaseTestClass {

    public SessionFactory sessionFactory;

    User populate() {
        User user = new User("Kate", "Lkky", "lkku@gmail.com", "923084324");

        Report report1 = new Report("September report", 0, new Date(2020, 9, 2));
        Report report2 = new Report("October report", 0, new Date(2020, 10, 2));
        user.addReports(report1);
        user.addReports(report2);

        Building grey = new Building("Grey Building", false);
        Building yellow = new Building("Yellow Building", false);
        Building black = new Building("Black Building", false);
        Building pink = new Building("Pink Building", false);

        report1.addBuilding(grey);
        report1.addBuilding(yellow);
        report2.addBuilding(black);
        report2.addBuilding(pink);

        Activity activity = new Activity(" Install Hard Surface Flooring, Countertops", 1000);
        Activity activity1 = new Activity("Complete Rough Framing", 5000);
        Activity activity2 = new Activity("Complete Rough Plumbing", 300);
        Activity activity3 = new Activity("Install Insulation", 2030);
        Activity activity4 = new Activity("Complete Drywall and Interior Fixtures", 900);
        grey.addActivity(activity);
        grey.addActivity(activity2);
        grey.addActivity(activity3);
        yellow.addActivity(activity4);
        yellow.addActivity(activity2);
        yellow.addActivity(activity1);
        black.addActivity(activity);
        black.addActivity(activity4);
        pink.addActivity(activity1);
        pink.addActivity(activity2);
        return user;
    }

    @BeforeEach
    void setUp() {
        Configuration configuration = new Configuration().configure();
        configuration.addAnnotatedClass(User.class)
                .addAnnotatedClass(Building.class)
                .addAnnotatedClass(Report.class)
                .addAnnotatedClass(Activity.class);
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        configuration.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:h2:~/test");
        configuration.setProperty("hibernate.hbm2ddl.auto", "create");
        configuration.setProperty("username", "sa");
        configuration.setProperty("password", "");
        configuration.setProperty("hibernate.show_sql", "true");
        sessionFactory = configuration.buildSessionFactory();

        MyHibernateSessionFactoryUtil.setSessionFactory(sessionFactory);

    }

    @AfterEach
    void tearDown() {
        sessionFactory.close();
    }
}
