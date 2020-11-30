package services;

import dao.ReportDao;
import entities.Activity;
import entities.Report;
import entities.User;

import java.util.List;

public class ReportService implements CommonService{

    private ReportDao reportDao;

    public ReportService(ReportDao reportDao) {
        this.reportDao = reportDao;
    }

    public void save(Report report) {
        reportDao.save(report);
    }

    public double getTheSumOfActivityPrices(int id){
        List<Activity> allActivityByUserId = reportDao.findAllActivitiesByReportId(id);
        return CommonService.getTheSumOfPrices(allActivityByUserId);
    }
}
