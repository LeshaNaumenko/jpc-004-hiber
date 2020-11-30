package services;

import entities.Activity;

import java.util.List;

public interface CommonService {

    static double getTheSumOfPrices(List<Activity> activities) {
        double sum = 0.0;
        for (Activity activity : activities) {
            sum+=activity.getPrice();
        }
        return sum;
    }

}
