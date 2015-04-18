package org.exoplatform.social.dao;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.exoplatform.social.domain.Activity;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by bdechateauvieux on 4/18/15.
 */
public class ActivityDaoTest {

    ActivityDao dao = new ActivityDao();

    @Test
    public void testPersist() throws SQLException {
        //Given
        Activity activity = new Activity();
        //When
        dao.saveActivity(activity);
        //Then
        DbUtils.loadDriver("org.h2.Driver");
        Connection conn = DriverManager.getConnection("jdbc:h2:mem:bch-entities", "sa", "");
        QueryRunner query = new QueryRunner();
        ScalarHandler scalar = new ScalarHandler();
        int count = (Integer) query.query(conn, "Select count(*) from SOC_ACTIVITIES", scalar);
        assertEquals(1, count);
    }


    @Test
    public void testFindActivityByLikerId() throws SQLException {
        //Given
        Activity activity1 = new Activity();
        activity1.addLiker("1");
        activity1.addLiker("2");
        dao.saveActivity(activity1);
        Activity activity2 = new Activity();
        activity2.addLiker("3");
        dao.saveActivity(activity2);

        //When
        List<Activity> activities = dao.getActivityByLikerId("1");

        //Then
        assertEquals(1, activities.size());
    }
}
