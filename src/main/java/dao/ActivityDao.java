package dao;

import entities.Activity;
import entities.Building;
import entities.Report;
import entities.User;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class ActivityDao extends AbstractDao<Activity> {

    public List<Activity> findAllActivityByBuildingId(Integer id) {
        return performFetchingListInPersistenceContext(session -> {
            Criteria criteria = session.createCriteria(Activity.class);
            return (List<Activity>) criteria.add(Restrictions.eq("building.id", id)).list();
        });
    }

/*    public List<Activity> findAllActivityByBuildingId(Integer id) {
        return performFetchingListInPersistenceContext(session -> {
            NativeQuery sqlQuery = session.createSQLQuery("SELECT a.id, a.building_id, a.work_name, a.price\n" +
                    "FROM activity a\n" +
                    "INNER JOIN building b\n" +
                    "on b.id=a.building_id\n" +
                    "where b.id = ?1");
            sqlQuery.setParameter(1, id);
            sqlQuery.addEntity("a", Activity.class)
                    .addEntity("b", Building.class);

            return  (List<Activity>) sqlQuery.list();
        });
    }*/
}
