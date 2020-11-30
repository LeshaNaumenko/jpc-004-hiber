import dao.ActivityDao;
import dao.BuildingDao;
import dao.ReportDao;
import dao.UserDao;
import entities.Activity;
import entities.Building;
import entities.Report;
import entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import services.ActivityService;
import services.BuildingService;
import services.ReportService;
import services.UserService;
import utils.MyHibernateSessionFactoryUtil;

import java.util.Date;
import java.util.List;

public class Main {

    public static void main1(String[] args) {
        UserDao userDao = new UserDao();
        ReportDao reportDao = new ReportDao();
        ActivityDao activityDao = new ActivityDao();
        UserService userService = new UserService(userDao, activityDao, reportDao);
        BuildingDao buildingDao = new BuildingDao();
        BuildingService buildingService = new BuildingService(buildingDao);
        ReportService reportService = new ReportService(reportDao);
        ActivityService activityService = new ActivityService(activityDao);

/*        User user1 = new User("Jhon", "Smith", "smithj@gmail.com", "34903284");
        User user2 = new User("Kate", "Lkky", "lkku@gmail.com", "923084324");

        Report report1 = new Report("September report", 0, new Date(2020,9,2));
        Report report2 = new Report("October report", 0, new Date(2020,10,2));
        user1.addReports(report1);
        user2.addReports(report2);

        Building grey = new Building("Grey Building", false );
        Building yellow = new Building("Yellow Building", false );
        Building black = new Building("Black Building", false );
        Building pink = new Building("Pink Building", false );
        Building grey = buildingService.findById(8);
        Building yellow = buildingService.findById(9);
        Building black = buildingService.findById(10);
        Building pink = buildingService.findById(11);



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


        buildingService.save(grey);
        buildingService.save(yellow);
        buildingService.save(black);
        buildingService.save(pink);
        */


    }

    public static void main(String[] args) {
        UserDao userDao = new UserDao();
        ReportDao reportDao = new ReportDao();
        ActivityDao activityDao = new ActivityDao();
        UserService userService = new UserService(userDao, activityDao, reportDao);
        ReportService reportService = new ReportService(reportDao);
        ActivityService activityService = new ActivityService(activityDao);
        BuildingDao buildingDao = new BuildingDao();
        BuildingService buildingService = new BuildingService(buildingDao, activityDao);


        //HDL.R.6
        // a. Get all Reports information for particular user
        User byId = userService.findById(1);
        System.out.println(byId.getReports());

        //=====================================================================
        // b. Get All activities information for Particular user and Specified building
        List<Activity> allActivityByUserId = activityService.findAllActivityByBuildingId(1);
        System.out.println(allActivityByUserId);

        /*
        List<Activity> allActivityByBuildingId = activityService.findAllActivityByBuildingId(1);
        System.out.println(allActivityByBuildingId);*/

        // C. Set Buildings in non-Active state where total price of all activities is more than specified value
        double v = buildingService.recalculateTheTotalPrice(4000, 10);
        System.out.println(v);
        //=====================================================================
        // D. Get total Activities price for particular building/report/user.

        //for particular building

        double buildingTheSumOfActivityPrices = buildingService.getTheSumOfActivityPrices(9);
        System.out.println("TheSumOfActivityPrices for bbuilding " + buildingTheSumOfActivityPrices);
        //for particular report
        double reportTheSumOfActivityPrices = reportService.getTheSumOfActivityPrices(9);
        System.out.println("theSumOfActivityPrices for report " + reportTheSumOfActivityPrices);

        //for particular user
        double userTheSumOfActivityPrices = userService.getTheSumOfActivityPrices(7);
        System.out.println("theSumOfActivityPrices for User " + userTheSumOfActivityPrices);
        //=====================================================================

        MyHibernateSessionFactoryUtil.closeSessionFactory();

    }
}
