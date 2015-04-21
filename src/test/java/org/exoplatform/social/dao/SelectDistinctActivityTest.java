package org.exoplatform.social.dao;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.exoplatform.social.domain.Activity;
import org.exoplatform.social.domain.StreamItem;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by bdechateauvieux on 4/18/15.
 */
public class SelectDistinctActivityTest {

    ActivityDao dao = new ActivityDao();

    @Test
    public void testFindActivityByLikerId() throws SQLException {
        //Given
        Activity activity = new Activity();
        dao.saveActivity(activity);
        {
            StreamItem item1 = new StreamItem();
            item1.setActivity(activity);
            item1.setOwnerId("BCH");
            item1.setOwnerStreamType(StreamItem.TYPE_MY_ACTIVITIES);
            dao.saveStreamItem(item1);
        }
        {
            StreamItem item2 = new StreamItem();
            item2.setActivity(activity);
            item2.setOwnerId("BCH");
            item2.setOwnerStreamType(StreamItem.TYPE_CONNECTIONS);
            dao.saveStreamItem(item2);
        }

        //When
        List<Activity> activities = dao.getActivityStream("BCH");

        //Then
        assertEquals(1, activities.size());
    }
}
