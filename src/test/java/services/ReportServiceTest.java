package services;

import dao.ReportDao;
import dao.UserDao;
import entities.Activity;
import entities.Building;
import entities.Report;
import entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.MyHibernateSessionFactoryUtil;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ReportServiceTest extends BaseTestClass {

    @Test
    void getAllReportsByUserId() {
        //preparing
        ReportDao reportDao = new ReportDao();
        ReportService reportService = new ReportService(reportDao);

        //populate
        User user = new User("Jhon", "Smith", "smithj@gmail.com", "34903284");
        Report report1 = new Report("September report", 0, new Date(2020, 9, 2));
        Report report2 = new Report("October report", 0, new Date(2020, 10, 2));
        user.addReports(report1);
        user.addReports(report2);

        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student objects
            session.save(user);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        //do
        List<Report> allReportsByUserId = reportService.getAllReportsByUserId(user.getId());

        //expectedReportNameList has no IDs, so only compare names
        List<String> expectedReportNameList = Stream.of(report1, report2).map(Report::getName).collect(Collectors.toList());
        List<String> actualReportNameList = allReportsByUserId.stream().map(Report::getName).collect(Collectors.toList());

        //assert
        assertTrue(actualReportNameList.size() == expectedReportNameList.size() && actualReportNameList.containsAll(expectedReportNameList) && expectedReportNameList.containsAll(actualReportNameList));
    }

    @Test
    void getTheSumOfActivityPricesForReport(){
        UserDao userDao = new UserDao();
        UserService userService = new UserService(userDao);
        ReportService reportService = new ReportService(new ReportDao());

        User user = populate();
        userService.save(user);

        double reportTheSumOfActivityPrices = reportService.getTheSumOfActivityPrices(1);
        assertEquals(2030.0, reportTheSumOfActivityPrices);
    }
}