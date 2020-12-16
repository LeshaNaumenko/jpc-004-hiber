package services;

import dao.ReportDao;
import entities.Activity;
import entities.Report;
import entities.User;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utils.MyHibernateSessionFactoryUtil;

import java.util.List;

public class ReportService implements CommonService{

    private static final Logger logger = Logger.getLogger(ReportService.class);

    private ReportDao reportDao;

    public ReportService(ReportDao reportDao) {
        this.reportDao = reportDao;
    }

    public void save(Report report) {
        reportDao.save(report);
    }

    public double getTheSumOfActivityPrices(int id){
        List<Activity> allActivityByUserId = reportDao.findAllActivitiesByReportId(id);
        double theSumOfPrices = CommonService.getTheSumOfPrices(allActivityByUserId);
        logger.info("Getting the sum of activity prices for Report with id : " + id + ". Sum:" + theSumOfPrices);
        return theSumOfPrices;
    }

    public List<Report> getAllReportsByUserId(Integer id){
        Session session = MyHibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Report r where r.user.id = :id");
        query.setParameter("id", id);
        List<Report> reports = (List<Report>)query.list();
        session.close();
        logger.info("All reports for user id = " + id + ": " + reports);
        return reports;

    }
}
